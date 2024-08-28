package com.swiftlend.agiospring.domain.application.dto;

import com.swiftlend.agiospring.domain.application.model.Contact;
import com.swiftlend.agiospring.domain.application.model.Loan;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ContactDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String cpf;
    private LocalDateTime lastUpdate;
    private String phone;
    private List<Loan> loans = new ArrayList<>();

    public ContactDTO(Contact contact) {
        this.id = contact.getId();
        this.firstName = contact.getFirstName();
        this.lastName = contact.getLastName();
        this.email = contact.getEmail();
        this.cpf = contact.getCpf();
        this.phone = contact.getPhone();
        this.lastUpdate = contact.getLastUpdate();
        this.loans = contact.getLoans();
    }


}
