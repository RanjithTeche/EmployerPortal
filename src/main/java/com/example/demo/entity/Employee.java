package com.example.demo.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "EMPLOYEE_RECORDS")
public class Employee {

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "NAME")
	private String name;
	@Column(name = "AGE")
	private int age;
	@Column(name = "LOCATION")
	private String location;
	@Column(name = "EMPLOYEE_NUMBER")
	private String employeeNumber;

	@Column(name = "HIREDATE")
	@Temporal(TemporalType.DATE)
	private Date dateOfHire;
	@Column(name = "SAL")
	private BigDecimal salary;
	@Column(name = "DEPTID")
	private String department;

	public Employee() {
		super();
	}

	// without id , name , epmoyeeNumber : employee dosen't exist so we need force
	public Employee(Long id, String name, String employeeNumber) {
		super();
		this.id = id;
		this.name = name;
		this.employeeNumber = employeeNumber;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getEmployeeNumber() {
		return employeeNumber;
	}

	public void setEmployeeNumber(String employeeNumber) {
		this.employeeNumber = employeeNumber;
	}

	public Date getDateOfHire() {
		return dateOfHire;
	}

	public void setDateOfHire(Date dateOfHire) {
		this.dateOfHire = dateOfHire;
	}

	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

}
