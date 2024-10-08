package com.swiftlend.agiospring.domain.application.dto;

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
    private Long loanID;
    private Integer installmentInterval;
    private Boolean paid;

    public LoanDTO(Loan loan) {
        this.id = loan.getId();
        this.amount = loan.getAmount();
        this.loan_date = loan.getLoan_date();
        this.totalInstallments = loan.getTotalInstallments();
        this.lastUpdate = loan.getLastUpdate();
        this.loanID = loan.getId();
        this.installmentInterval = loan.getInstallmentInterval();
        this.paid = loan.getPaid();
    }

}
