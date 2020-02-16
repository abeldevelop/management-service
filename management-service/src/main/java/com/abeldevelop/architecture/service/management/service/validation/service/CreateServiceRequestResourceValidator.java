package com.abeldevelop.architecture.service.management.service.validation.service;


import java.util.Arrays;

import org.springframework.stereotype.Component;

import com.abeldevelop.architecture.library.common.exception.client.ValidationRequestException;
import com.abeldevelop.architecture.library.common.factory.validation.ValidationResource;
import com.abeldevelop.architecture.library.common.util.StringUtils;
import com.abeldevelop.architecture.service.management.dto.service.CreateServiceRequestResource;
import com.abeldevelop.architecture.service.management.dto.service.ServiceConstants;
import com.abeldevelop.architecture.service.management.service.constant.ErrorServiceCodeMessageConstants;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class CreateServiceRequestResourceValidator implements ValidationResource {
	
	@Override
	public boolean areYouTheElement(String elementName) {
		return CreateServiceRequestResource.class.getCanonicalName().equals(elementName);
	}

	@Override
	public void validate(Object toValidate) {
		CreateServiceRequestResource createServiceRequestResource = (CreateServiceRequestResource) toValidate;
		validateName(createServiceRequestResource);
		validateDescription(createServiceRequestResource);
		validateGitUrl(createServiceRequestResource);
		validateDocumentationUrl(createServiceRequestResource);
	}

	private void validateName(CreateServiceRequestResource createServiceRequestResource) {
		if(StringUtils.isEmpty(createServiceRequestResource.getName())) {
			throw new ValidationRequestException(ErrorServiceCodeMessageConstants.SERVICE_NAME_NOT_NULL);
		}
		if(!StringUtils.isSizeBetween(createServiceRequestResource.getName(), ServiceConstants.NAME_MIN_SIZE, ServiceConstants.NAME_MAX_SIZE)) {
			throw new ValidationRequestException(ErrorServiceCodeMessageConstants.SERVICE_NAME_SIZE, Arrays.asList(ServiceConstants.NAME_MIN_SIZE, ServiceConstants.NAME_MAX_SIZE));
		}
	}
	
	private void validateDescription(CreateServiceRequestResource createServiceRequestResource) {
		if(StringUtils.isEmpty(createServiceRequestResource.getDescription())) {
			throw new ValidationRequestException(ErrorServiceCodeMessageConstants.SERVICE_DESCRIPTION_NOT_NULL);
		}
		if(!StringUtils.isSizeBetween(createServiceRequestResource.getDescription(), ServiceConstants.DESCRIPTION_MIN_SIZE, ServiceConstants.DESCRIPTION_MAX_SIZE)) {
			throw new ValidationRequestException(ErrorServiceCodeMessageConstants.SERVICE_DESCRIPTION_SIZE, Arrays.asList(ServiceConstants.DESCRIPTION_MIN_SIZE, ServiceConstants.DESCRIPTION_MAX_SIZE));
		}
	}
	
	private void validateGitUrl(CreateServiceRequestResource createServiceRequestResource) {
		if(StringUtils.isEmpty(createServiceRequestResource.getGitUrl())) {
			throw new ValidationRequestException(ErrorServiceCodeMessageConstants.SERVICE_GIT_URL_NOT_NULL);
		}
		if(!StringUtils.isSizeBetween(createServiceRequestResource.getGitUrl(), ServiceConstants.GIT_URL_MIN_SIZE, ServiceConstants.GIT_URL_MAX_SIZE)) {
			throw new ValidationRequestException(ErrorServiceCodeMessageConstants.SERVICE_GIT_URL_SIZE, Arrays.asList(ServiceConstants.GIT_URL_MIN_SIZE, ServiceConstants.GIT_URL_MAX_SIZE));
		}
	}
	
	private void validateDocumentationUrl(CreateServiceRequestResource createServiceRequestResource) {
		if(StringUtils.isEmpty(createServiceRequestResource.getDocumentationUrl())) {
			throw new ValidationRequestException(ErrorServiceCodeMessageConstants.SERVICE_DOCUMENTATION_URL_NOT_NULL);
		}
		if(!StringUtils.isSizeBetween(createServiceRequestResource.getDocumentationUrl(), ServiceConstants.DOCUMENTATION_URL_MIN_SIZE, ServiceConstants.DOCUMENTATION_URL_MAX_SIZE)) {
			throw new ValidationRequestException(ErrorServiceCodeMessageConstants.SERVICE_DOCUMENTATION_URL_SIZE, Arrays.asList(ServiceConstants.DOCUMENTATION_URL_MIN_SIZE, ServiceConstants.DOCUMENTATION_URL_MAX_SIZE));
		}
	}
}
