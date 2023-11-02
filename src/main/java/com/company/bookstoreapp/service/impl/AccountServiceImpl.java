package com.company.bookstoreapp.service.impl;

import com.company.bookstoreapp.error.Exception.AuthenticationException;
import com.company.bookstoreapp.error.Exception.ResourceAlreadyExistsException;
import com.company.bookstoreapp.error.Exception.ResourceNotFoundException;
import com.company.bookstoreapp.model.dto.request.SignInRequestDto;
import com.company.bookstoreapp.model.dto.request.SignUpRequestDTO;
import com.company.bookstoreapp.model.entity.Account;
import com.company.bookstoreapp.model.entity.Role;
import com.company.bookstoreapp.model.jwt.JwtToken;
import com.company.bookstoreapp.repository.AccountRepository;
import com.company.bookstoreapp.repository.RoleRepository;
import com.company.bookstoreapp.security.JWTProvider;
import com.company.bookstoreapp.service.AccountService;
import com.company.bookstoreapp.service.AuthorService;
import com.company.bookstoreapp.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final RoleRepository roleRepository;
    private final AuthorService authorService;
    private final StudentService studentService;
    private final JWTProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    @Override
    public JwtToken signup(SignUpRequestDTO signUpRequestDTO) {
        boolean existByEmail = accountRepository.existsAccountByEmail(signUpRequestDTO.getEmail());
        if (existByEmail) {
            throw new ResourceAlreadyExistsException("mess");
        }
        var role = signUpRequestDTO.getProfession();
        Account account = buildNewAccount(signUpRequestDTO);
        switch (role) {
            case AUTHOR -> authorService.saveAuthor(signUpRequestDTO, account);
            case STUDENT -> studentService.saveStudent(signUpRequestDTO, account);
        }
        return jwtProvider.getJWTToken(account.getId());
    }

    @Override
    public JwtToken signIn(SignInRequestDto signInRequestDto) {
        var account = accountRepository.findByEmail(signInRequestDto.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("Account not found with this email: "
                        + signInRequestDto.getEmail()));

        if (passwordEncoder.matches(signInRequestDto.getPassword(), account.getPassword())) {
            return jwtProvider.getJWTToken(account.getId());
        }

        throw new AuthenticationException("Invalid username or password");
    }

    private Account buildNewAccount(SignUpRequestDTO userSignUpRequest) {
        Role role = roleRepository.findRoleByRoleName(userSignUpRequest.getProfession().name());
        return Account.builder()
                .role(role)
                .email(userSignUpRequest.getEmail())
                .password(passwordEncoder.encode(userSignUpRequest.getPassword()))
                .build();
    }
}
