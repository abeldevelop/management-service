package com.abeldevelop.architecture.service.management.service.mapper;

import org.springframework.stereotype.Component;

import com.abeldevelop.architecture.service.management.dto.application.ApplicationResponseResource;
import com.abeldevelop.architecture.service.management.dto.application.CreateApplicationRequestResource;
import com.abeldevelop.architecture.service.management.dto.application.UpdateApplicationRequestResource;
import com.abeldevelop.architecture.service.management.model.ApplicationEntity;
import com.abeldevelop.architecture.service.management.service.domain.Application;

@Component
public class ApplicationMapper {

	public Application mapResourceToDomain(CreateApplicationRequestResource createApplicationRequestResource) {
        return Application.builder()
        		.name(createApplicationRequestResource.getName())
        		.description(createApplicationRequestResource.getDescription())
        		.enabled(createApplicationRequestResource.getEnabled())
        		.homeUri(createApplicationRequestResource.getHomeUri())
        		.documentationUrl(createApplicationRequestResource.getDocumentationUrl())
                .build();
    }
    
    public Application mapResourceToDomain(String id, UpdateApplicationRequestResource updateApplicationRequestResource) {
        return Application.builder()
        		.id(id)
        		.description(updateApplicationRequestResource.getDescription())
        		.enabled(updateApplicationRequestResource.getEnabled())
        		.homeUri(updateApplicationRequestResource.getHomeUri())
        		.documentationUrl(updateApplicationRequestResource.getDocumentationUrl())
        		.version(updateApplicationRequestResource.getVersion())
                .build();
    }
    
    public ApplicationResponseResource mapDomainToResource(Application application) {
        return ApplicationResponseResource.builder()
        		.id(application.getId())
        		.name(application.getName())
        		.description(application.getDescription())
        		.enabled(application.getEnabled())
        		.homeUri(application.getHomeUri())
        		.documentationUrl(application.getDocumentationUrl())
        		.version(application.getVersion())
                .build();
    }
    
    public ApplicationEntity mapDomainToEntity(Application application) {
        return ApplicationEntity.builder()
        		.name(application.getName())
        		.description(application.getDescription())
        		.enabled(application.getEnabled())
        		.homeUri(application.getHomeUri())
        		.documentationUrl(application.getDocumentationUrl())
        		.version(application.getVersion())
                .build();
    }
    
    public ApplicationEntity mapDomainToEntity(Application application, ApplicationEntity applicationEntity) {
    	applicationEntity.setName(application.getName());
    	applicationEntity.setDescription(application.getDescription());
    	applicationEntity.setEnabled(application.getEnabled());
    	applicationEntity.setHomeUri(application.getHomeUri());
    	applicationEntity.setDocumentationUrl(application.getDocumentationUrl());
    	applicationEntity.setVersion(application.getVersion());
    	return applicationEntity;
    }
    
    public Application mapEntityToDomain(ApplicationEntity applicationEntity) {
        return Application.builder()
        		.id(applicationEntity.getId())
        		.name(applicationEntity.getName())
        		.description(applicationEntity.getDescription())
        		.enabled(applicationEntity.getEnabled())
        		.homeUri(applicationEntity.getHomeUri())
        		.documentationUrl(applicationEntity.getDocumentationUrl())
        		.version(applicationEntity.getVersion())
                .build();
    }
}