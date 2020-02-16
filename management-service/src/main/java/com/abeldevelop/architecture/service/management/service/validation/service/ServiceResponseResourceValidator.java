package com.abeldevelop.architecture.service.management.service.validation.service;

import java.util.Arrays;

import org.springframework.stereotype.Component;

import com.abeldevelop.architecture.library.common.exception.server.ValidationResponseException;
import com.abeldevelop.architecture.library.common.factory.validation.ValidationResource;
import com.abeldevelop.architecture.library.common.util.StringUtils;
import com.abeldevelop.architecture.service.management.dto.service.ServiceConstants;
import com.abeldevelop.architecture.service.management.dto.service.ServiceResponseResource;
import com.abeldevelop.architecture.service.management.service.constant.ErrorServiceCodeMessageConstants;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class ServiceResponseResourceValidator implements ValidationResource {
	
	@Override
	public boolean areYouTheElement(String elementName) {
		return ServiceResponseResource.class.getCanonicalName().equals(elementName);
	}

	@Override
	public void validate(Object toValidate) {
		ServiceResponseResource serviceResponseResource = (ServiceResponseResource) toValidate;
		validateName(serviceResponseResource);
		validateDescription(serviceResponseResource);
		validatePort(serviceResponseResource);
		validateGitUrl(serviceResponseResource);
		validateDocumentationUrl(serviceResponseResource);
	}

	private void validateName(ServiceResponseResource serviceResponseResource) {
		if(StringUtils.isEmpty(serviceResponseResource.getName())) {
			throw new ValidationResponseException(ErrorServiceCodeMessageConstants.SERVICE_NAME_NOT_NULL);
		}
		if(!StringUtils.isSizeBetween(serviceResponseResource.getName(), ServiceConstants.NAME_MIN_SIZE, ServiceConstants.NAME_MAX_SIZE)) {
			throw new ValidationResponseException(ErrorServiceCodeMessageConstants.SERVICE_NAME_SIZE, Arrays.asList(ServiceConstants.NAME_MIN_SIZE, ServiceConstants.NAME_MAX_SIZE));
		}
	}
	
	private void validateDescription(ServiceResponseResource serviceResponseResource) {
		if(StringUtils.isEmpty(serviceResponseResource.getDescription())) {
			throw new ValidationResponseException(ErrorServiceCodeMessageConstants.SERVICE_DESCRIPTION_NOT_NULL);
		}
		if(!StringUtils.isSizeBetween(serviceResponseResource.getDescription(), ServiceConstants.DESCRIPTION_MIN_SIZE, ServiceConstants.DESCRIPTION_MAX_SIZE)) {
			throw new ValidationResponseException(ErrorServiceCodeMessageConstants.SERVICE_DESCRIPTION_SIZE, Arrays.asList(ServiceConstants.DESCRIPTION_MIN_SIZE, ServiceConstants.DESCRIPTION_MAX_SIZE));
		}
	}
	
	private void validatePort(ServiceResponseResource serviceResponseResource) {
		if(serviceResponseResource.getPort() == null) {
			throw new ValidationResponseException(ErrorServiceCodeMessageConstants.SERVICE_PORT_NOT_NULL);
		}
	}
	
	private void validateGitUrl(ServiceResponseResource serviceResponseResource) {
		if(StringUtils.isEmpty(serviceResponseResource.getGitUrl())) {
			throw new ValidationResponseException(ErrorServiceCodeMessageConstants.SERVICE_GIT_URL_NOT_NULL);
		}
		if(!StringUtils.isSizeBetween(serviceResponseResource.getGitUrl(), ServiceConstants.NAME_MIN_SIZE, ServiceConstants.NAME_MAX_SIZE)) {
			throw new ValidationResponseException(ErrorServiceCodeMessageConstants.SERVICE_GIT_URL_SIZE, Arrays.asList(ServiceConstants.NAME_MIN_SIZE, ServiceConstants.NAME_MAX_SIZE));
		}
	}
	
	private void validateDocumentationUrl(ServiceResponseResource serviceResponseResource) {
		if(StringUtils.isEmpty(serviceResponseResource.getDocumentationUrl())) {
			throw new ValidationResponseException(ErrorServiceCodeMessageConstants.SERVICE_DOCUMENTATION_URL_NOT_NULL);
		}
		if(!StringUtils.isSizeBetween(serviceResponseResource.getDocumentationUrl(), ServiceConstants.DOCUMENTATION_URL_MIN_SIZE, ServiceConstants.DOCUMENTATION_URL_MAX_SIZE)) {
			throw new ValidationResponseException(ErrorServiceCodeMessageConstants.SERVICE_DOCUMENTATION_URL_SIZE, Arrays.asList(ServiceConstants.DOCUMENTATION_URL_MIN_SIZE, ServiceConstants.DOCUMENTATION_URL_MAX_SIZE));
		}
	}
}
