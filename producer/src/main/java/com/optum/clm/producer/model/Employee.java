package com.optum.clm.producer.model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Employee extends Model {

	@NotNull
	private String firstName;

	@NotNull
	private String lastName;

	@OneToOne
	private Address address;
}
