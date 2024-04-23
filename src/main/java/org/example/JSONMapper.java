package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONMapper {

    private ObjectMapper mapper = new ObjectMapper();

    public <T> T mapTo(String json, Class<T> selectedClass) {
        try {
          return  mapper.readValue(json,selectedClass);
        } catch (JsonProcessingException e){//|IllegalArgumentException e) {
            throw new IllegalArgumentException(e);
            //throw new RuntimeException(e);
        }
    }
}
