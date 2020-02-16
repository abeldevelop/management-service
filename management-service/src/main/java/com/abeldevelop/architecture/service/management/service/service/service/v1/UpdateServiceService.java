package com.abeldevelop.architecture.service.management.service.service.service.v1;

import com.abeldevelop.architecture.service.management.service.domain.Service;

public interface UpdateServiceService {

	public Service executeUpdate(String applicationId, Service service);
	
}
