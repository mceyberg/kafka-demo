package com.optum.clm.producer.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.optum.clm.producer.model.Employee;
import com.optum.clm.producer.service.EmployeeService;
import com.optum.clm.producer.hateoas.EmployeeResource;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public PagedResources<Resource<EmployeeResource>> findAllEmployees(Pageable pageable,
																	   PagedResourcesAssembler<EmployeeResource> assembler) {

		Page<Employee> page = employeeService.findAll(pageable);
		return assembler.toResource(page.map(EmployeeResource::new));
	}

	@GetMapping(value = "/{employeeId}",
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public EmployeeResource findEmployeeById(@PathVariable Long employeeId) {
		return new EmployeeResource(employeeService.findById(employeeId));
	}

	@PutMapping(value = "/{employeeId}",
			consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public void updateEmployee(@PathVariable Long employeeId,
							   @RequestBody @Valid Employee updatedEmployee) {

		Employee employee = employeeService.findById(employeeId);

		employee.setFirstName(updatedEmployee.getFirstName());
		employee.setLastName(updatedEmployee.getLastName());

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
}
