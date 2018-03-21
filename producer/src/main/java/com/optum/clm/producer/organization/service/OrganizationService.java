package com.optum.clm.producer.organization.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.optum.clm.producer.organization.model.Organization;

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
