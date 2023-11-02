package com.company.bookstoreapp.service.impl;

import com.company.bookstoreapp.model.dto.request.SignUpRequestDTO;
import com.company.bookstoreapp.model.entity.Account;
import com.company.bookstoreapp.model.entity.Author;
import com.company.bookstoreapp.model.entity.Role;
import com.company.bookstoreapp.repository.AuthorRepository;
import com.company.bookstoreapp.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    @Override
    public void saveAuthor(SignUpRequestDTO signUpRequestDTO,Account account) {
        Author author  = buildNewAuthor(signUpRequestDTO,account);
        authorRepository.save(author);
    }
    private Author buildNewAuthor(SignUpRequestDTO userSignUpRequest,Account account) {
        return Author.builder().
                account(account)
                .age(userSignUpRequest.getAge())
                .name(userSignUpRequest.getName()).build();
    }
}
