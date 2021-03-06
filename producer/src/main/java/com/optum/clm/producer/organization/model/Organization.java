package com.optum.clm.producer.organization.model;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.optum.clm.producer.address.model.Address;
import com.optum.clm.producer.employee.model.Employee;
import com.optum.clm.producer.core.model.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Organization extends Model {

	@NotNull
	private String organizationName;

	@OneToOne
	@JsonManagedReference
	private Employee ceo;

	@NotNull
	@OneToOne
	private Address address;

	@JsonIgnore
	@OneToMany(mappedBy = "organization")
	private Set<Employee> employees;
}
