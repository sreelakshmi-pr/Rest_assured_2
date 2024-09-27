package com.automation.steps;

import com.automation.pojo.CreateObjectPojo;
import com.automation.utils.ConfigReader;
import com.automation.utils.RestAssuredUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.junit.Assert;

public class ResponseSteps {

    @Then("verify status code is {int}")
    public void verify_status_code_is(int statusCode) {
        Assert.assertEquals(statusCode, RestAssuredUtils.getStatusCode());
    }

    @And("stores created booking id into {string}")
    public void storesCreatedBookingIdInto(String key) {
        ConfigReader.setConfigValue(key, RestAssuredUtils.getResponseFieldValue("bookingid"));
    }

    @And("verify booking id is not empty")
    public void verifyBookingIdIsNotEmpty() {
        String bookingId = RestAssuredUtils.getResponseFieldValue("bookingid");
        Assert.assertTrue(!bookingId.isEmpty());
    }

    @And("store token value to {string}")
    public void storeTokenValueTo(String key) {
        String token = RestAssuredUtils.getResponseFieldValue("token");
        ConfigReader.setConfigValue(key, token);
    }

    @And("verify response is same as request passed in post call")
    public void verifyResponseIsSameAsRequestPassedInPostCall() throws JsonProcessingException {
        String content = RestAssuredUtils.getDataFromFile("src/test/resources/json/create_object.json");
        ObjectMapper om = new ObjectMapper();

        CreateObjectPojo expectedRequest = om.readValue(content, CreateObjectPojo.class);
        CreateObjectPojo actualResponse = RestAssuredUtils.getResponse().as(CreateObjectPojo.class);

        Assert.assertTrue(expectedRequest.equals(actualResponse));
    }

    @And("stores created id into {string}")
    public void storesCreatedIdInto(String key) {
        String id = RestAssuredUtils.getResponseFieldValue("id");
        ConfigReader.setConfigValue(key, id);
    }

    @And("verify response is matching with the {string}")
    public void verifyResponseIsMatchingWithThe(String fileName) {
        RestAssuredUtils.getResponse().then().assertThat()
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("json/create_object_schema.json"));
    }

    @And("verify response message is {string}")
    public void verifyResponseMessageIs(String message) {
        Assert.assertEquals(message, RestAssuredUtils.getResponseFieldValue("reason"));
    }
}
