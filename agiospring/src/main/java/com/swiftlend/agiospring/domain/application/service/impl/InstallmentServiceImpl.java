package com.swiftlend.agiospring.domain.application.service.impl;

import com.swiftlend.agiospring.domain.application.dto.InstallmentDTO;
import com.swiftlend.agiospring.domain.application.model.Installment;
import com.swiftlend.agiospring.domain.application.model.Loan;
import com.swiftlend.agiospring.domain.application.repository.InstallmentRepository;
import com.swiftlend.agiospring.domain.application.repository.LoanRepository;
import com.swiftlend.agiospring.domain.application.service.facade.InstallmentService;
import com.swiftlend.agiospring.domain.security.model.User;
import com.swiftlend.agiospring.domain.security.repository.UserRepository;
import com.swiftlend.agiospring.domain.security.service.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InstallmentServiceImpl implements InstallmentService {

    private final HttpServletRequest request;
    private final TokenService tokenService;
    private final InstallmentRepository installmentRepository;
    private final  LoanRepository loanRepository;
    private final UserRepository userRepository;

    @Override
    public List<InstallmentDTO> findAll() {
        String authorizationHeader = request.getHeader("Authorization");
        String token = authorizationHeader.substring(7);
        String username = tokenService.validateToken(token);
        User user = userRepository.findByUsername(username).orElse(null);
        List<Installment> installments = installmentRepository.findAllByUserOwner(user);
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
        Float amountInstallment = loan.getAmount() / loan.getTotalInstallments();
        String authorizationHeader = request.getHeader("Authorization");
        String token = authorizationHeader.substring(7);
        String username = tokenService.validateToken(token);
        User user = userRepository.findByUsername(username).orElse(null);
        for (int i = 1; i <= loan.getTotalInstallments(); i++) {
            Installment installment = new Installment();
            installment.setLastUpdate(LocalDateTime.now(ZoneOffset.ofHours(-3)));
            installment.setOwner(loan.getOwner().getFirstName());
            createOneinstallment(loan, amountInstallment, user, i, installment, installmentRepository);
        }
    }



    @Override
    public void pay(Long id) {
        Installment installment = installmentRepository.findById(id).orElse(null);
        if (installment != null) {
            installment.setIsPaid(!installment.getIsPaid());
            installmentRepository.save(installment);
        }
    }

    public static void createOneinstallment(Loan loan, Float amountInstallment, User user, long i, Installment installment, InstallmentRepository installmentRepository) {
        installment.setUserOwner(user);
        installment.setLoan(loan);
        installment.setAmount(amountInstallment);
        installment.setIsPaid(false);
        LocalDateTime loand = loan.getLoan_date();
        LocalDateTime dueDate = loand.plusDays(i * loan.getInstallmentInterval());
        installment.setDueDate(dueDate);
        installmentRepository.save(installment);
    }


}
