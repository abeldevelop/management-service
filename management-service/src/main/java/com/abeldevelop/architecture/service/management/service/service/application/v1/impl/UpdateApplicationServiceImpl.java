package com.abeldevelop.architecture.service.management.service.service.application.v1.impl;

import java.util.Arrays;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.abeldevelop.architecture.library.common.exception.client.BadRequestException;
import com.abeldevelop.architecture.library.common.service.CommonService;
import com.abeldevelop.architecture.service.management.model.ApplicationEntity;
import com.abeldevelop.architecture.service.management.repository.ApplicationRepository;
import com.abeldevelop.architecture.service.management.service.constant.ErrorApplicationCodeMessageConstants;
import com.abeldevelop.architecture.service.management.service.domain.Application;
import com.abeldevelop.architecture.service.management.service.mapper.ApplicationMapper;
import com.abeldevelop.architecture.service.management.service.service.application.v1.UpdateApplicationService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UpdateApplicationServiceImpl extends CommonService implements UpdateApplicationService {

	private final ApplicationRepository applicationRepository;
	private final ApplicationMapper applicationMapper;
	
	@Override
	@Transactional
	public Application executeUpdate(Application application) {
		ApplicationEntity applicationEntity = applicationRepository.executeFindById(application.getId())
			.orElseThrow(() -> new BadRequestException(ErrorApplicationCodeMessageConstants.APPLICATION_WITH_ID_NOT_EXIST, Arrays.asList(application.getId())));
		
		checkNotExistConflict(applicationEntity.getVersion(), application.getVersion());
		
		return applicationMapper.mapEntityToDomain(applicationRepository.executeSave(applicationMapper.mapDomainToEntity(application, applicationEntity)));
	}

}
