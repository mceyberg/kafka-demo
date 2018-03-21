package com.optum.clm.producer.employee.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.optum.clm.producer.employee.model.Employee;
import com.optum.clm.producer.organization.model.Organization;

@Repository
public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long> {
	Page<Employee> findByOrganization(Pageable pageable, Organization organization);
}
