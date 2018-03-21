package com.optum.clm.producer.employee.model;

import org.springframework.hateoas.Resource;

import com.optum.clm.producer.employee.controller.EmployeeController;
import com.optum.clm.producer.organization.controller.OrganizationController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class EmployeeResource extends Resource<Employee> {

	public EmployeeResource(Employee employee) {
		super(employee);

		add(linkTo(methodOn(EmployeeController.class).findEmployeeById(employee.getId())).withSelfRel());
		if (employee.getOrganization() != null) {
			add(linkTo(methodOn(OrganizationController.class).findOrganizationById(employee.getOrganization().getId())).withRel("organization"));
		}
	}
}
