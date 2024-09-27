package com.automation.steps;

import com.automation.utils.ConfigReader;
import io.cucumber.java.Before;
import io.restassured.RestAssured;

public class Hooks {
    @Before
    public void setUp() {
        ConfigReader.initConfig();

        if (ConfigReader.getConfigValue("application.name").equals("restful-api")) {
            RestAssured.baseURI = ConfigReader.getConfigValue("restful.api.base.uri");
        } else {
            RestAssured.baseURI = ConfigReader.getConfigValue("base.uri");
        }


    }
}
