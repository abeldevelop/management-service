package com.abeldevelop.architecture.service.management.service.validation.application;

import java.util.Arrays;

import org.springframework.stereotype.Component;

import com.abeldevelop.architecture.library.common.exception.client.ValidationRequestException;
import com.abeldevelop.architecture.library.common.factory.validation.ValidationResource;
import com.abeldevelop.architecture.library.common.util.StringUtils;
import com.abeldevelop.architecture.service.management.dto.application.ApplicationConstants;
import com.abeldevelop.architecture.service.management.dto.application.UpdateApplicationRequestResource;
import com.abeldevelop.architecture.service.management.service.constant.ErrorApplicationCodeMessageConstants;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class UpdateApplicationRequestResourceValidator implements ValidationResource {

	@Override
	public boolean areYouTheElement(String elementName) {
		return UpdateApplicationRequestResource.class.getCanonicalName().equals(elementName);
	}

	@Override
	public void validate(Object toValidate) {
		UpdateApplicationRequestResource updateApplicationRequestResource = (UpdateApplicationRequestResource) toValidate;
		validateDescription(updateApplicationRequestResource);
		validateEnabled(updateApplicationRequestResource);
		validateHomeUri(updateApplicationRequestResource);
		validateDocumentationUrl(updateApplicationRequestResource);
		validateVersion(updateApplicationRequestResource);
	}

	private void validateDescription(UpdateApplicationRequestResource updateApplicationRequestResource) {
		if(StringUtils.isEmpty(updateApplicationRequestResource.getDescription())) {
			throw new ValidationRequestException(ErrorApplicationCodeMessageConstants.APPLICATION_DESCRIPTION_NOT_NULL);
		}
		if(!StringUtils.isSizeBetween(updateApplicationRequestResource.getDescription(), ApplicationConstants.DESCRIPTION_MIN_SIZE, ApplicationConstants.DESCRIPTION_MAX_SIZE)) {
			throw new ValidationRequestException(ErrorApplicationCodeMessageConstants.APPLICATION_DESCRIPTION_SIZE, Arrays.asList(ApplicationConstants.DESCRIPTION_MIN_SIZE, ApplicationConstants.DESCRIPTION_MAX_SIZE));
		}
	}

	private void validateEnabled(UpdateApplicationRequestResource updateApplicationRequestResource) {
		if(updateApplicationRequestResource.getEnabled() == null) {
			throw new ValidationRequestException(ErrorApplicationCodeMessageConstants.APPLICATION_ENABLED_NOT_NULL);
		}
	}

	private void validateHomeUri(UpdateApplicationRequestResource updateApplicationRequestResource) {
		if(StringUtils.isEmpty(updateApplicationRequestResource.getHomeUri())) {
			throw new ValidationRequestException(ErrorApplicationCodeMessageConstants.APPLICATION_HOME_URI_NOT_NULL);
		}
		if(!StringUtils.isSizeBetween(updateApplicationRequestResource.getHomeUri(), ApplicationConstants.HOME_URI_MIN_SIZE, ApplicationConstants.HOME_URI_MAX_SIZE)) {
			throw new ValidationRequestException(ErrorApplicationCodeMessageConstants.APPLICATION_HOME_URI_SIZE, Arrays.asList(ApplicationConstants.HOME_URI_MIN_SIZE, ApplicationConstants.HOME_URI_MAX_SIZE));
		}
	}

	private void validateDocumentationUrl(UpdateApplicationRequestResource updateApplicationRequestResource) {
		if(StringUtils.isEmpty(updateApplicationRequestResource.getDocumentationUrl())) {
			throw new ValidationRequestException(ErrorApplicationCodeMessageConstants.APPLICATION_DOCUMENTATION_URL_NOT_NULL);
		}
		if(!StringUtils.isSizeBetween(updateApplicationRequestResource.getDocumentationUrl(), ApplicationConstants.DOCUMENTATION_URL_MIN_SIZE, ApplicationConstants.DOCUMENTATION_URL_MAX_SIZE)) {
			throw new ValidationRequestException(ErrorApplicationCodeMessageConstants.APPLICATION_DOCUMENTATION_URL_SIZE, Arrays.asList(ApplicationConstants.DOCUMENTATION_URL_MIN_SIZE, ApplicationConstants.DOCUMENTATION_URL_MAX_SIZE));
		}
	}
	
	private void validateVersion(UpdateApplicationRequestResource updateApplicationRequestResource) {
		if(updateApplicationRequestResource.getVersion() == null) {
			throw new ValidationRequestException(ErrorApplicationCodeMessageConstants.APPLICATION_VERSION_NOT_NULL);
		}
	}
}