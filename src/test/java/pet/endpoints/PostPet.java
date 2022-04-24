package pet.endpoints;

import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class PostPet {

    private static String POST_PET = "https://petstore.swagger.io/v2/pet";

    @Step("Post a new pet")
    public void withDetails(String pet) {
        SerenityRest.given()
                .contentType("application/json")
                .header("Content-Type", "application/json")
                .body(pet)
                .when()
                .post(POST_PET);
    }
}
