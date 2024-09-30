package com.swiftlend.agiospring.domain.application.dto;

import com.swiftlend.agiospring.domain.application.model.Installment;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
public class InstallmentDTO {

    private Long id;
    private Float amount;
    private LocalDateTime dueDate;
    private Boolean isPaid;
    private LocalDateTime lastUpdate;
    private String owner;

    public InstallmentDTO(Installment installment) {
        this.id = installment.getId();
        this.amount = installment.getAmount();
        this.dueDate = installment.getDueDate();
        this.isPaid = installment.getIsPaid();
        this.lastUpdate = installment.getLastUpdate();
        this.owner = installment.getOwner();
    }
}
