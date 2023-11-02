package com.company.bookstoreapp.model.dto.request;

import com.company.bookstoreapp.model.enums.Profession;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SignUpRequestDTO {
    @Email
    String email;
    @NotEmpty(message = "last name can not empty!")
    String name;

    @NotEmpty(message = "Password is required!")
    String password;

    Integer age;
    Profession profession;


}