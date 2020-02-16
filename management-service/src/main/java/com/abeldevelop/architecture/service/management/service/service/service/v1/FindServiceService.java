package com.abeldevelop.architecture.service.management.service.service.service.v1;

import com.abeldevelop.architecture.library.common.domain.pagination.in.PaginationAndSortIn;
import com.abeldevelop.architecture.library.common.domain.pagination.out.PaginationResult;
import com.abeldevelop.architecture.service.management.service.domain.Service;

public interface FindServiceService {

	public Service executeFindByName(String applicationId, String id);
	
	public PaginationResult<Service> executeFindAll(PaginationAndSortIn paginationAndSortIn, String applicationId, String name);
	
}