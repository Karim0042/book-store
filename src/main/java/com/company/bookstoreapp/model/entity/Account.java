package com.company.bookstoreapp.model.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
@AllArgsConstructor
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "username", unique = true)
    String email;

    @Column(name = "password")
    String password;
   @OneToOne
   @JoinColumn(name = "role_id")
    Role role;
}
