package com.swiftlend.agiospring.domain.application.service.facade;

import com.swiftlend.agiospring.domain.application.dto.InstallmentDTO;
import com.swiftlend.agiospring.domain.application.dto.LoanDTO;

import java.util.List;

public interface LoanService {

    List<LoanDTO> findAll();
    LoanDTO findById(Long id);
    LoanDTO create(Long id, LoanDTO loanDTO);
    void delete(Long id);
    LoanDTO update(Long id, LoanDTO loanDTO);
    void setInstallments(List<InstallmentDTO> installmentsDTO);
    List<LoanDTO> findByContact(Long id);
}
