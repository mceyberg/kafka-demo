package com.optum.clm.producer.hateoas;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;

import com.optum.clm.producer.controller.EmployeeController;
import com.optum.clm.producer.controller.OrganizationController;
import com.optum.clm.producer.model.Employee;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class EmployeeResource extends Resource<Employee> {

	public EmployeeResource(Employee employee) {
		super(employee);

		add(ControllerLinkBuilder.linkTo(methodOn(EmployeeController.class).findEmployeeById(employee.getId())).withSelfRel());
		add(ControllerLinkBuilder.linkTo(methodOn(OrganizationController.class).findOrganizationById(employee.getOrganization().getId())).withRel("organization"));
	}
}
