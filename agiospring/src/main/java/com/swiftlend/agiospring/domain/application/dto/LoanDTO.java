package com.swiftlend.agiospring.domain.application.dto;

import com.swiftlend.agiospring.domain.application.model.Contact;
import com.swiftlend.agiospring.domain.application.model.Loan;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class LoanDTO {

    private Long id;
    private Float amount;
    private LocalDateTime loan_date;
    private Integer totalInstallments;
    private LocalDateTime lastUpdate;
    private Contact owner;
    private Integer installmentInterval;

    public LoanDTO(Loan loan) {
        this.id = loan.getId();
        this.amount = loan.getAmount();
        this.loan_date = loan.getLoan_date();
        this.totalInstallments = loan.getTotalInstallments();
        this.lastUpdate = loan.getLastUpdate();
        this.owner = loan.getOwner();
        this.installmentInterval = loan.getInstallmentInterval();
    }

}
