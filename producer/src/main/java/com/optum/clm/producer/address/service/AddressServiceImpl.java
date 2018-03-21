package com.optum.clm.producer.address.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.optum.clm.producer.core.exception.NotFoundException;
import com.optum.clm.producer.address.model.Address;
import com.optum.clm.producer.address.repository.AddressRepository;
import com.optum.clm.producer.notification.service.NotificationService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AddressServiceImpl implements AddressService {

	private static final String TOPIC_ADDRESS_CHANGED = "address-changed";
	private static final String TOPIC_ADDRESS_DELETED = "address-deleted";


	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private NotificationService notificationService;

	@Override
	public Address findById(Long addressId) {
		return addressRepository.findById(addressId)
				.orElseThrow(() -> new NotFoundException("Address not found for id %d", addressId));
	}

	@Override
	public Iterable<Address> findAll() {
		return addressRepository.findAll();
	}

	@Override
	public Page<Address> findAll(Pageable pageable) {
		return addressRepository.findAll(pageable);
	}

	@Override
	public void save(Address address) {

		log.debug("Creating address, address: {}", address);
		addressRepository.save(address);
		notificationService.sendMessage(TOPIC_ADDRESS_CHANGED, "id", address.getId().toString());
	}

	@Override
	public void delete(Address address) {
		log.debug("Deleting address, addressId: {}", address.getId());
		addressRepository.delete(address);
		notificationService.sendMessage(TOPIC_ADDRESS_DELETED, "id", address.getId().toString());
	}
}
