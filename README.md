# REST API testing with Serenity and Cucumber

Serenity BDD is a library that makes it easier to write high quality automated acceptance tests, with powerful reporting and living documentation features. It has strong support for both web testing with Selenium, and API testing using RestAssured.

Serenity strongly encourages good test automation design, and supports several design patterns, including classic Page Objects, the newer Lean Page Objects/ Action Classes approach, and the more sophisticated and flexible Screenplay pattern.

## The pet project
The best place to start with the project is to clone or download the pet project on Github. This project interact with the DEMO PET STORE: https://petstore.swagger.io/. This is an PET API shop where you make a purchase a pet, adding, updating and deleting a pet.
The framework is developing with Maven then you have to have installed Java and Maven in your local machine to run it.
Serenity with Cucumber is implemented to write and describe the scenarios.
### The project directory structure
```Gherkin
src
  + main
  + test
    + java          Test runners and supporting code
       + pet
          + endpoints        Interaction classes that  interact with API
          + stepdefinitions  Action classes
          + template         Classes to generate test data for REST API
    + resources          Feature and config file
       + features         Feature file
            pet_end_to_end.feature
      + templates
        pet.json
        standard.properties
             
```

## The scenarios
The scenario is described in Gherkin. It is covering an end-to-end test for the pet endpoints. From creating a new pet to deleting it.
```Gherkin
Feature: PET API End to End Testing

  Scenario: Pet end to end request testing
    When I look up the "available" pets
    Then the user should get the response code 200
    Given the following pet:
      | id    | name     | status          |
      | 90899  | Rojo    | available       |
    When the user post a new available pet
    Then the user should get the response code 200
    And the pet should be as "available"
    Given the following pet update:
      | id       | name        | status   |
      | 90899     | Rojo       | sold    |
    When the user update the pet
    Then the user should get the response code 200
    And the pet should be as "sold"
    When the user delete the pet "90899"
    Then the user should get the response code 200
    And the pet "90899" should no longer exist
```
### The Action Classes implementation.

The action classes pattern is implemented in the rest of the actions. Here is an example:

```java
    @When("I look up the {string} pets")
public void iLookUpThePets(String status) {
        petFindByStatus.fetchPetByStatus(status);
        }

@Then("the user should get the response code {int}")
public void theUserShouldGetTheResponseCode(int statusCode) {
        restAssuredThat(response -> response.statusCode(statusCode));
        }
```
These classes are declared using the Serenity `@Steps` annotation, shown below:
```java

@Steps
    PetFindByStatus petFindByStatus;

@Steps
    PostPet postPet;
```

The `@Steps`annotation tells Serenity to create a new instance of the class, and inject any other steps or page objects that this instance might need.

The `PetFindById` class is an example of a very simple interaction class. This class is the responsible for getting the pet by id.
```java
public class PetFindById {

    private static String PET_BY_ID = "https://petstore.swagger.io/v2/pet/{id}";

    @Step("Get pet by id {0}")
    public void fetchPetById(String id) {
        SerenityRest.given()
                .pathParam("id", id)
                .get(PET_BY_ID);
    }
}
```

## Executing the tests
To run the project, you can either just run the `CucumberTestSuite` test runner class, or run either `mvn verify` from the command line.

```json
$ mvn clean verify
```
To open the full report run the command:
```json
open target/site/serenity/index.html
```

The test results will be recorded in the `target/site/serenity` directory.



