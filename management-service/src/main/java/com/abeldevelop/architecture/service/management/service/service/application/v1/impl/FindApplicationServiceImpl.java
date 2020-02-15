package com.abeldevelop.architecture.service.management.service.service.application.v1.impl;

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
import com.abeldevelop.architecture.service.management.model.ApplicationEntity;
import com.abeldevelop.architecture.service.management.repository.ApplicationRepository;
import com.abeldevelop.architecture.service.management.repository.specification.ApplicationSpecification;
import com.abeldevelop.architecture.service.management.service.constant.ErrorApplicationCodeMessageConstants;
import com.abeldevelop.architecture.service.management.service.domain.Application;
import com.abeldevelop.architecture.service.management.service.mapper.ApplicationMapper;
import com.abeldevelop.architecture.service.management.service.mapper.ApplicationSortMapper;
import com.abeldevelop.architecture.service.management.service.service.application.v1.FindApplicationService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FindApplicationServiceImpl implements FindApplicationService {

	private final ApplicationRepository applicationRepository;
	private final ApplicationMapper applicationMapper;
	private final PaginationMapper paginationMapper;
	private final ApplicationSortMapper applicationSortMapper;
	private final ApplicationSpecification applicationSpecification;
	
	@Override
	@Transactional(readOnly = true)
	public Application executeFindById(String id) {
		return applicationMapper.mapEntityToDomain(
				applicationRepository.executeFindById(id)
				.orElseThrow(() -> new BadRequestException(ErrorApplicationCodeMessageConstants.APPLICATION_WITH_ID_NOT_EXIST, Arrays.asList(id)))
				);
	}

	@Override
	@Transactional(readOnly = true)
	public PaginationResult<Application> executeFindAll(PaginationAndSortIn paginationAndSortIn, String name, Boolean enabled) {
		Page<ApplicationEntity> applicationEntityPage = findAll(paginationAndSortIn, name, enabled);
		
		return PaginationResult.of(
                paginationMapper.mapPageToPaginationOut(applicationEntityPage),
                applicationEntityPage.getContent().stream().map(applicationMapper::mapEntityToDomain).collect(Collectors.toList()));
	}

	private Page<ApplicationEntity> findAll(PaginationAndSortIn paginationAndSortIn, String name, Boolean enabled) {
		PageRequest pageRequest = PageRequest.of(paginationAndSortIn.getPagination().getPage(), paginationAndSortIn.getPagination().getSize(), applicationSortMapper.map(paginationAndSortIn.getSort()));
		Specification<ApplicationEntity> specification = getSpecification(name, enabled);
        if(specification == null) {
        	return applicationRepository.executeFindAll(pageRequest);
        } else {
        	return applicationRepository.executeFindAll(specification, pageRequest);
        }
	}
	
	private Specification<ApplicationEntity> getSpecification(String name, Boolean enabled) {
		List<Specification<ApplicationEntity>> specifications = new ArrayList<>();
		if(!StringUtils.isEmpty(name)) {
			specifications.add(applicationSpecification.nameLike(name));
		}
		if(enabled != null) {
			specifications.add(applicationSpecification.enabled(enabled.booleanValue()));
		}
		if(specifications.isEmpty()) {
			return null;
		}
		Specification<ApplicationEntity> specification = null;
		int index = 0;
		for(Specification<ApplicationEntity> spec : specifications) {
			if(index == 0) {
				specification = spec;
			}
			specification = specification.or(spec);
			index++;
		}
		return specification;
	}
}
