package com.optum.clm.producer.address.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.optum.clm.producer.address.model.Address;
import com.optum.clm.producer.address.service.AddressService;
import com.optum.clm.producer.address.model.AddressResource;

@RestController
@RequestMapping("/addresses")
public class AddressController {


	@Autowired
	private AddressService addressService;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public PagedResources<Resource<AddressResource>> findAllAddresses(Pageable pageable,
																	  PagedResourcesAssembler<AddressResource> assembler) {

		Page<Address> page = addressService.findAll(pageable);

		return assembler.toResource(page.map(AddressResource::new));
	}

	@GetMapping(value = "/{addressId}",
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public AddressResource findAddressById(@PathVariable Long addressId) {
		return new AddressResource(addressService.findById(addressId));
	}

	@PutMapping(value = "/{addressId}",
			consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public void updateAddress(@PathVariable Long addressId,
							  @RequestBody @Valid Address updatedAddress) {

		Address address = addressService.findById(addressId);

		address.setStreetAddress1(updatedAddress.getStreetAddress1());
		address.setStreetAddress2(updatedAddress.getStreetAddress2());
		address.setCity(updatedAddress.getCity());
		address.setState(updatedAddress.getState());
		address.setCountry(updatedAddress.getCountry());

		addressService.save(address);
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public void createAddress(@RequestBody @Valid Address address) {
		addressService.save(address);
	}

	@DeleteMapping("/{addressId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteAddress(@PathVariable Long addressId) {

		Address address = addressService.findById(addressId);
		addressService.delete(address);
	}
}
