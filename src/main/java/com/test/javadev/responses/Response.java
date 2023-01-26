package com.test.javadev.responses;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;

public class Response {
        public static ResponseEntity<?> errorResponse(HttpStatus status, String message) {
            HashMap<String, Object> response = new HashMap<>();
            response.put("message", message);

            return new ResponseEntity(response, status);
        }
}
