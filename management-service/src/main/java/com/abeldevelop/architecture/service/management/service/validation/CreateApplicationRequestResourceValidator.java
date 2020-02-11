package com.abeldevelop.architecture.service.management.service.validation;

import java.util.Arrays;

import org.springframework.stereotype.Component;

import com.abeldevelop.architecture.library.common.exception.client.ValidationRequestException;
import com.abeldevelop.architecture.library.common.factory.validation.ValidationResource;
import com.abeldevelop.architecture.library.common.util.StringUtils;
import com.abeldevelop.architecture.service.management.dto.application.ApplicationConstants;
import com.abeldevelop.architecture.service.management.dto.application.CreateApplicationRequestResource;
import com.abeldevelop.architecture.service.management.service.constant.ErrorApplicationCodeMessageConstants;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class CreateApplicationRequestResourceValidator implements ValidationResource {

	@Override
	public boolean areYouTheElement(String elementName) {
		return CreateApplicationRequestResource.class.getCanonicalName().equals(elementName);
	}

	@Override
	public void validate(Object toValidate) {
		CreateApplicationRequestResource createApplicationRequestResource = (CreateApplicationRequestResource) toValidate;
		validateName(createApplicationRequestResource);
		validateDescription(createApplicationRequestResource);
		validateEnabled(createApplicationRequestResource);
		validateHomeUri(createApplicationRequestResource);
		validateDocumentationUrl(createApplicationRequestResource);
	}

	private void validateName(CreateApplicationRequestResource createApplicationRequestResource) {
		if(StringUtils.isEmpty(createApplicationRequestResource.getName())) {
			throw new ValidationRequestException(ErrorApplicationCodeMessageConstants.APPLICATION_NAME_NOT_NULL);
		}
		if(!StringUtils.isSizeBetween(createApplicationRequestResource.getName(), ApplicationConstants.NAME_MIN_SIZE, ApplicationConstants.NAME_MAX_SIZE)) {
			throw new ValidationRequestException(ErrorApplicationCodeMessageConstants.APPLICATION_NAME_SIZE, Arrays.asList(ApplicationConstants.NAME_MIN_SIZE, ApplicationConstants.NAME_MAX_SIZE));
		}
	}

	private void validateDescription(CreateApplicationRequestResource createApplicationRequestResource) {
		if(StringUtils.isEmpty(createApplicationRequestResource.getDescription())) {
			throw new ValidationRequestException(ErrorApplicationCodeMessageConstants.APPLICATION_DESCRIPTION_NOT_NULL);
		}
		if(!StringUtils.isSizeBetween(createApplicationRequestResource.getDescription(), ApplicationConstants.DESCRIPTION_MIN_SIZE, ApplicationConstants.DESCRIPTION_MAX_SIZE)) {
			throw new ValidationRequestException(ErrorApplicationCodeMessageConstants.APPLICATION_DESCRIPTION_SIZE, Arrays.asList(ApplicationConstants.DESCRIPTION_MIN_SIZE, ApplicationConstants.DESCRIPTION_MAX_SIZE));
		}
	}

	private void validateEnabled(CreateApplicationRequestResource createApplicationRequestResource) {
		if(createApplicationRequestResource.getEnabled() == null) {
			throw new ValidationRequestException(ErrorApplicationCodeMessageConstants.APPLICATION_ENABLED_NOT_NULL);
		}
	}

	private void validateHomeUri(CreateApplicationRequestResource createApplicationRequestResource) {
		if(StringUtils.isEmpty(createApplicationRequestResource.getHomeUri())) {
			throw new ValidationRequestException(ErrorApplicationCodeMessageConstants.APPLICATION_HOME_URI_NOT_NULL);
		}
		if(!StringUtils.isSizeBetween(createApplicationRequestResource.getHomeUri(), ApplicationConstants.HOME_URI_MIN_SIZE, ApplicationConstants.HOME_URI_MAX_SIZE)) {
			throw new ValidationRequestException(ErrorApplicationCodeMessageConstants.APPLICATION_HOME_URI_SIZE, Arrays.asList(ApplicationConstants.HOME_URI_MIN_SIZE, ApplicationConstants.HOME_URI_MAX_SIZE));
		}
	}

	private void validateDocumentationUrl(CreateApplicationRequestResource createApplicationRequestResource) {
		if(StringUtils.isEmpty(createApplicationRequestResource.getDocumentationUrl())) {
			throw new ValidationRequestException(ErrorApplicationCodeMessageConstants.APPLICATION_DOCUMENTATION_URL_NOT_NULL);
		}
		if(!StringUtils.isSizeBetween(createApplicationRequestResource.getDocumentationUrl(), ApplicationConstants.DOCUMENTATION_URL_MIN_SIZE, ApplicationConstants.DOCUMENTATION_URL_MAX_SIZE)) {
			throw new ValidationRequestException(ErrorApplicationCodeMessageConstants.APPLICATION_DOCUMENTATION_URL_SIZE, Arrays.asList(ApplicationConstants.DOCUMENTATION_URL_MIN_SIZE, ApplicationConstants.DOCUMENTATION_URL_MAX_SIZE));
		}
	}
}