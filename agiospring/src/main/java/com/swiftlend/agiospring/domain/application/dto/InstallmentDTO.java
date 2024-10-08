package com.swiftlend.agiospring.domain.application.dto;

import com.swiftlend.agiospring.domain.application.model.Installment;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class InstallmentDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private Float amount;
    private LocalDateTime dueDate;
    private Boolean isPaid;
    private LocalDateTime lastUpdate;

    public InstallmentDTO(Installment installment) {
        this.id = installment.getId();
        this.amount = installment.getAmount();
        this.dueDate = installment.getDueDate();
        this.isPaid = installment.getIsPaid();
        this.lastUpdate = installment.getLastUpdate();
    }
}
