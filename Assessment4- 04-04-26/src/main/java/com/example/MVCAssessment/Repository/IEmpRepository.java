package com.example.MVCAssessment.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.MVCAssessment.entities.Emp;

public interface IEmpRepository extends JpaRepository<Emp, Integer> {
}
