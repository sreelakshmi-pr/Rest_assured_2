package com.automation.steps;

import com.automation.pojo.CreateBookingPojo;
import com.automation.pojo.CreateTokenPojo;
import com.automation.utils.ConfigReader;
import com.automation.utils.RestAssuredUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.Random;

public class RequestSteps {

    @Given("user wants to call {string} end point")
    public void user_wants_to_call_end_point(String endPoint) {

        if (endPoint.contains("@id")) {
            String bookingId = ConfigReader.getConfigValue("booking.id");
            endPoint = endPoint.replace("@id", bookingId);
        }

        RestAssuredUtils.setEndPoint(endPoint);
    }

    @Given("set header {string} to {string}")
    public void set_header_to(String key, String value) {
        if (value.contains("@token")) {
            value = value.replace("@token", ConfigReader.getConfigValue("api.token"));
        }
        RestAssuredUtils.setHeader(key, value);
    }

    @Given("set request body from the file {string}")
    public void set_request_body_from_the_file(String fileName) {
        RestAssuredUtils.setBody(fileName);
    }

    @When("user performs post call")
    public void user_performs_post_call() {
        RestAssuredUtils.post();
    }

    @When("user performs get call")
    public void user_performs_get_call() {
        RestAssuredUtils.get();
    }


    @And("user performs put call")
    public void userPerformsPutCall() {
        RestAssuredUtils.put();
    }

    @And("set request body from the file {string} with random price")
    public void setRequestBodyFromTheFileWithRandomPrice(String fileName) throws JsonProcessingException {
        String jsonFolderPath = ConfigReader.getConfigValue("json.folder.path");
        String jsonBody = RestAssuredUtils.getDataFromFile(jsonFolderPath + fileName);
        ObjectMapper om = new ObjectMapper();
        CreateBookingPojo createBookingPojo = om.readValue(jsonBody, CreateBookingPojo.class);

        // Generate random price
        int price = new Random().nextInt(1000);
        createBookingPojo.setTotalprice(price);
        RestAssuredUtils.setBodyUsingPojo(createBookingPojo);
    }

    @And("set request body from the file {string} with username {string} and password {string}")
    public void setRequestBodyFromTheFileWithUsernameAndPassword(String fileName, String username, String password) throws JsonProcessingException {
        String jsonFolderPath = ConfigReader.getConfigValue("json.folder.path");
        String content = RestAssuredUtils.getDataFromFile(jsonFolderPath + fileName);

        ObjectMapper om = new ObjectMapper();
        CreateTokenPojo createTokenPojo = om.readValue(content, CreateTokenPojo.class);

        createTokenPojo.setUsername(username);
        createTokenPojo.setPassword(password);

        RestAssuredUtils.setBodyUsingPojo(createTokenPojo);
    }
}
