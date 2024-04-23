package org.example;

import com.google.gson.Gson;

public class JSONMapperGSON {

    private final Gson gson;

    public JSONMapperGSON(Gson gson) {
        this.gson = gson;
    }


    public <T> T mapToGSON(String json, Class<T> selectedClass) {

        if ((json == null) || (json.isEmpty())) {

            throw new IllegalArgumentException("json is null or empty");
        } else {
            return gson.fromJson(json, selectedClass);
        }


    }
}