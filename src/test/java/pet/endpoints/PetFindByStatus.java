package pet.endpoints;

import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class PetFindByStatus {

    private static String PET_BY_STATUS = "https://petstore.swagger.io/v2/pet/findByStatus?status={status}";

    /**
     * Method to request the available pets by the get endpoint
     * @param status
     */
    @Step("Get pet by status {0}")
    public void fetchPetByStatus(String status) {
        SerenityRest.given()
                .pathParam("status", status)
                .get(PET_BY_STATUS);
    }

}
