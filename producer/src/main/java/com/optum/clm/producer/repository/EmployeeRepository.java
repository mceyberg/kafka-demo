package com.optum.clm.producer.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.optum.clm.producer.model.Employee;
import com.optum.clm.producer.model.Organization;

@Repository
public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long> {
	Page<Employee> findByOrganization(Pageable pageable, Organization organization);
}
