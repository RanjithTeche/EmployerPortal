package com.example.demo.service;

import java.util.List;

import com.example.demo.model.EmployeeVO;

public interface EmployeeService {

	Long createEmployee(EmployeeVO employeeRequest);

	List<EmployeeVO> createEmployees(List<EmployeeVO> employeesRequest);

	EmployeeVO findEmployee(Long id);

	void updateEmployeeWithEmpNo(String empNo, EmployeeVO request);

	void removeEmployee(Long id);
}
