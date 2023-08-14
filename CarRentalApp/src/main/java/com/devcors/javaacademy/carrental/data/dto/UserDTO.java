package com.devcors.javaacademy.carrental.data.dto;

import com.devcors.javaacademy.carrental.annotation.EnumValidator;
import com.devcors.javaacademy.carrental.data.entity.enums.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern.Flag;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {

    @NotBlank(message = "Email is required.")
    @Email(message = "The email address is invalid.", flags = {Flag.CASE_INSENSITIVE})
    private String email;

    @NotBlank(message = "First name is required.")
    private String firstname;

    @NotBlank(message = "Last name is required.")
    private String lastname;

    @NotBlank(message = "Address is required.")
    private String address;

    @NotBlank(message = "Role is required.")
    @EnumValidator(enumClass = UserRole.class)
    private String role;

    @NotBlank(message = "Password is required.")
    private String password;
}
