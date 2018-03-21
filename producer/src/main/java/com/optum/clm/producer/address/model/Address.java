package com.optum.clm.producer.address.model;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import com.optum.clm.producer.core.model.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Address extends Model {

	@NotNull
	private String streetAddress1;

	private String streetAddress2;

	@NotNull
	private String city;

	@NotNull
	private String state;

	private String zipCode;

	@NotNull
	private String country;
}
