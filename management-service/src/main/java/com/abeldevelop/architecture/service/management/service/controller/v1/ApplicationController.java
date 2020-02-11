package com.abeldevelop.architecture.service.management.service.controller.v1;

import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.RestController;

import com.abeldevelop.architecture.library.common.annotation.DisableDataInLog;
import com.abeldevelop.architecture.library.common.domain.pagination.in.PaginationAndSortIn;
import com.abeldevelop.architecture.library.common.domain.pagination.in.PaginationIn;
import com.abeldevelop.architecture.library.common.domain.pagination.out.PaginationResult;
import com.abeldevelop.architecture.library.common.factory.validation.ValidationFactory;
import com.abeldevelop.architecture.library.common.mapper.pagination.PaginationMapper;
import com.abeldevelop.architecture.service.management.api.v1.ApplicationApi;
import com.abeldevelop.architecture.service.management.dto.application.ApplicationPaginationResponseResource;
import com.abeldevelop.architecture.service.management.dto.application.ApplicationResponseResource;
import com.abeldevelop.architecture.service.management.dto.application.ApplicationSort;
import com.abeldevelop.architecture.service.management.dto.application.CreateApplicationRequestResource;
import com.abeldevelop.architecture.service.management.dto.application.UpdateApplicationRequestResource;
import com.abeldevelop.architecture.service.management.service.domain.Application;
import com.abeldevelop.architecture.service.management.service.mapper.ApplicationMapper;
import com.abeldevelop.architecture.service.management.service.mapper.ApplicationSortMapper;
import com.abeldevelop.architecture.service.management.service.service.v1.CreateApplicationService;
import com.abeldevelop.architecture.service.management.service.service.v1.DeleteApplicationService;
import com.abeldevelop.architecture.service.management.service.service.v1.FindApplicationService;
import com.abeldevelop.architecture.service.management.service.service.v1.UpdateApplicationService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ApplicationController implements ApplicationApi {

	private final ValidationFactory validationFactory;
	private final ApplicationMapper applicationMapper;
	private final PaginationMapper paginationMapper;
	private final ApplicationSortMapper applicationSortMapper;
	private final CreateApplicationService createApplicationService;
	private final UpdateApplicationService updateApplicationService;
	private final DeleteApplicationService deleteApplicationService;
	private final FindApplicationService findApplicationService;
	
	@Override
	public ApplicationResponseResource executeCreate(CreateApplicationRequestResource createApplicationRequestResource) {
		validationFactory.validate(createApplicationRequestResource);
		ApplicationResponseResource applicationResponseResource = applicationMapper.mapDomainToResource(createApplicationService.executeCreate(applicationMapper.mapResourceToDomain(createApplicationRequestResource)));
		validationFactory.validate(applicationResponseResource);
		return applicationResponseResource;
	}

	@Override
	public ApplicationResponseResource executeUpdate(String id, UpdateApplicationRequestResource updateApplicationRequestResource) {
		validationFactory.validate(updateApplicationRequestResource);
		ApplicationResponseResource applicationResponseResource = applicationMapper.mapDomainToResource(updateApplicationService.executeUpdate(applicationMapper.mapResourceToDomain(id, updateApplicationRequestResource)));
		validationFactory.validate(applicationResponseResource);
		return applicationResponseResource;
	}

	@Override
	public void executeDelete(String id) {
		deleteApplicationService.executeDelete(id);
	}

	@Override
	public ApplicationResponseResource executeFindById(String id) {
		ApplicationResponseResource applicationResponseResource = applicationMapper.mapDomainToResource(findApplicationService.executeFindById(id));
		validationFactory.validate(applicationResponseResource);
		return applicationResponseResource;
	}

//	@DisableDataInLog
	@Override
	public ApplicationPaginationResponseResource executeFindAll(Integer page, Integer size, ApplicationSort sort, String name, Boolean enabled) {
		PaginationIn paginationIn = paginationMapper.map(page, size);
		PaginationAndSortIn paginationAndSortIn = PaginationAndSortIn.builder().pagination(paginationIn).sort(applicationSortMapper.map(sort)).build();
		PaginationResult<Application> paginationResult = findApplicationService.executeFindAll(paginationAndSortIn, name, enabled);
		ApplicationPaginationResponseResource applicationPaginationResponseResource = ApplicationPaginationResponseResource.builder()
				.pagination(paginationMapper.map(paginationResult.getPagination()))
				.applications(paginationResult.getResults().stream().map(applicationMapper::mapDomainToResource).collect(Collectors.toList()))
				.build();
		validationFactory.validate(applicationPaginationResponseResource);
		return applicationPaginationResponseResource;
	}

}