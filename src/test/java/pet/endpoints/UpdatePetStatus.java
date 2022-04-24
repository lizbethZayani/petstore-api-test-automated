package pet.endpoints;

import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class UpdatePetStatus {
    private static String UPDATE_PET_STATUS = "https://petstore.swagger.io/v2/pet";

    @Step("Update the pet")
    public void withStatus(String pet) {
        SerenityRest.given()
                .contentType("application/json")
                .header("Content-Type", "application/json")
                .body(pet)
                .when()
                .put(UPDATE_PET_STATUS);
    }

}
