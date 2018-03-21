package com.optum.clm.producer.address.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.optum.clm.producer.address.model.Address;

public interface AddressService {

	Address findById(Long addressId);

	Iterable<Address> findAll();

	Page<Address> findAll(Pageable pageable);

	void save(Address address);

	void delete(Address address);
}
