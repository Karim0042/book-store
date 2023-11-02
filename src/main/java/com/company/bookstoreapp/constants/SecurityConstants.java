package com.company.bookstoreapp.constants;

import lombok.Data;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
@Getter
public class SecurityConstants {

    @Value("${security.auth.whitelist}")
    private String[] whiteList;
}
