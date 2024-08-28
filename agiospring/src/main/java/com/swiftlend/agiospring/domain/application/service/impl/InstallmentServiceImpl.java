package com.swiftlend.agiospring.domain.application.service.impl;

import com.swiftlend.agiospring.domain.application.dto.InstallmentDTO;
import com.swiftlend.agiospring.domain.application.dto.LoanDTO;
import com.swiftlend.agiospring.domain.application.model.Installment;
import com.swiftlend.agiospring.domain.application.model.Loan;
import com.swiftlend.agiospring.domain.application.repository.InstallmentRepository;
import com.swiftlend.agiospring.domain.application.repository.LoanRepository;
import com.swiftlend.agiospring.domain.application.service.facade.InstallmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class InstallmentServiceImpl implements InstallmentService {


    @Autowired
    private InstallmentRepository installmentRepository;
    @Autowired
    private LoanRepository loanRepository;

    @Override
    public List<InstallmentDTO> findAll() {
        List<Installment> installments = installmentRepository.findAll();
        return installments.stream().map(InstallmentDTO::new).toList();
    }

    @Override
    public InstallmentDTO findById(Long id) {
        Installment installment = installmentRepository.findById(id).orElse(null);
        if (installment != null) {
            return new InstallmentDTO(installment);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        Installment installment = installmentRepository.findById(id).orElse(null);
        if (installment != null) {
            installmentRepository.delete(installment);
        }
    }


    @Override
    public List<InstallmentDTO> findByLoan(Long id) {
        List<Installment> installments = installmentRepository.findAllByLoanId(id);
        return installments.stream().map(InstallmentDTO::new).toList();
    }

    @Override
    public void creatEachInstallment(Loan loan) {
        Float amountInstallment = loan.getAmount() / loan.getTotal_installments();
        Loan newLoan = loanRepository.findById(loan.getId()).orElse(null);
        for (int i = 0; i < loan.getTotal_installments(); i++) {
            Installment installment = new Installment();
            installment.setLoan(loan);
            installment.setAmount(amountInstallment);
            installment.setIsPaid(false);
            LocalDateTime loand = loan.getLoan_date();
            LocalDateTime dueDate = loand.plusDays((long) i * loan.getInstallmentInterval());
            installment.setDueDate(dueDate);
            installmentRepository.save(installment);
        }
    }



}
