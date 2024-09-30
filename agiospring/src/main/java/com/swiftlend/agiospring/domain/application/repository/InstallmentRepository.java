package com.swiftlend.agiospring.domain.application.repository;

import com.swiftlend.agiospring.domain.application.model.Installment;
import com.swiftlend.agiospring.domain.security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InstallmentRepository extends JpaRepository<Installment, Long> {
    List<Installment> findAllByUserOwner(User user);
    List<Installment> findAllByLoanId(Long id);
}
