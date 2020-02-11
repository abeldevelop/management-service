package com.abeldevelop.architecture.service.management.service.validation;

import org.springframework.stereotype.Component;

import com.abeldevelop.architecture.library.common.exception.server.ValidationResponseException;
import com.abeldevelop.architecture.library.common.factory.validation.ValidationResource;
import com.abeldevelop.architecture.library.common.validation.PaginationResponseResourceValidation;
import com.abeldevelop.architecture.service.management.dto.application.ApplicationPaginationResponseResource;
import com.abeldevelop.architecture.service.management.service.constant.ErrorApplicationCodeMessageConstants;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class ApplicationPaginationResponseResourceValidator implements ValidationResource {

	private final ApplicationResponseResourceValidator applicationResponseResourceValidator;
	private final PaginationResponseResourceValidation paginationResponseResourceValidation;
	
	@Override
	public boolean areYouTheElement(String elementName) {
		return ApplicationPaginationResponseResource.class.getCanonicalName().equals(elementName);
	}

	@Override
	public void validate(Object toValidate) {
		ApplicationPaginationResponseResource applicationPaginationResponseResource = (ApplicationPaginationResponseResource) toValidate;
		validatePagination(applicationPaginationResponseResource);
		validateErrorMessages(applicationPaginationResponseResource);
	}

	private void validatePagination(ApplicationPaginationResponseResource applicationPaginationResponseResource) {
		paginationResponseResourceValidation.validate(applicationPaginationResponseResource.getPagination());
	}
	
	private void validateErrorMessages(ApplicationPaginationResponseResource applicationPaginationResponseResource) {
		if(applicationPaginationResponseResource.getApplications() == null) {
			throw new ValidationResponseException(ErrorApplicationCodeMessageConstants.APPLICATION_PAGINATION_RESPONSE_RESOURCE_APPLICATIONS_NOT_NULL);
		}
		applicationPaginationResponseResource.getApplications().stream().forEach(applicationResponseResourceValidator::validate);
	}
	
}