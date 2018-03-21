package com.optum.clm.producer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.optum.clm.producer.exception.NotFoundException;
import com.optum.clm.producer.model.Employee;
import com.optum.clm.producer.model.Organization;
import com.optum.clm.producer.repository.EmployeeRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService {

	private static final String TOPIC_EMPLOYEE_CHANGED = "employee-changed";
	private static final String TOPIC_EMPLOYEE_DELETED = "employee-deleted";

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private NotificationService notificationService;

	@Override
	public Employee findById(Long employeeId) {
		return employeeRepository.findById(employeeId)
				.orElseThrow(() -> new NotFoundException("Employee not found for id %d", employeeId));
	}

	@Override
	public Iterable<Employee> findAll() {
		return employeeRepository.findAll();
	}

	@Override
	public Page<Employee> findAll(Pageable pageable) {
		return employeeRepository.findAll(pageable);
	}

	@Override
	public void save(Employee employee) {

		employeeRepository.save(employee);
		notificationService.sendMessage(TOPIC_EMPLOYEE_CHANGED, "id", employee.getId().toString());
	}

	@Override
	public void delete(Employee employee) {

		employeeRepository.delete(employee);
		notificationService.sendMessage(TOPIC_EMPLOYEE_DELETED, "id", employee.getId().toString());
	}

	@Override
	public Page<Employee> findByOrganization(Pageable pageable, Organization organization) {
		return employeeRepository.findByOrganization(pageable, organization);
	}
}
