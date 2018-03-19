package com.optum.clm.consumer.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Address extends Model {

	private String streetAddress1;

	private String streetAddress2;

	private String city;

	private String state;

	private String zipCode;

	private String country;
}
