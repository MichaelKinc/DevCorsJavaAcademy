package com.devcors.javaacademy.carrental.data.dto;

import com.devcors.javaacademy.carrental.annotation.EnumValidator;
import com.devcors.javaacademy.carrental.annotation.NullOrNotBlankValidator;
import com.devcors.javaacademy.carrental.data.entity.enums.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserUpdateDTO {

    @NullOrNotBlankValidator(message = "Email cannot be blank.")
    @Email(message = "The email address is invalid.", flags = {Pattern.Flag.CASE_INSENSITIVE})
    private String email;

    @NullOrNotBlankValidator(message = "First name cannot be blank.")
    private String firstname;

    @NullOrNotBlankValidator(message = "Last name cannot be blank.")
    private String lastname;

    @NullOrNotBlankValidator(message = "Address cannot be blank.")
    private String address;

    @NullOrNotBlankValidator(message = "Email cannot be blank.")
    @EnumValidator(enumClass = UserRole.class)
    private String role;

    @NullOrNotBlankValidator(message = "Password cannot be blank.")
    private String password;
}
