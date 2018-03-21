package com.optum.clm.consumer.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Employee extends Model {

	private String firstName;

	private String lastName;
}
