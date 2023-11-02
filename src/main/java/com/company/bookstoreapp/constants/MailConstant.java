package com.company.bookstoreapp.constants;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class MailConstant {
    @Value("${spring.mail.username}")
    private String email;
}
