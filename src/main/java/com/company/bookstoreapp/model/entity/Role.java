package com.company.bookstoreapp.model.entity;

import com.company.bookstoreapp.model.entity.generator.IdGenerator;
import com.company.bookstoreapp.model.enums.Profession;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(generator = "id-generator")
    @GenericGenerator(name = "id-generator",type= IdGenerator.class)
    String id;
    @Column(name = "name")
    String roleName;

}
