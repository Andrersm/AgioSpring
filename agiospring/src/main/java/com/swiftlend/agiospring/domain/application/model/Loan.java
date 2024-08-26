package com.swiftlend.agiospring.domain.application.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    private Float amount;
    private Date loan_date;
    private Integer total_installments;

    @ManyToOne(cascade = CascadeType.ALL)
    private Contact owner;



}
