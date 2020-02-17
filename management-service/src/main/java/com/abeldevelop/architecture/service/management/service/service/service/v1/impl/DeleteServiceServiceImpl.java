package com.abeldevelop.architecture.service.management.service.service.service.v1.impl;

import java.util.Arrays;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.abeldevelop.architecture.library.common.exception.client.BadRequestException;
import com.abeldevelop.architecture.service.management.repository.ServiceRepository;
import com.abeldevelop.architecture.service.management.service.constant.ErrorServiceCodeMessageConstants;
import com.abeldevelop.architecture.service.management.service.service.application.v1.FindApplicationService;
import com.abeldevelop.architecture.service.management.service.service.service.v1.DeleteServiceService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class DeleteServiceServiceImpl implements DeleteServiceService {
	
	private final FindApplicationService findApplicationService;
	private final ServiceRepository serviceRepository;
	
	@Override
	@Transactional
	public void executeDelete(String applicationId, String name) {
		findApplicationService.findById(applicationId);
		
		if(!serviceRepository.executeExistsByName(name)) {
			throw new BadRequestException(ErrorServiceCodeMessageConstants.SERVICES_WITH_NAME_NOT_EXIST, Arrays.asList(name));
		}
		
		serviceRepository.executeDeleteByName(name);
	}

}
