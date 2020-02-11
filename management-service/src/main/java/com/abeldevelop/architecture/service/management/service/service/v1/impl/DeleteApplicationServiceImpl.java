package com.abeldevelop.architecture.service.management.service.service.v1.impl;

import java.util.Arrays;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.abeldevelop.architecture.library.common.exception.client.BadRequestException;
import com.abeldevelop.architecture.service.management.repository.ApplicationRepository;
import com.abeldevelop.architecture.service.management.service.constant.ErrorApplicationCodeMessageConstants;
import com.abeldevelop.architecture.service.management.service.service.v1.DeleteApplicationService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class DeleteApplicationServiceImpl implements DeleteApplicationService {

	private final ApplicationRepository applicationRepository;
	
	@Override
	@Transactional
	public void executeDelete(String id) {
		if(!applicationRepository.executeExistsById(id)) {
			throw new BadRequestException(ErrorApplicationCodeMessageConstants.APPLICATION_WITH_ID_NOT_EXIST, Arrays.asList(id));
		}
		applicationRepository.executeDeleteById(id);
	}

}
