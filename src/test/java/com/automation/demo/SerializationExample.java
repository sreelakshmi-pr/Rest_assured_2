package com.automation.demo;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SerializationExample {
    public static void main(String[] args) throws Exception {
        String content = getDataFromFile("src/test/resources/json/create_booking.json");

        ObjectMapper mapper = new ObjectMapper();
        CreateBookingPojo createBookingPojo = mapper.readValue(content, CreateBookingPojo.class);
        System.out.println(createBookingPojo.toString());
    }

    public static String getDataFromFile(String filePath) throws FileNotFoundException {
        String content = new Scanner(new FileInputStream(filePath)).useDelimiter("\\Z").next();
        return content;
    }

}
