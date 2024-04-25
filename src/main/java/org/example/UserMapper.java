package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UserMapper {

    private final ObjectMapper MAPPER = new ObjectMapper();

    public User mapToUserFromJSONStringUserFields(String json) {

        User user = new User();

        try {
            JsonNode node = MAPPER.readTree(json);
            user.setId(node.get("id").longValue());
            user.setName(node.get("name").textValue());
            user.setAge(node.get("age").intValue());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return user;
    }
}
