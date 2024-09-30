package com.swiftlend.agiospring.domain.security.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.swiftlend.agiospring.domain.application.model.Contact;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String username;
    private String password;
    private String email;

    @OneToMany(mappedBy = "ownerUser", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Contact> contatos;
}
