package com.swiftlend.agiospring.domain.application.service.impl;

import com.swiftlend.agiospring.domain.application.dto.InstallmentDTO;
import com.swiftlend.agiospring.domain.application.dto.LoanDTO;
import com.swiftlend.agiospring.domain.application.model.Contact;
import com.swiftlend.agiospring.domain.application.model.Loan;
import com.swiftlend.agiospring.domain.application.repository.LoanRepository;
import com.swiftlend.agiospring.domain.application.service.facade.ContactService;
import com.swiftlend.agiospring.domain.application.service.facade.InstallmentService;
import com.swiftlend.agiospring.domain.application.service.facade.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@RequiredArgsConstructor
@Service
public class LoanServiceImpl implements LoanService {

    private final InstallmentService installmentService;
    private final LoanRepository loanRepository;
    private final ContactService contactService;


    @Override
    public List<LoanDTO> findAll() {
        List<Loan> loans = loanRepository.findAll();
        return loans.stream().map(LoanDTO::new).toList();
    }

    @Override
    public LoanDTO findById(Long id) {
        Loan loan = loanRepository.findById(id).orElse(null);
        if (loan != null) {
            return new LoanDTO(loan);
        }
        return null;
    }

    @Override
    public LoanDTO create(Long id, LoanDTO loanDTO) {
        Contact contact = contactService.findContactByID(id);
        Loan loan = new Loan();
        loan.setLoan_date(loanDTO.getLoan_date());
        loan.setAmount(loanDTO.getAmount());
        loan.setTotalInstallments(loanDTO.getTotalInstallments());
        loan.setOwner(contact);
        loan.setLastUpdate(LocalDateTime.now(ZoneOffset.ofHours(-3)));
        loan.setInstallmentInterval(loanDTO.getInstallmentInterval());
        loanRepository.save(loan);
        installmentService.creatEachInstallment(loan);
        return new LoanDTO(loan);
    }

    @Override
    public void delete(Long id) {
        Loan loan = loanRepository.findById(id).orElse(null);
        if (loan != null) {
            loanRepository.delete(loan);
        }
    }

    @Override
    public LoanDTO update(Long id , LoanDTO loanDTO) {
        Loan loan = loanRepository.findById(id).orElse(null);
        if (loan != null) {
            loan.setLoan_date(loanDTO.getLoan_date());
            loan.setAmount(loanDTO.getAmount());
            loan.setLastUpdate(LocalDateTime.now(ZoneOffset.ofHours(-3)));
            loan.setOwner(loanDTO.getOwner());
            loan.setTotalInstallments(loanDTO.getTotalInstallments());
            loanRepository.save(loan);
            return new LoanDTO(loan);
        }
        return null;
    }

    @Override
    public void setInstallments(List<InstallmentDTO> installmentsDTO) {

    }

    @Override
    public List<LoanDTO> findByContact(Long id) {
        List<Loan> loans = loanRepository.findByOwnerId(id);
        return loans.stream().map(LoanDTO::new).toList();

    }
}
