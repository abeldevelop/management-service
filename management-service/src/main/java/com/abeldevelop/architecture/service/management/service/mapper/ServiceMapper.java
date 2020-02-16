package com.abeldevelop.architecture.service.management.service.mapper;

import org.springframework.stereotype.Component;

import com.abeldevelop.architecture.service.management.dto.service.CreateServiceRequestResource;
import com.abeldevelop.architecture.service.management.dto.service.ServiceResponseResource;
import com.abeldevelop.architecture.service.management.dto.service.UpdateServiceRequestResource;
import com.abeldevelop.architecture.service.management.model.ServiceEntity;
import com.abeldevelop.architecture.service.management.service.domain.Service;

@Component
public class ServiceMapper {

	public Service mapResourceToDomain(CreateServiceRequestResource createServiceRequestResource) {
		return Service.builder()
				.name(createServiceRequestResource.getName())
				.description(createServiceRequestResource.getDescription())
				.port(createServiceRequestResource.getPort())
				.gitUrl(createServiceRequestResource.getGitUrl())
				.documentationUrl(createServiceRequestResource.getDocumentationUrl())
				.build();
	}
	
	public Service mapResourceToDomain(String name, UpdateServiceRequestResource updateServiceRequestResource) {
		return Service.builder()
				.name(name)
				.description(updateServiceRequestResource.getDescription())
				.gitUrl(updateServiceRequestResource.getGitUrl())
				.documentationUrl(updateServiceRequestResource.getDocumentationUrl())
				.build();
	}
	
	public ServiceResponseResource mapDomainToResource(Service service) {
		return ServiceResponseResource.builder()
				.name(service.getName())
				.description(service.getDescription())
				.port(service.getPort())
				.gitUrl(service.getGitUrl())
				.documentationUrl(service.getDocumentationUrl())
				.build();
	}
	
	public ServiceEntity mapDomainToEntity(Service service) {
		return ServiceEntity.builder()
				.name(service.getName())
				.description(service.getDescription())
				.port(service.getPort())
				.gitUrl(service.getGitUrl())
				.documentationUrl(service.getDocumentationUrl())
				.build();
	}
	
	public ServiceEntity mapDomainToEntity(Service service, ServiceEntity serviceEntity) {
		serviceEntity.setDescription(service.getDescription());
		serviceEntity.setPort(service.getPort());
		serviceEntity.setGitUrl(service.getGitUrl());
		serviceEntity.setDocumentationUrl(service.getDocumentationUrl());
		return serviceEntity;
	}
	
	public Service mapEntityToDomain(ServiceEntity serviceEntity) {
		return Service.builder()
				.name(serviceEntity.getName())
				.description(serviceEntity.getDescription())
				.port(serviceEntity.getPort())
				.gitUrl(serviceEntity.getGitUrl())
				.documentationUrl(serviceEntity.getDocumentationUrl())
				.build();
	}
}
