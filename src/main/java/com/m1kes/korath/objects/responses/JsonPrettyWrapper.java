package com.m1kes.korath.objects.responses;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class JsonPrettyWrapper {

    public static String toJson(JsonResponse response) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter writer = mapper.writer().withDefaultPrettyPrinter();
        try {
            return writer.writeValueAsString(response);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String toJson(JsonLogResponse response) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter writer = mapper.writer().withDefaultPrettyPrinter();
        try {
            return writer.writeValueAsString(response);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "";
        }
    }

}
