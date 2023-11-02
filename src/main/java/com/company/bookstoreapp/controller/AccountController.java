package com.company.bookstoreapp.controller;

import com.company.bookstoreapp.model.dto.request.SignInRequestDto;
import com.company.bookstoreapp.model.dto.request.SignUpRequestDTO;
import com.company.bookstoreapp.model.jwt.JwtToken;
import com.company.bookstoreapp.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v3/accounts")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @PostMapping("/signup")
    public ResponseEntity<JwtToken> signup(@RequestBody @Valid SignUpRequestDTO signUpRequestDTO) {
        return ResponseEntity.ok(accountService.signup(signUpRequestDTO));
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtToken> signIn(@RequestBody @Valid SignInRequestDto signInRequestDto) {
        return ResponseEntity.ok(accountService.signIn(signInRequestDto));
    }
}
