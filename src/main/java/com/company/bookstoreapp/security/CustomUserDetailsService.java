package com.company.bookstoreapp.security;

import com.company.bookstoreapp.error.Exception.AuthenticationException;
import com.company.bookstoreapp.model.entity.Account;
import com.company.bookstoreapp.repository.AccountRepository;
import com.company.bookstoreapp.service.AccountService;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.io.UncheckedIOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final AccountRepository accountRepository;
    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        Account account = accountRepository.findById(id)
                .orElseThrow(()-> new AuthenticationException());

        return  new User(account.getId(), "", List.of(new SimpleGrantedAuthority(account.getRole().getRoleName())));
    }
}
