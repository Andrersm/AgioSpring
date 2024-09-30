package com.swiftlend.agiospring.domain.application.model;

import com.swiftlend.agiospring.domain.security.model.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class Installment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Float amount;
    private LocalDateTime dueDate;
    private Boolean isPaid;
    private LocalDateTime lastUpdate;
    private String owner;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User userOwner;

    @ManyToOne
    @JoinColumn(name = "loan_id")
    private Loan loan;



}