package com.abeldevelop.architecture.service.management.service.service.v1.impl;

import java.util.Arrays;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.abeldevelop.architecture.library.common.exception.client.BadRequestException;
import com.abeldevelop.architecture.library.common.util.StringUtils;
import com.abeldevelop.architecture.service.management.model.ApplicationEntity;
import com.abeldevelop.architecture.service.management.repository.ApplicationRepository;
import com.abeldevelop.architecture.service.management.service.constant.ErrorApplicationCodeMessageConstants;
import com.abeldevelop.architecture.service.management.service.domain.Application;
import com.abeldevelop.architecture.service.management.service.mapper.ApplicationMapper;
import com.abeldevelop.architecture.service.management.service.service.v1.CreateApplicationService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CreateApplicationServiceImpl implements CreateApplicationService {

	private final ApplicationRepository applicationRepository;
	private final ApplicationMapper applicationMapper;
	
	@Override
	@Transactional
	public Application executeCreate(Application application) {
		if(applicationRepository.executeFindByName(application.getName()).isPresent()) {
			throw new BadRequestException(ErrorApplicationCodeMessageConstants.APPLICATION_WITH_NAME_EXIST, Arrays.asList(application.getName()));
		}
		ApplicationEntity applicationEntity = applicationMapper.mapDomainToEntity(application);
		applicationEntity.setId(StringUtils.generateUrlName(application.getName()));
		
		return applicationMapper.mapEntityToDomain(applicationRepository.executeSave(applicationEntity));
	}

}
