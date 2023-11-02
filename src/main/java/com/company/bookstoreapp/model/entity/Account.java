package com.company.bookstoreapp.model.entity;

import com.company.bookstoreapp.model.entity.generator.IdGenerator;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Builder
@FieldDefaults(level= AccessLevel.PRIVATE)
@AllArgsConstructor
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(generator = "id-generator")
    @GenericGenerator(name = "id-generator",type=IdGenerator.class)
    String id;

    @Column(name = "email", unique = true)
    String email;

    @Column(name = "password")
    String password;
   @ManyToOne
    Role role;
}
