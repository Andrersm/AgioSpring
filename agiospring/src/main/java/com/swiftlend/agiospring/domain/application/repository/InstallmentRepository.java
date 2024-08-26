package com.swiftlend.agiospring.domain.application.repository;

import com.swiftlend.agiospring.domain.application.model.Installment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstallmentRepository extends JpaRepository<Installment, Long> {
}
