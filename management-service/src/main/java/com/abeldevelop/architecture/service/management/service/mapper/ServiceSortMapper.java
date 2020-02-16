package com.abeldevelop.architecture.service.management.service.mapper;

import org.springframework.stereotype.Component;

import com.abeldevelop.architecture.library.common.domain.pagination.in.SortDirection;
import com.abeldevelop.architecture.library.common.domain.pagination.in.SortIn;
import com.abeldevelop.architecture.library.common.mapper.pagination.SortMapper;
import com.abeldevelop.architecture.service.management.dto.service.ServiceSort;
import com.abeldevelop.architecture.service.management.model.ServiceEntity_;

@Component
public class ServiceSortMapper implements SortMapper<ServiceSort> {

	public SortIn map(ServiceSort enumSort) {
		if(enumSort == null) {
			return getDefault();
		}
		SortIn sortIn = null;
		switch (enumSort) {
			case NAME_DESC:
				sortIn = SortIn.of(SortDirection.DESC, ServiceEntity_.name.getName());
				break;
			default:
				sortIn = getDefault();
				break;
		}
		return sortIn;
    }
	
	private SortIn getDefault() {
		return SortIn.of(SortDirection.DESC, ServiceEntity_.name.getName());
	}
}