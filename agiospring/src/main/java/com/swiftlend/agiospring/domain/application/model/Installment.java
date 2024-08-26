package com.swiftlend.agiospring.domain.application.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Installment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    private Float amount;
    private Date dueDate;
    private Boolean isPaid;

    @ManyToOne
    private Loan loan;

}
