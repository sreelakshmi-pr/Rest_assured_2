Feature: Validate create booking end point

  Scenario: Verify user can create booking
    Given user wants to call "/booking" end point
    And set header "Content-Type" to "application/json"
    And set request body from the file "create_booking.json"
    When user performs post call
    Then verify status code is 200
    And verify booking id is not empty
    And stores created booking id into "booking.id"
    When user wants to call "/auth" end point
    And set header "Content-Type" to "application/json"
    And set request body from the file "create_token.json"
    And user performs post call
    Then verify status code is 200
    And store token value to "api.token"
    When user wants to call "/booking/@id" end point
    And set header "Content-Type" to "application/json"
    And set header "Cookie" to "token=@token"
    And set request body from the file "create_booking.json" with random price
    And user performs put call
    Then verify status code is 200
