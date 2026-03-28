package com.cg.Assessment3.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cg.Assessment3.entity.Loan;



public interface LoanRepository extends JpaRepository<Loan, Long> {

    @Query("SELECT l FROM Loan l WHERE l.applicantName = :applicantName AND l.status = 'PENDING'")
    List<Loan> findPendingByApplicantName(String applicantName);
}
