package com.company.bookstoreapp.model.entity;

import com.company.bookstoreapp.model.enums.Profession;
import jakarta.persistence.*;

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "role_name")
    @Enumerated(value = EnumType.STRING)
    Profession roleName;

}
