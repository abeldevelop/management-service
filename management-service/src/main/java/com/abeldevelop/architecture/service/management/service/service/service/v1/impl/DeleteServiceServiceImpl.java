package com.abeldevelop.architecture.service.management.service.service.service.v1.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.abeldevelop.architecture.service.management.service.service.service.v1.DeleteServiceService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class DeleteServiceServiceImpl implements DeleteServiceService {
	
	@Override
	@Transactional
	public void executeDelete(String applicationId, String name) {
		
	}

}
