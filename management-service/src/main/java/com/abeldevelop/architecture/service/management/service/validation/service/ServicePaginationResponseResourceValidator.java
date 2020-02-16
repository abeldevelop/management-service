package com.abeldevelop.architecture.service.management.service.validation.service;

import org.springframework.stereotype.Component;

import com.abeldevelop.architecture.library.common.exception.server.ValidationResponseException;
import com.abeldevelop.architecture.library.common.factory.validation.ValidationResource;
import com.abeldevelop.architecture.library.common.validation.PaginationResponseResourceValidation;
import com.abeldevelop.architecture.service.management.dto.service.ServicePaginationResponseResource;
import com.abeldevelop.architecture.service.management.service.constant.ErrorServiceCodeMessageConstants;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class ServicePaginationResponseResourceValidator implements ValidationResource {
	
	private final PaginationResponseResourceValidation paginationResponseResourceValidation;
	private final ServiceResponseResourceValidator serviceResponseResourceValidator;
	
	@Override
	public boolean areYouTheElement(String elementName) {
		return ServicePaginationResponseResource.class.getCanonicalName().equals(elementName);
	}

	@Override
	public void validate(Object toValidate) {
		ServicePaginationResponseResource servicePaginationResponseResource = (ServicePaginationResponseResource) toValidate;
		validatePagination(servicePaginationResponseResource);
		validateServices(servicePaginationResponseResource);
	}

	private void validatePagination(ServicePaginationResponseResource servicePaginationResponseResource) {
		paginationResponseResourceValidation.validate(servicePaginationResponseResource.getPagination());
	}
	
	private void validateServices(ServicePaginationResponseResource servicePaginationResponseResource) {
		if(servicePaginationResponseResource.getServices() == null) {
			throw new ValidationResponseException(ErrorServiceCodeMessageConstants.SERVICES_SERVICE_PAGINATION_RESPONSE_RESOURCE_NOT_NULL);
		}
		servicePaginationResponseResource.getServices().stream().forEach(serviceResponseResourceValidator::validate);
	}
}
