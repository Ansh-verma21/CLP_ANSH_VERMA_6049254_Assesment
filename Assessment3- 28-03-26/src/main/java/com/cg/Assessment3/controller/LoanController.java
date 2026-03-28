package com.cg.Assessment3.controller;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.Assessment3.entity.Loan;
import com.cg.Assessment3.service.LoanService;

@RestController
@RequestMapping("/loans")
public class LoanController {

    @Autowired
    private LoanService s;

    @PostMapping
    public ResponseEntity<?> applyLoan(@RequestBody Loan l) {
        return new ResponseEntity<>(s.applyLoan(l), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAllLoans() {
        List<Loan> list = s.fetchAll();
        if (list.isEmpty()) {
            return new ResponseEntity<>(list, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getLoanById(@PathVariable Long id) {
        return new ResponseEntity<>(s.getLoanById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<?> updateStatus(@PathVariable Long id, Loan body) {
        String status = body.getStatus();
        return new ResponseEntity<>(s.updateStatusById(id, status), HttpStatus.OK);
    }
}
