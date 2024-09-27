Feature: Create Object

  Scenario: Verify user can create object
    Given user wants to call "/objects" end point
    And set header "Content-Type" to "application/json"
    And set request body from the file "create_object.json"
    When user performs post call
    Then verify status code is 200
    And stores created id into "booking.id"
    When user wants to call "/objects/@id" end point
    And user performs get call
    Then verify status code is 200
    And verify response is same as request passed in post call
    And verify response is matching with the "create_object_schema.json"