package com.example.studentapi;

// StudentResponse.java - JSON returned to the client
public record StudentResponse(
        Long id, String name, String email, String course
) {
}
