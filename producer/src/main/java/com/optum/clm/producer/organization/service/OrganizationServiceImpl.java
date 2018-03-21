package com.optum.clm.producer.organization.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.optum.clm.producer.core.exception.NotFoundException;
import com.optum.clm.producer.organization.model.Organization;
import com.optum.clm.producer.organization.repository.OrganizationRepository;
import com.optum.clm.producer.notification.service.NotificationService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OrganizationServiceImpl implements OrganizationService {

	private static final String TOPIC_ORGANIZATION_CHANGED = "organization-changed";
	private static final String TOPIC_ORGANIZATION_DELETED = "organization-deleted";

	@Autowired
	private OrganizationRepository organizationRepository;

	@Autowired
	private NotificationService notificationService;

	@Override
	public Organization findById(Long organizationId) {
		return organizationRepository.findById(organizationId).orElseThrow(() -> new NotFoundException("Organization not found: " + organizationId));
	}

	@Override
	public Iterable<Organization> findAll() {
		return organizationRepository.findAll();
	}

	@Override
	public Page<Organization> findAll(Pageable pageable) {
		return organizationRepository.findAll(pageable);
	}

	@Override
	public void save(Organization organization) {
		organizationRepository.save(organization);

		notificationService.sendMessage(TOPIC_ORGANIZATION_CHANGED, "id", organization.getId().toString());
	}

	@Override
	public void delete(Organization organization) {
		organizationRepository.delete(organization);

		notificationService.sendMessage(TOPIC_ORGANIZATION_DELETED, "id", organization.getId().toString());
	}
}
