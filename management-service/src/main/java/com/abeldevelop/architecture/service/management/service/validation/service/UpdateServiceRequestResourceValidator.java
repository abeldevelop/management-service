package com.abeldevelop.architecture.service.management.service.validation.service;

import java.util.Arrays;

import org.springframework.stereotype.Component;

import com.abeldevelop.architecture.library.common.exception.client.ValidationRequestException;
import com.abeldevelop.architecture.library.common.factory.validation.ValidationResource;
import com.abeldevelop.architecture.library.common.util.StringUtils;
import com.abeldevelop.architecture.service.management.dto.service.ServiceConstants;
import com.abeldevelop.architecture.service.management.dto.service.UpdateServiceRequestResource;
import com.abeldevelop.architecture.service.management.service.constant.ErrorServiceCodeMessageConstants;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class UpdateServiceRequestResourceValidator implements ValidationResource {
	
	@Override
	public boolean areYouTheElement(String elementName) {
		return UpdateServiceRequestResource.class.getCanonicalName().equals(elementName);
	}

	@Override
	public void validate(Object toValidate) {
		UpdateServiceRequestResource updateServiceRequestResource = (UpdateServiceRequestResource) toValidate;
		validateDescription(updateServiceRequestResource);
		validateGitUrl(updateServiceRequestResource);
		validateDocumentationUrl(updateServiceRequestResource);
	}

	private void validateDescription(UpdateServiceRequestResource updateServiceRequestResource) {
		if(StringUtils.isEmpty(updateServiceRequestResource.getDescription())) {
			throw new ValidationRequestException(ErrorServiceCodeMessageConstants.SERVICE_DESCRIPTION_NOT_NULL);
		}
		if(!StringUtils.isSizeBetween(updateServiceRequestResource.getDescription(), ServiceConstants.DESCRIPTION_MIN_SIZE, ServiceConstants.DESCRIPTION_MAX_SIZE)) {
			throw new ValidationRequestException(ErrorServiceCodeMessageConstants.SERVICE_DESCRIPTION_SIZE, Arrays.asList(ServiceConstants.DESCRIPTION_MIN_SIZE, ServiceConstants.DESCRIPTION_MAX_SIZE));
		}
	}
	
	private void validateGitUrl(UpdateServiceRequestResource updateServiceRequestResource) {
		if(StringUtils.isEmpty(updateServiceRequestResource.getGitUrl())) {
			throw new ValidationRequestException(ErrorServiceCodeMessageConstants.SERVICE_GIT_URL_NOT_NULL);
		}
		if(!StringUtils.isSizeBetween(updateServiceRequestResource.getGitUrl(), ServiceConstants.GIT_URL_MIN_SIZE, ServiceConstants.GIT_URL_MAX_SIZE)) {
			throw new ValidationRequestException(ErrorServiceCodeMessageConstants.SERVICE_GIT_URL_SIZE, Arrays.asList(ServiceConstants.GIT_URL_MIN_SIZE, ServiceConstants.GIT_URL_MAX_SIZE));
		}
	}
	
	private void validateDocumentationUrl(UpdateServiceRequestResource updateServiceRequestResource) {
		if(StringUtils.isEmpty(updateServiceRequestResource.getDocumentationUrl())) {
			throw new ValidationRequestException(ErrorServiceCodeMessageConstants.SERVICE_DOCUMENTATION_URL_NOT_NULL);
		}
		if(!StringUtils.isSizeBetween(updateServiceRequestResource.getDocumentationUrl(), ServiceConstants.DOCUMENTATION_URL_MIN_SIZE, ServiceConstants.DOCUMENTATION_URL_MAX_SIZE)) {
			throw new ValidationRequestException(ErrorServiceCodeMessageConstants.SERVICE_DOCUMENTATION_URL_SIZE, Arrays.asList(ServiceConstants.DOCUMENTATION_URL_MIN_SIZE, ServiceConstants.DOCUMENTATION_URL_MAX_SIZE));
		}
	}
	
}
