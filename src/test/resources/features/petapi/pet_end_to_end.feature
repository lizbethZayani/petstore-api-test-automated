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