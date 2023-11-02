package com.company.bookstoreapp.repository;

import com.company.bookstoreapp.model.entity.Account;
import com.company.bookstoreapp.model.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, String> {
    Author findAuthorByAccountId(String id);


}
