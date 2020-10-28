Feature: To validate PetStore API functionality

  @API
  Scenario: validate GET Method for Pet API
    Given user have the endpoint as "baseURL/ENDPOINT_GET_PET"
    When user send the "get" request to "ENDPOINT_GET_PET" using
      | ContentType | application/json |           |
      | query       | status           | available |
    Then user assert all pets status as "available"

  @API
  Scenario: Validate POST Method add Pet API
    Given user have the endpoint as "baseURL/ENDPOINT_ADD_PET"
    When user send the "post" request using request body as "AddPet"
      | ContentType | application/json |  |
    Then user assert value as "demotestsumit" from response path "category.name"
    And user store the "id" of Pet

  @API
  Scenario: Validate PUT Method update status of Pet API
    Given user have the endpoint as "baseURL/ENDPOINT_ADD_PET"
    When user send the "put" request using request body as "UpdatePetStatus"
      | ContentType | application/json |  |
    Then user assert value as "sold" from response path "status"

  @API
  Scenario: Validate Delete Method  Pet API
    Given user have the endpoint as "baseURL/ENDPOINT_DELETE_PET"
    When user send the "delete" request to "ENDPOINT_DELETE_PET" using
      | ContentType | application/json |    |
      | path        | petid            | id |
    Then user assert value as "id" from response path "message"
