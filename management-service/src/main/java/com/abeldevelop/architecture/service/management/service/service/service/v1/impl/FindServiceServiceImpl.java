package com.abeldevelop.architecture.service.management.service.service.service.v1.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.abeldevelop.architecture.library.common.domain.pagination.in.PaginationAndSortIn;
import com.abeldevelop.architecture.library.common.domain.pagination.out.PaginationResult;
import com.abeldevelop.architecture.library.common.exception.client.BadRequestException;
import com.abeldevelop.architecture.library.common.mapper.pagination.PaginationMapper;
import com.abeldevelop.architecture.library.common.util.StringUtils;
import com.abeldevelop.architecture.service.management.model.ServiceEntity;
import com.abeldevelop.architecture.service.management.repository.ServiceRepository;
import com.abeldevelop.architecture.service.management.repository.specification.ServiceSpecification;
import com.abeldevelop.architecture.service.management.service.constant.ErrorServiceCodeMessageConstants;
import com.abeldevelop.architecture.service.management.service.mapper.ServiceMapper;
import com.abeldevelop.architecture.service.management.service.mapper.ServiceSortMapper;
import com.abeldevelop.architecture.service.management.service.service.application.v1.FindApplicationService;
import com.abeldevelop.architecture.service.management.service.service.service.v1.FindServiceService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FindServiceServiceImpl implements FindServiceService {
	
	private final FindApplicationService findApplicationService;
	private final ServiceRepository serviceRepository;
	private final PaginationMapper paginationMapper;
	private final ServiceMapper serviceMapper;
	private final ServiceSortMapper serviceSortMapper;
	private final ServiceSpecification serviceSpecification;
	
	@Override
	@Transactional(readOnly = true)
	public com.abeldevelop.architecture.service.management.service.domain.Service executeFindByName(String applicationId, String name) {
		findApplicationService.findById(applicationId);
		
		ServiceEntity serviceEntity = serviceRepository.executeFindByName(name)
				.orElseThrow(() -> new BadRequestException(ErrorServiceCodeMessageConstants.SERVICES_WITH_NAME_NOT_EXIST, Arrays.asList(name)));
		
		return serviceMapper.mapEntityToDomain(serviceEntity);
	}

	@Override
	@Transactional(readOnly = true)
	public PaginationResult<com.abeldevelop.architecture.service.management.service.domain.Service> executeFindAll(PaginationAndSortIn paginationAndSortIn, String applicationId, String name) {
		findApplicationService.findById(applicationId);
		Page<ServiceEntity> applicationEntityPage = findAll(paginationAndSortIn, name);
		
		return PaginationResult.of(
                paginationMapper.mapPageToPaginationOut(applicationEntityPage),
                applicationEntityPage.getContent().stream().map(serviceMapper::mapEntityToDomain).collect(Collectors.toList()));
	}

	private Page<ServiceEntity> findAll(PaginationAndSortIn paginationAndSortIn, String name) {
		PageRequest pageRequest = PageRequest.of(paginationAndSortIn.getPagination().getPage(), paginationAndSortIn.getPagination().getSize(), serviceSortMapper.map(paginationAndSortIn.getSort()));
		Specification<ServiceEntity> specification = getSpecification(name);
        if(specification == null) {
        	return serviceRepository.executeFindAll(pageRequest);
        } else {
        	return serviceRepository.executeFindAll(specification, pageRequest);
        }
	}
	
	private Specification<ServiceEntity> getSpecification(String name) {
		List<Specification<ServiceEntity>> specifications = new ArrayList<>();
		if(!StringUtils.isEmpty(name)) {
			specifications.add(serviceSpecification.nameLike(name));
		}
		Specification<ServiceEntity> specification = null;
		int index = 0;
		for(Specification<ServiceEntity> spec : specifications) {
			if(index == 0) {
				specification = spec;
			}
			specification = specification.or(spec);
			index++;
		}
		return specification;
	}
}
