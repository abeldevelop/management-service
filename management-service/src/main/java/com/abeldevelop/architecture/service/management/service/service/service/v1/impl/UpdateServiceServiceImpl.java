package com.abeldevelop.architecture.service.management.service.service.service.v1.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.abeldevelop.architecture.service.management.service.service.service.v1.UpdateServiceService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UpdateServiceServiceImpl implements UpdateServiceService {
	
	@Override
	@Transactional
	public com.abeldevelop.architecture.service.management.service.domain.Service executeUpdate(String applicationId, com.abeldevelop.architecture.service.management.service.domain.Service service) {
		return null;
	}

}
