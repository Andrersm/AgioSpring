package com.swiftlend.agiospring.domain.application.dto;

import com.swiftlend.agiospring.domain.application.model.Contact;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SimpleContactDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String cpf;
    private LocalDateTime lastUpdate;
    private String phone;

    public SimpleContactDTO(Contact contact) {
        this.id = contact.getId();
        this.firstName = contact.getFirstName();
        this.lastName = contact.getLastName();
        this.email = contact.getEmail();
        this.cpf = contact.getCpf();
        this.phone = contact.getPhone();
        this.lastUpdate = contact.getLastUpdate();
    }
}
