package com.optum.clm.producer.hateoas;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;

import com.optum.clm.producer.controller.AddressController;
import com.optum.clm.producer.controller.EmployeeController;
import com.optum.clm.producer.controller.OrganizationController;
import com.optum.clm.producer.model.Organization;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class OrganizationResource extends Resource<Organization> {

	public OrganizationResource(Organization organization) {
		super(organization);
		add(ControllerLinkBuilder.linkTo(methodOn(OrganizationController.class).findOrganizationById(organization.getId())).withSelfRel());
		add(ControllerLinkBuilder.linkTo(methodOn(AddressController.class).findAddressById(organization.getAddress().getId())).withRel("address"));
		add(ControllerLinkBuilder.linkTo(methodOn(EmployeeController.class).findEmployeeById(organization.getCeo().getId())).withRel("ceo"));
		add(linkTo(methodOn(OrganizationController.class).findEmployeesById(organization.getId(), null, null)).withRel("employees"));
	}
}
