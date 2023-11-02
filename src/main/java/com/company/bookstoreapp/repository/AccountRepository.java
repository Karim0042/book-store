package com.company.bookstoreapp.repository;

import com.company.bookstoreapp.model.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account,String> {
    Optional<Account> findByEmail(String email);
    boolean existsAccountByEmail(String email);
}
