package pet.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import pet.endpoints.*;
import pet.template.FieldValues;
import pet.template.MergeFrom;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.hamcrest.Matchers.equalTo;

public class PetStepDefinitions {

    @Steps
    PetFindByStatus petFindByStatus;

    @Steps
    PostPet postPet;

    @Steps
    PetFindById petFindById;

    @Steps
    UpdatePetStatus updatePetStatus;

    @Steps
    DeletePet deletePet;

    String pet;

    @When("I look up the {string} pets")
    public void iLookUpThePets(String status) {
        petFindByStatus.fetchPetByStatus(status);
    }

    @Then("the user should get the response code {int}")
    public void theUserShouldGetTheResponseCode(int statusCode) {
        restAssuredThat(response -> response.statusCode(statusCode));
    }

    @Given("the following pet:")
    public void theFollowingPet(List<Map<String, String>> tradeDetails) throws IOException {
        pet = MergeFrom.template("templates/pet.json")
                .withDefaultValuesFrom(FieldValues.in("templates/standard.properties"))
                .withFieldsFrom(tradeDetails.get(0));
    }

    @When("the user post a new available pet")
    public void theUserPostANewAvailablePet() {
        postPet.withDetails(pet);
    }

    @And("the pet should be as {string}")
    public void theShouldBeAs(String status) {
        restAssuredThat(response -> response.statusCode(200));
        restAssuredThat(response -> response.body(LocationResponse.STATUS, equalTo(status)));
    }

    @Given("the following pet update:")
    public void theUserUpdateTheStatusTo(List<Map<String, String>> tradeDetails) throws IOException {
        pet = MergeFrom.template("templates/pet.json")
                .withDefaultValuesFrom(FieldValues.in("templates/standard.properties"))
                .withFieldsFrom(tradeDetails.get(0));
    }

    @When("the user update the pet")
    public void theUserUpdateThePet() {
        updatePetStatus.withStatus(pet);
    }

    @When("the user delete the pet {string}")
    public void theUserDeleteThePet(String petId) {
        deletePet.deletePetById(petId);
    }

    @And("the pet {string} should no longer exist")
    public void thePetShouldNoLongerExist(String petId) {
        petFindById.fetchPetById(petId);
        restAssuredThat(response -> response.statusCode(404));
    }
}
