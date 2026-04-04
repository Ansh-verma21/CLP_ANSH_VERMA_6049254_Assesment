package com.example.MVCAssessment.DTO;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

public class EmpDTO {

    private Integer empId;

    @NotBlank(message = "Employee name is required")
    @Size(min = 3, max = 25, message = "Name must be between 3 and 25 characters")
    private String empName;

    @Min(value = 1000, message = "Salary must be less than 1000")
    @Max(value = 500000, message = "Salary must not be more than 500000")
    private Double empSal;

    @FutureOrPresent(message = "Date of joining must be current or future date")
    private LocalDate empDoj;

    private String deptName;

	public Integer getEmpId() {
		return empId;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public Double getEmpSal() {
		return empSal;
	}

	public void setEmpSal(Double empSal) {
		this.empSal = empSal;
	}

	public LocalDate getEmpDoj() {
		return empDoj;
	}

	public void setEmpDoj(LocalDate empDoj) {
		this.empDoj = empDoj;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

    
}