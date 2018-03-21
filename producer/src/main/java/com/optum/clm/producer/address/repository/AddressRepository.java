package com.optum.clm.producer.address.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.optum.clm.producer.address.model.Address;

@Repository
public interface AddressRepository extends PagingAndSortingRepository<Address, Long> {

}
