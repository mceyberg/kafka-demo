package com.optum.clm.producer.organization.controller;

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

import com.optum.clm.producer.address.model.Address;
import com.optum.clm.producer.employee.model.Employee;
import com.optum.clm.producer.organization.model.Organization;
import com.optum.clm.producer.address.service.AddressService;
import com.optum.clm.producer.employee.service.EmployeeService;
import com.optum.clm.producer.organization.service.OrganizationService;
import com.optum.clm.producer.employee.model.EmployeeResource;
import com.optum.clm.producer.organization.model.OrganizationResource;

import lombok.Data;

@RestController
@RequestMapping("/organizations")
public class OrganizationController {

	@Autowired
	private OrganizationService organizationService;

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private AddressService addressService;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public PagedResources<Resource<OrganizationResource>> findAllOrganizations(Pageable pageable,
																			   PagedResourcesAssembler<OrganizationResource> assembler) {

		Page<Organization> organizationPage = organizationService.findAll(pageable);

		return assembler.toResource(organizationPage.map(OrganizationResource::new));
	}

	@GetMapping(value = "/{organizationId}",
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Resource<Organization> findOrganizationById(@PathVariable Long organizationId) {
		Organization organization = organizationService.findById(organizationId);
		return new OrganizationResource(organization);
	}

	@PutMapping(value = "/{organizationId}",
			consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public void updateOrganization(@PathVariable Long organizationId,
								   @RequestBody @Valid Organization updatedOrganization) {

		Organization organization = organizationService.findById(organizationId);

		organization.setOrganizationName(updatedOrganization.getOrganizationName());

		organizationService.save(organization);
	}

	@PutMapping(value = "/{organizationId}/addresses",
			consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public void changeAddress(@PathVariable Long organizationId,
							  @RequestBody UpdateAddressDTO addressDTO) {

		Organization organization = organizationService.findById(organizationId);

		Address address = addressService.findById(addressDTO.getAddressId());

		organization.setAddress(address);

		organizationService.save(organization);
	}

	@PutMapping(value = "/{organizationId}/ceo",
			consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public void changeCeo(@PathVariable	Long organizationId,
						  @RequestBody UpdateCeoDTO ceoDTO) {

		Organization organization = organizationService.findById(organizationId);

		Employee ceo = employeeService.findById(ceoDTO.getEmployeeId());

		organization.setCeo(ceo);

		organizationService.save(organization);
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public void createOrganization(@RequestBody @Valid Organization organization) {
		organizationService.save(organization);
	}

	@DeleteMapping("/{organizationId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteOrganization(@PathVariable Long organizationId) {

		Organization organization = organizationService.findById(organizationId);
		organizationService.delete(organization);
	}

	@GetMapping("/{organizationId}/employees")
	@ResponseStatus(HttpStatus.OK)
	public PagedResources<Resource<EmployeeResource>> findEmployeesById(@PathVariable Long organizationId,
																		Pageable pageable,
																		PagedResourcesAssembler<EmployeeResource> assembler) {

		Organization organization = organizationService.findById(organizationId);

		Page<Employee> employeePage = employeeService.findByOrganization(pageable, organization);

		return assembler.toResource(employeePage.map(EmployeeResource::new));
	}

	@Data
	private static class UpdateAddressDTO {
		private Long addressId;
	}

	@Data
	private static class UpdateCeoDTO {
		private Long employeeId;
	}
}
