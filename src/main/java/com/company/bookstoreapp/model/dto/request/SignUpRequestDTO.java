package com.company.bookstoreapp.model.dto.request;

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

    @Pattern(regexp = "[0-9]+")
    String password;

    Integer age;


}