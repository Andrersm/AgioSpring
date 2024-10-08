package com.swiftlend.agiospring.domain.application.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.swiftlend.agiospring.domain.security.model.User;
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
    private Integer installmentInterval;
    private Integer payedInstallments;
    private Boolean paid = false;

    @ManyToOne
    @JoinColumn(name = "user_owner_id", nullable = false)
    private User userOwner;
    @ManyToOne
    @JoinColumn(name = "contact_id")
    private Contact owner;

    @JsonIgnore
    @OneToMany(mappedBy = "loan", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Installment> installments = new ArrayList<>();

    public void payInstallments(Boolean payed) {
        payedInstallments += payed ? 1 : -1;
        paid = (payedInstallments == totalInstallments);
    }

}