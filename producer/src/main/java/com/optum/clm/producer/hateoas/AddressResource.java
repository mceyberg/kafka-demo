package com.optum.clm.producer.hateoas;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;

import com.optum.clm.producer.controller.AddressController;
import com.optum.clm.producer.model.Address;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class AddressResource extends Resource<Address> {

	public AddressResource(Address address) {
		super(address);
		add(ControllerLinkBuilder.linkTo(methodOn(AddressController.class).findAddressById(address.getId())).withSelfRel());
	}
}
