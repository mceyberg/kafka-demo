package com.optum.clm.producer.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.optum.clm.producer.model.Organization;

/**
 *
 */
public interface OrganizationService {

	Organization findById(Long organizationId);

	Iterable<Organization> findAll();

	Page<Organization> findAll(Pageable pageable);

	void save(Organization organization);

	void delete(Organization organization);
}
