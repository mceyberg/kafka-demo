package com.optum.clm.producer.employee.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.optum.clm.producer.core.model.Model;
import com.optum.clm.producer.organization.model.Organization;

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

	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "organization_id")
	private Organization organization;
}
