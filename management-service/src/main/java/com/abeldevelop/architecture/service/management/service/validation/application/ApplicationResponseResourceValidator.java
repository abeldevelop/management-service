package com.abeldevelop.architecture.service.management.service.validation.application;

import java.util.Arrays;

import org.springframework.stereotype.Component;

import com.abeldevelop.architecture.library.common.exception.server.ValidationResponseException;
import com.abeldevelop.architecture.library.common.factory.validation.ValidationResource;
import com.abeldevelop.architecture.library.common.util.StringUtils;
import com.abeldevelop.architecture.service.management.dto.application.ApplicationConstants;
import com.abeldevelop.architecture.service.management.dto.application.ApplicationResponseResource;
import com.abeldevelop.architecture.service.management.service.constant.ErrorApplicationCodeMessageConstants;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class ApplicationResponseResourceValidator implements ValidationResource {

	@Override
	public boolean areYouTheElement(String elementName) {
		return ApplicationResponseResource.class.getCanonicalName().equals(elementName);
	}

	@Override
	public void validate(Object toValidate) {
		ApplicationResponseResource applicationResponseResource = (ApplicationResponseResource) toValidate;
		validateId(applicationResponseResource);
		validateName(applicationResponseResource);
		validateDescription(applicationResponseResource);
		validateEnabled(applicationResponseResource);
		validateHomeUri(applicationResponseResource);
		validateDocumentationUrl(applicationResponseResource);
	}

	private void validateId(ApplicationResponseResource applicationResponseResource) {
		if(applicationResponseResource.getId() == null) {
			throw new ValidationResponseException(ErrorApplicationCodeMessageConstants.APPLICATION_ID_NOT_NULL);
		}
	}
	
	private void validateName(ApplicationResponseResource applicationResponseResource) {
		if(StringUtils.isEmpty(applicationResponseResource.getName())) {
			throw new ValidationResponseException(ErrorApplicationCodeMessageConstants.APPLICATION_NAME_NOT_NULL);
		}
		if(!StringUtils.isSizeBetween(applicationResponseResource.getName(), ApplicationConstants.NAME_MIN_SIZE, ApplicationConstants.NAME_MAX_SIZE)) {
			throw new ValidationResponseException(ErrorApplicationCodeMessageConstants.APPLICATION_NAME_SIZE, Arrays.asList(ApplicationConstants.NAME_MIN_SIZE, ApplicationConstants.NAME_MAX_SIZE));
		}
	}

	private void validateDescription(ApplicationResponseResource applicationResponseResource) {
		if(StringUtils.isEmpty(applicationResponseResource.getDescription())) {
			throw new ValidationResponseException(ErrorApplicationCodeMessageConstants.APPLICATION_DESCRIPTION_NOT_NULL);
		}
		if(!StringUtils.isSizeBetween(applicationResponseResource.getDescription(), ApplicationConstants.DESCRIPTION_MIN_SIZE, ApplicationConstants.DESCRIPTION_MAX_SIZE)) {
			throw new ValidationResponseException(ErrorApplicationCodeMessageConstants.APPLICATION_DESCRIPTION_SIZE, Arrays.asList(ApplicationConstants.DESCRIPTION_MIN_SIZE, ApplicationConstants.DESCRIPTION_MAX_SIZE));
		}
	}

	private void validateEnabled(ApplicationResponseResource applicationResponseResource) {
		if(applicationResponseResource.getEnabled() == null) {
			throw new ValidationResponseException(ErrorApplicationCodeMessageConstants.APPLICATION_ENABLED_NOT_NULL);
		}
	}

	private void validateHomeUri(ApplicationResponseResource applicationResponseResource) {
		if(StringUtils.isEmpty(applicationResponseResource.getHomeUri())) {
			throw new ValidationResponseException(ErrorApplicationCodeMessageConstants.APPLICATION_HOME_URI_NOT_NULL);
		}
		if(!StringUtils.isSizeBetween(applicationResponseResource.getHomeUri(), ApplicationConstants.HOME_URI_MIN_SIZE, ApplicationConstants.HOME_URI_MAX_SIZE)) {
			throw new ValidationResponseException(ErrorApplicationCodeMessageConstants.APPLICATION_HOME_URI_SIZE, Arrays.asList(ApplicationConstants.HOME_URI_MIN_SIZE, ApplicationConstants.HOME_URI_MAX_SIZE));
		}
	}

	private void validateDocumentationUrl(ApplicationResponseResource applicationResponseResource) {
		if(StringUtils.isEmpty(applicationResponseResource.getDocumentationUrl())) {
			throw new ValidationResponseException(ErrorApplicationCodeMessageConstants.APPLICATION_DOCUMENTATION_URL_NOT_NULL);
		}
		if(!StringUtils.isSizeBetween(applicationResponseResource.getDocumentationUrl(), ApplicationConstants.DOCUMENTATION_URL_MIN_SIZE, ApplicationConstants.DOCUMENTATION_URL_MAX_SIZE)) {
			throw new ValidationResponseException(ErrorApplicationCodeMessageConstants.APPLICATION_DOCUMENTATION_URL_SIZE, Arrays.asList(ApplicationConstants.DOCUMENTATION_URL_MIN_SIZE, ApplicationConstants.DOCUMENTATION_URL_MAX_SIZE));
		}
	}
	
}