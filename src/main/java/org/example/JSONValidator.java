package org.example;

public class JSONValidator {

    public static boolean isThisStringJSON(String jsonString) {
        try {
            //new JSONObject(jsonString);
            return true;
        } catch (Exception e) {
            return false;
        }

    }
}
