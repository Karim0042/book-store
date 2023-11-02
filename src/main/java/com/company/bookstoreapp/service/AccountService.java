package com.company.bookstoreapp.service;

import com.company.bookstoreapp.model.dto.request.SignInRequestDto;
import com.company.bookstoreapp.model.dto.request.SignUpRequestDTO;
import com.company.bookstoreapp.model.entity.Account;
import com.company.bookstoreapp.model.jwt.JwtToken;
import com.company.bookstoreapp.security.JWTProvider;

public interface AccountService {
    JwtToken signup(SignUpRequestDTO signUpRequestDTO);
    JwtToken signIn(SignInRequestDto signInRequestDto);
}
