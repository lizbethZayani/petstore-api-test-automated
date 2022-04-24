package pet.endpoints;

import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class PetFindById {

    private static String PET_BY_ID = "https://petstore.swagger.io/v2/pet/{id}";

    /**
     * Method to request the pet by id through the get endpoint
     * @param id
     */
    @Step("Get pet by id {0}")
    public void fetchPetById(String id) {
        SerenityRest.given()
                .pathParam("id", id)
                .get(PET_BY_ID);
    }
}
