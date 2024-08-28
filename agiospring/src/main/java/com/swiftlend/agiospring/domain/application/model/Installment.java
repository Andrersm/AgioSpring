package com.swiftlend.agiospring.domain.application.model;

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

    @ManyToOne
    @JoinColumn(name = "loan_id")
    private Loan loan;



}