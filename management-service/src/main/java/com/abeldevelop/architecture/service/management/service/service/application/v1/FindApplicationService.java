package com.abeldevelop.architecture.service.management.service.service.application.v1;

import com.abeldevelop.architecture.library.common.domain.pagination.in.PaginationAndSortIn;
import com.abeldevelop.architecture.library.common.domain.pagination.out.PaginationResult;
import com.abeldevelop.architecture.service.management.model.ApplicationEntity;
import com.abeldevelop.architecture.service.management.service.domain.Application;

public interface FindApplicationService {

	public ApplicationEntity findById(String id);
	
	public Application executeFindById(String id);
	
	public PaginationResult<Application> executeFindAll(PaginationAndSortIn paginationAndSortIn, String name, Boolean enabled);
	
}