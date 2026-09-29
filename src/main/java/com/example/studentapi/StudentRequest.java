package com.example.studentapi;

// StudentRequest.java - JSON accepted from the client

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record StudentRequest(
        @NotBlank String name,
        @Email @NotBlank String email,
        @NotBlank String course
) {
}
