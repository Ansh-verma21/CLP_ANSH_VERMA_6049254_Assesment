package com.example.MVCAssessment.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MVCAssessment.DTO.EmpDTO;
import com.example.MVCAssessment.Repository.IEmpRepository;
import com.example.MVCAssessment.entities.Emp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmpService {

    @Autowired
    private IEmpRepository repo;

    private EmpDTO toDTO(Emp emp) {
        EmpDTO dto = new EmpDTO();
        dto.setEmpId(emp.getEmpId());
        dto.setEmpName(emp.getEmpName());
        dto.setEmpSal(emp.getEmpSal());
        dto.setEmpDoj(emp.getEmpDoj());
        dto.setDeptName(emp.getDeptName());
        return dto;
    }



    public List<EmpDTO> getAllEmps() {
        List<Emp> list = repo.findAll();
        List<EmpDTO> dtoList = new ArrayList<>();
        for (Emp emp : list) {
            dtoList.add(toDTO(emp));
        }
        return dtoList;
    }

    public EmpDTO getEmpById(Integer id) {
        Optional<Emp> emp = repo.findById(id);
        if (emp.isPresent()) {
            return toDTO(emp.get());
        }
        return null;
    }

    public void updateEmp(Integer id, EmpDTO dto) {
        Optional<Emp> existing = repo.findById(id);
        if (existing.isPresent()) {
            Emp emp = existing.get();
            emp.setEmpName(dto.getEmpName());
            emp.setEmpSal(dto.getEmpSal());
            emp.setEmpDoj(dto.getEmpDoj());
            emp.setDeptName(dto.getDeptName());
            repo.save(emp);
        }
    }

    public void deleteEmp(Integer id) {
        repo.deleteById(id);
    }
}
