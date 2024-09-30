package com.swiftlend.agiospring.domain.application.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
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
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Float amount;
    private LocalDateTime loan_date;
    private Integer totalInstallments;
    private LocalDateTime lastUpdate = LocalDateTime.now();
    private Integer installmentInterval = 0;

    @ManyToOne
    @JoinColumn(name = "contact_id")
    private Contact owner;

    @JsonIgnore
    @OneToMany(mappedBy = "loan", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Installment> installments = new ArrayList<>();

}