package com.optum.clm.producer.organization.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.optum.clm.producer.organization.model.Organization;

@Repository
public interface OrganizationRepository extends PagingAndSortingRepository<Organization, Long> {
}
