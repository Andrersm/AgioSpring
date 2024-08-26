package com.swiftlend.agiospring.domain.application.repository;

import com.swiftlend.agiospring.domain.application.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loan, Long> {
}
