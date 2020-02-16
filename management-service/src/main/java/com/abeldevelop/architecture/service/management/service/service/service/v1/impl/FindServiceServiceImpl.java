package com.abeldevelop.architecture.service.management.service.service.service.v1.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.abeldevelop.architecture.library.common.domain.pagination.in.PaginationAndSortIn;
import com.abeldevelop.architecture.library.common.domain.pagination.out.PaginationResult;
import com.abeldevelop.architecture.service.management.service.service.service.v1.FindServiceService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FindServiceServiceImpl implements FindServiceService {
	
	@Override
	@Transactional(readOnly = true)
	public com.abeldevelop.architecture.service.management.service.domain.Service executeFindByName(String applicationId, String id) {
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public PaginationResult<com.abeldevelop.architecture.service.management.service.domain.Service> executeFindAll(PaginationAndSortIn paginationAndSortIn, String applicationId, String name) {
		return null;
	}

}
