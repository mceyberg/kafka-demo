package com.optum.clm.producer.organization.model;

import org.springframework.hateoas.Resource;

import com.optum.clm.producer.address.controller.AddressController;
import com.optum.clm.producer.employee.controller.EmployeeController;
import com.optum.clm.producer.organization.controller.OrganizationController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class OrganizationResource extends Resource<Organization> {

	public OrganizationResource(Organization organization) {
		super(organization);
		add(linkTo(methodOn(OrganizationController.class).findOrganizationById(organization.getId())).withSelfRel());
		if (organization.getAddress() != null) {
			add(linkTo(methodOn(AddressController.class).findAddressById(organization.getAddress().getId())).withRel("address"));
		}
		if (organization.getCeo() != null) {
			add(linkTo(methodOn(EmployeeController.class).findEmployeeById(organization.getCeo().getId())).withRel("ceo"));
		}
		add(linkTo(methodOn(OrganizationController.class).findEmployeesById(organization.getId(), null, null)).withRel("employees"));
	}
}
