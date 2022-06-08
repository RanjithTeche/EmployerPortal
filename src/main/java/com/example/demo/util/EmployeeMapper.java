package com.example.demo.util;

import com.example.demo.entity.Employee;
import com.example.demo.model.EmployeeVO;

public class EmployeeMapper {

	public static Employee mapToEmployeeEntity(EmployeeVO employeeVO) {

		if (employeeVO == null) {
			return null;
		}
		Employee employee = new Employee(employeeVO.getId(), employeeVO.getName(), employeeVO.getEmployeeNumber());
		employee.setAge(employeeVO.getAge());
		employee.setDateOfHire(DateUtils.parse(employeeVO.getDateOfHire()));
		employee.setLocation(employeeVO.getLocation());
		employee.setDepartment(employeeVO.getDepartment());
		employee.setSalary(employeeVO.getSalary());
		return employee;

	}

	public static EmployeeVO mapToEmployeeVO(Employee employee) {

		if (employee == null) {
			return null;
		}
		EmployeeVO employeeVO = new EmployeeVO(employee.getId(), employee.getName(), employee.getEmployeeNumber());
		employeeVO.setAge(employee.getAge());
		employeeVO.setDateOfHire(DateUtils.format(employee.getDateOfHire()));
		employeeVO.setLocation(employee.getLocation());
		employeeVO.setDepartment(employee.getDepartment());
		employeeVO.setSalary(employee.getSalary());
		return employeeVO;

	}

}
