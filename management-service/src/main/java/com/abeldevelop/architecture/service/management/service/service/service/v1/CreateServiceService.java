package com.abeldevelop.architecture.service.management.service.service.service.v1;

import com.abeldevelop.architecture.service.management.service.domain.Service;

public interface CreateServiceService {

	public Service executeCreate(String applicationId, Service service);
	
}