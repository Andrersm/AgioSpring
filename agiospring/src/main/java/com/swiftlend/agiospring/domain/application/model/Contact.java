package com.swiftlend.agiospring.domain.application.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.swiftlend.agiospring.domain.security.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String cpf;
    private String lastName;
    private String email;
    private String phone;
    private LocalDateTime lastUpdate;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User ownerUser;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Loan> loans = new ArrayList<>();


}
