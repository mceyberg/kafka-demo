package com.optum.clm.producer.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
import com.optum.clm.producer.model.Employee;
import com.optum.clm.producer.service.AddressService;
import com.optum.clm.producer.service.EmployeeService;

import lombok.Data;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private AddressService addressService;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Page<Employee> findAllEmployees(@RequestParam(defaultValue = "0") Integer page,
										   @RequestParam(defaultValue = "20") Integer size,
										   @RequestParam(defaultValue = "ASC") Sort.Direction direction,
										   @RequestParam(defaultValue = "id") String... sortBy) {

		Pageable pageable = PageRequest.of(page, size, direction, sortBy);

		return employeeService.findAll(pageable);
	}

	@GetMapping(value = "/{employeeId}",
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Employee findEmployeeById(@PathVariable Long employeeId) {
		return employeeService.findById(employeeId);
	}

	@PutMapping(value = "/{employeeId}",
			consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public void updateEmployee(@PathVariable Long employeeId,
							   @RequestBody @Valid Employee updatedEmployee) {

		Employee employee = employeeService.findById(employeeId);

		employee.setFirstName(updatedEmployee.getFirstName());
		employee.setLastName(updatedEmployee.getLastName());
		employee.setAddress(updatedEmployee.getAddress());

		employeeService.save(employee);
	}

	@PutMapping(value = "/{employeeId}/addresses",
			consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public void changeAddress(@PathVariable Long employeeId,
							  @RequestBody ChangeAddressDTO addressDTO) {

		Employee employee = employeeService.findById(employeeId);

		Address address = addressService.findById(addressDTO.getAddressId());

		employee.setAddress(address);

		employeeService.save(employee);
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public void createEmployee(@RequestBody @Valid Employee employee) {
		employeeService.save(employee);
	}

	@DeleteMapping("/{employeeId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteEmployee(@PathVariable Long employeeId) {

		Employee employee = employeeService.findById(employeeId);
		employeeService.delete(employee);
	}

	@Data
	private static class ChangeAddressDTO {
		private Long addressId;
	}
}
