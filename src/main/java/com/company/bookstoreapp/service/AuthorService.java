package com.company.bookstoreapp.service;

import com.company.bookstoreapp.model.dto.request.SignUpRequestDTO;
import com.company.bookstoreapp.model.entity.Account;

public interface AuthorService {
     void saveAuthor(SignUpRequestDTO signUpRequestDTO, Account account);
}
