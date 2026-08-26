package com.chacuio.issueflowapi.users.dto;

import com.chacuio.issueflowapi.users.model.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record UserRequestDTO(
        @NotBlank(message = "The name field cannot be null")
        @Length(max = 150, min = 1, message = "The name field must be between 1 and 150 characters long")
        String name,

        @NotBlank(message = "The email field cannot be null")
        @Length(max = 150, message = "The email field can have up to 150 characters.")
        @Email(message = "The email field must be in a valid format (example@email.com)")
        String email,

        @NotBlank(message = "The password field cannot be null")
        @Length(min = 6, message = "The password field must be at least 6 characters long")
        String password,

        @NotBlank(message = "The role field cannot be null")
        Role role
) { }
