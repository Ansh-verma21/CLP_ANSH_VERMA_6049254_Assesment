package com.cg.Assessment3.service;



import java.util.List;

import com.cg.Assessment3.entity.Loan;


public interface LoanService {

    Loan applyLoan(Loan loan);

    List<Loan> fetchAll();

    Loan getLoanById(Long id);

    Loan updateStatusById(Long id, String status);
}
