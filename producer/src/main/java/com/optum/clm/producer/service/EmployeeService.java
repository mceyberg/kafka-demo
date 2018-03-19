package com.optum.clm.producer.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.optum.clm.producer.model.Employee;

public interface EmployeeService {

	Employee findById(Long employeeId);

	Iterable<Employee> findAll();

	Page<Employee> findAll(Pageable pageable);

	void save(Employee employee);

	void delete(Employee employee);
}
