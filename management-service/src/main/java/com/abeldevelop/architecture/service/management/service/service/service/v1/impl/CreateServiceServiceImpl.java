package com.abeldevelop.architecture.service.management.service.service.service.v1.impl;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.abeldevelop.architecture.library.common.exception.client.BadRequestException;
import com.abeldevelop.architecture.library.common.service.CommonService;
import com.abeldevelop.architecture.service.management.model.ApplicationEntity;
import com.abeldevelop.architecture.service.management.model.ServiceEntity;
import com.abeldevelop.architecture.service.management.repository.ServiceRepository;
import com.abeldevelop.architecture.service.management.service.constant.ErrorServiceCodeMessageConstants;
import com.abeldevelop.architecture.service.management.service.mapper.ServiceMapper;
import com.abeldevelop.architecture.service.management.service.service.application.v1.FindApplicationService;
import com.abeldevelop.architecture.service.management.service.service.service.v1.CreateServiceService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CreateServiceServiceImpl extends CommonService implements CreateServiceService {
	
	private final FindApplicationService findApplicationService;
	private final ServiceMapper serviceMapper;
	private final ServiceRepository serviceRepository;
	
	@Override
	@Transactional
	public com.abeldevelop.architecture.service.management.service.domain.Service executeCreate(String applicationId, com.abeldevelop.architecture.service.management.service.domain.Service service) {
		ApplicationEntity applicationEntity = findApplicationService.findById(applicationId);
		checkIfServiceExist(service);
		checkIfPortExist(service.getPort());
		if(service.getPort() == null) {
			service.setPort(serviceRepository.executeFindLastPort());
		}
		ServiceEntity serviceEntity = serviceMapper.mapDomainToEntity(service);
		serviceEntity.setApplication(applicationEntity);
		serviceRepository.executeSave(serviceEntity);
		return serviceMapper.mapEntityToDomain(serviceEntity);
	}

	private void checkIfServiceExist(com.abeldevelop.architecture.service.management.service.domain.Service service) {
		Optional<ServiceEntity> serviceEntityOptional = serviceRepository.executeFindByName(service.getName());
		if(serviceEntityOptional.isPresent()) {
			throw new BadRequestException(ErrorServiceCodeMessageConstants.SERVICES_WITH_NAME_EXIST, Arrays.asList(service.getName()));
		}
	}
	
	private void checkIfPortExist(Integer port) {
		if(port != null && serviceRepository.executeExistsByPort(port)) {
			throw new BadRequestException(ErrorServiceCodeMessageConstants.SERVICES_WITH_PORT_EXIST, Arrays.asList(port));
		}
	}
}
