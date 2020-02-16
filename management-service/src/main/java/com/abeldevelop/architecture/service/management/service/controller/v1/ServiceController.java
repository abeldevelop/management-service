package com.abeldevelop.architecture.service.management.service.controller.v1;

import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.RestController;

import com.abeldevelop.architecture.library.common.domain.pagination.in.PaginationAndSortIn;
import com.abeldevelop.architecture.library.common.domain.pagination.in.PaginationIn;
import com.abeldevelop.architecture.library.common.domain.pagination.out.PaginationResult;
import com.abeldevelop.architecture.library.common.factory.validation.ValidationFactory;
import com.abeldevelop.architecture.library.common.mapper.pagination.PaginationMapper;
import com.abeldevelop.architecture.service.management.api.v1.ServiceApi;
import com.abeldevelop.architecture.service.management.dto.service.CreateServiceRequestResource;
import com.abeldevelop.architecture.service.management.dto.service.ServicePaginationResponseResource;
import com.abeldevelop.architecture.service.management.dto.service.ServiceResponseResource;
import com.abeldevelop.architecture.service.management.dto.service.ServiceSort;
import com.abeldevelop.architecture.service.management.dto.service.UpdateServiceRequestResource;
import com.abeldevelop.architecture.service.management.service.domain.Service;
import com.abeldevelop.architecture.service.management.service.mapper.ServiceMapper;
import com.abeldevelop.architecture.service.management.service.mapper.ServiceSortMapper;
import com.abeldevelop.architecture.service.management.service.service.service.v1.CreateServiceService;
import com.abeldevelop.architecture.service.management.service.service.service.v1.DeleteServiceService;
import com.abeldevelop.architecture.service.management.service.service.service.v1.FindServiceService;
import com.abeldevelop.architecture.service.management.service.service.service.v1.UpdateServiceService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ServiceController implements ServiceApi {
	
	private final ValidationFactory validationFactory;
	private final ServiceMapper serviceMapper;
	private final PaginationMapper paginationMapper;
	private final ServiceSortMapper serviceSortMapper;
	private final CreateServiceService createServiceService;
	private final UpdateServiceService updateServiceService;
	private final DeleteServiceService deleteServiceService;
	private final FindServiceService findServiceService;
	
	@Override
	public ServiceResponseResource executeCreate(String id, CreateServiceRequestResource createServiceRequestResource) {
		validationFactory.validate(createServiceRequestResource);
		ServiceResponseResource serviceResponseResource = serviceMapper.mapDomainToResource(createServiceService.executeCreate(id, serviceMapper.mapResourceToDomain(createServiceRequestResource)));
		validationFactory.validate(serviceResponseResource);
		return serviceResponseResource;
	}

	@Override
	public ServiceResponseResource executeUpdate(String id, String name, UpdateServiceRequestResource updateServiceRequestResource) {
		validationFactory.validate(updateServiceRequestResource);
		ServiceResponseResource serviceResponseResource = serviceMapper.mapDomainToResource(updateServiceService.executeUpdate(id, serviceMapper.mapResourceToDomain(name, updateServiceRequestResource)));
		validationFactory.validate(serviceResponseResource);
		return serviceResponseResource;
	}

	@Override
	public void executeDelete(String id, String name) {
		deleteServiceService.executeDelete(id, name);
	}

	@Override
	public ServiceResponseResource executeFindByName(String id, String name) {
		ServiceResponseResource serviceResponseResource = serviceMapper.mapDomainToResource(findServiceService.executeFindByName(id, name));
		validationFactory.validate(serviceResponseResource);
		return serviceResponseResource;
	}

	@Override
	public ServicePaginationResponseResource executeFindAll(String id, Integer page, Integer size, ServiceSort sort, String name) {
		PaginationIn paginationIn = paginationMapper.map(page, size);
		PaginationAndSortIn paginationAndSortIn = PaginationAndSortIn.builder().pagination(paginationIn).sort(serviceSortMapper.map(sort)).build();
		PaginationResult<Service> paginationResult = findServiceService.executeFindAll(paginationAndSortIn, id, name);
		ServicePaginationResponseResource servicePaginationResponseResource = ServicePaginationResponseResource.builder()
				.pagination(paginationMapper.map(paginationResult.getPagination()))
				.services(paginationResult.getResults().stream().map(serviceMapper::mapDomainToResource).collect(Collectors.toList()))
				.build();
		validationFactory.validate(servicePaginationResponseResource);
		return servicePaginationResponseResource;
	}

}
