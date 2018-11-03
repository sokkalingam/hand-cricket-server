package com.project.handcricket.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MvcResult;

import java.io.IOException;

@Component
public class TestUtils {

    private ObjectMapper objectMapper;

    public TestUtils() {
        objectMapper = new ObjectMapper();
    }

    public <T> T getResponseObject(MvcResult result, Class<T> cls) {
        try {
            return objectMapper.readValue(result.getResponse().getContentAsString(), cls);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public <T> String getObjectAsJson(T t) {
        try {
            return objectMapper.writeValueAsString(t);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }
}
