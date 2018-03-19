package com.optum.clm.producer.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.optum.clm.producer.model.Address;
import com.optum.clm.producer.service.AddressService;

@RestController
@RequestMapping("/addresses")
public class AddressController {


	@Autowired
	private AddressService addressService;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Page<Address> findAllAddresses(@RequestParam(defaultValue = "0") Integer page,
										  @RequestParam(defaultValue = "20") Integer size,
										  @RequestParam(defaultValue = "ASC") Sort.Direction direction,
										  @RequestParam(defaultValue = "id") String... sortBy) {

		PageRequest pageRequest = PageRequest.of(page, size, direction, sortBy);
		return addressService.findAll(pageRequest);
	}

	@GetMapping(value = "/{addressId}",
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Address findAddressById(@PathVariable Long addressId) {
		return addressService.findById(addressId);
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
