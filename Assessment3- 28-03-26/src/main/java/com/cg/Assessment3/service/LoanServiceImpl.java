package com.cg.Assessment3.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.Assessment3.entity.Loan;
import com.cg.Assessment3.exception.DuplicateLoanApplicationException;
import com.cg.Assessment3.exception.InvalidLoanAmountException;
import com.cg.Assessment3.exception.LoanNotFoundException;
import com.cg.Assessment3.repository.LoanRepository;

@Service
public class LoanServiceImpl implements LoanService {

    @Autowired
    private LoanRepository repo;

    @Override
    public Loan applyLoan(Loan loan) {

        if (loan.getLoanAmount() <= 0 || loan.getLoanAmount() > 5000000) {
            throw new InvalidLoanAmountException("Loan amount must be between 1 and 5000000");
        }

        List<Loan> pending = repo.findPendingByApplicantName(loan.getApplicantName());
        if (!pending.isEmpty()) {
            throw new DuplicateLoanApplicationException(
                "Applicant " + loan.getApplicantName() + " already has a pending  application"
            );
        }

        loan.setStatus("PENDING");
        return repo.save(loan);
    }

    @Override
    public List<Loan> fetchAll() {
        return repo.findAll();
    }

    @Override
    public Loan getLoanById(Long id) {
        return repo.findById(id)
            .orElseThrow(() -> new LoanNotFoundException("Loan not found with id " + id));
    }

    @Override
    public Loan updateStatusById(Long id, String status) {
        Loan loan = repo.findById(id)
            .orElseThrow(() -> new LoanNotFoundException("Loan not found with id " + id));

        loan.setStatus(status);
        return repo.save(loan);
    }
}
