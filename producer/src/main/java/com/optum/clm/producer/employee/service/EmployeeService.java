package com.optum.clm.producer.employee.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.optum.clm.producer.employee.model.Employee;
import com.optum.clm.producer.organization.model.Organization;

public interface EmployeeService {

	Employee findById(Long employeeId);

	Iterable<Employee> findAll();

	Page<Employee> findAll(Pageable pageable);

	void save(Employee employee);

	void delete(Employee employee);

	Page<Employee> findByOrganization(Pageable pageable, Organization organization);
}
