package com.optum.clm.producer.address.model;

import org.springframework.hateoas.Resource;

import com.optum.clm.producer.address.controller.AddressController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class AddressResource extends Resource<Address> {

	public AddressResource(Address address) {
		super(address);
		add(linkTo(methodOn(AddressController.class).findAddressById(address.getId())).withSelfRel());
	}
}
