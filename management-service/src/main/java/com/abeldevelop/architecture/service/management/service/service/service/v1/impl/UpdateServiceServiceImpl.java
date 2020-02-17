package com.abeldevelop.architecture.service.management.service.service.service.v1.impl;

import java.util.Arrays;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.abeldevelop.architecture.library.common.exception.client.BadRequestException;
import com.abeldevelop.architecture.library.common.service.CommonService;
import com.abeldevelop.architecture.service.management.model.ServiceEntity;
import com.abeldevelop.architecture.service.management.repository.ServiceRepository;
import com.abeldevelop.architecture.service.management.service.constant.ErrorServiceCodeMessageConstants;
import com.abeldevelop.architecture.service.management.service.mapper.ServiceMapper;
import com.abeldevelop.architecture.service.management.service.service.application.v1.FindApplicationService;
import com.abeldevelop.architecture.service.management.service.service.service.v1.UpdateServiceService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UpdateServiceServiceImpl extends CommonService implements UpdateServiceService {
	
	private final FindApplicationService findApplicationService;
	private final ServiceRepository serviceRepository;
	private final ServiceMapper serviceMapper;
	
	@Override
	@Transactional
	public com.abeldevelop.architecture.service.management.service.domain.Service executeUpdate(String applicationId, com.abeldevelop.architecture.service.management.service.domain.Service service) {
		findApplicationService.findById(applicationId);
		ServiceEntity serviceEntity = serviceRepository.executeFindByName(service.getName())
				.orElseThrow(() -> new BadRequestException(ErrorServiceCodeMessageConstants.SERVICES_WITH_NAME_NOT_EXIST, Arrays.asList(service.getName())));
		
		serviceMapper.mapDomainToEntity(service, serviceEntity);
		return serviceMapper.mapEntityToDomain(serviceRepository.executeSave(serviceEntity));
	}

}
