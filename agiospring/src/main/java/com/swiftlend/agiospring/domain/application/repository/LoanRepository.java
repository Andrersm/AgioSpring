package com.swiftlend.agiospring.domain.application.repository;

import com.swiftlend.agiospring.domain.application.model.Loan;
import com.swiftlend.agiospring.domain.security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, Long> {

    List<Loan> findByOwnerId(long id);
    List<Loan> findAllByUserOwner(User user);

}
