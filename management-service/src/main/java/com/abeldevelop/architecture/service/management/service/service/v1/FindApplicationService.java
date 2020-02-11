package com.abeldevelop.architecture.service.management.service.service.v1;

import com.abeldevelop.architecture.library.common.domain.pagination.in.PaginationAndSortIn;
import com.abeldevelop.architecture.library.common.domain.pagination.out.PaginationResult;
import com.abeldevelop.architecture.service.management.service.domain.Application;

public interface FindApplicationService {

	public Application executeFindById(String id);
	
	public PaginationResult<Application> executeFindAll(PaginationAndSortIn paginationAndSortIn, String name, Boolean enabled);
	
}