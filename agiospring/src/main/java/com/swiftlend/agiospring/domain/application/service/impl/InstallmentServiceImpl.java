package com.swiftlend.agiospring.domain.application.service.impl;

import com.swiftlend.agiospring.domain.application.dto.InstallmentDTO;
import com.swiftlend.agiospring.domain.application.model.Installment;
import com.swiftlend.agiospring.domain.application.model.Loan;
import com.swiftlend.agiospring.domain.application.repository.InstallmentRepository;
import com.swiftlend.agiospring.domain.application.service.facade.InstallmentService;
import com.swiftlend.agiospring.domain.security.model.User;
import com.swiftlend.agiospring.domain.security.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InstallmentServiceImpl implements InstallmentService {

    private static final Logger logger = LoggerFactory.getLogger(InstallmentServiceImpl.class);


    private final TokenService tokenService;
    private final InstallmentRepository installmentRepository;

    @Override
    @Cacheable(value = "installments", keyGenerator = "jwtKeyGenerator")
    public List<InstallmentDTO> findAll() {
        logger.info("Fetching installments from database...");
        User user = tokenService.getUserFromToken();
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
        User user = tokenService.getUserFromToken();
        for (int i = 1; i <= loan.getTotalInstallments(); i++) {
            Installment installment = new Installment();
            installment.setLastUpdate(LocalDateTime.now(ZoneOffset.ofHours(-3)));
            installment.setOwner(loan.getOwner().getFirstName());
            createOneinstallment(loan, amountInstallment, user, i, installment, installmentRepository);
        }
    }



    @Override
    @CacheEvict(value = "installments", key = "@jwtKeyGenerator.generate(target, method, #id)")
    public void pay(Long id) {
        User user = tokenService.getUserFromToken();
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
