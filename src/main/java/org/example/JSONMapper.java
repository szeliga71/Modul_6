package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONMapper {

    private final ObjectMapper MAPPER = new ObjectMapper();

    public <T> T mapTo(String json, Class<T> selectedClass) {
        try {
            return MAPPER.readValue(json, selectedClass);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e);

        }
    }
}
