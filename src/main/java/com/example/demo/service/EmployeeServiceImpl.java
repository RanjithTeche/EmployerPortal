package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Employee;
import com.example.demo.exception.EmployeeNotFound;
import com.example.demo.model.EmployeeVO;
import com.example.demo.repository.EmployeeDAO;
import com.example.demo.util.EmployeeMapper;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeDAO employeeDAO;

	@Autowired
	public EmployeeServiceImpl(EmployeeDAO employeeDAO) {
		super();
		this.employeeDAO = employeeDAO;
	}

	@Override
	public Long createEmployee(EmployeeVO employeeRequest) {
		Employee employee = employeeDAO.save(EmployeeMapper.mapToEmployeeEntity(employeeRequest));
		return employee.getId();
	}

	@Override
	public List<EmployeeVO> createEmployees(List<EmployeeVO> employeesRequest) {
		// TODO Auto-generated method stub
		List<Employee> employees = employeesRequest.stream().map(EmployeeMapper::mapToEmployeeEntity)
				.collect(Collectors.toList());

		List<Employee> entities = employeeDAO.saveAll(employees);
		return entities.stream().map(EmployeeMapper::mapToEmployeeVO).collect(Collectors.toList());
	}

	@Override
	public EmployeeVO findEmployee(Long id) {
		Optional<Employee> employee = employeeDAO.findById(id);
		if (employee.isPresent()) {
			return EmployeeMapper.mapToEmployeeVO(employee.get());
		} else {
			throw new EmployeeNotFound("Given employee not present :" + id);
		}
	}

	@Override
	public void updateEmployeeWithEmpNo(String empNo, EmployeeVO request) {
		Optional<Employee> employee = employeeDAO.findByEmployeeNumber(empNo);
		if (employee.isPresent()) {
			employeeDAO.save(EmployeeMapper.mapToEmployeeEntity(request));
		} else {
			throw new EmployeeNotFound("Given employee not present :" + empNo);
		}
	}

	@Override
	public void removeEmployee(Long id) {
		employeeDAO.deleteById(id);
	}

}
