package pet.endpoints;

import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class DeletePet {


    private static String PET_BY_ID = "https://petstore.swagger.io/v2/pet/{id}";

    /**
     * Method to delete the pet git through the deleted endpoint
     * @param id
     */
    @Step("the user delete the pet {0}")
    public void deletePetById(String id) {
        SerenityRest.given()
                .pathParam("id", id)
                .delete(PET_BY_ID);
    }
}
