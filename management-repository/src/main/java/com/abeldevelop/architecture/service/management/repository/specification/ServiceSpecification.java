package com.abeldevelop.architecture.service.management.repository.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.abeldevelop.architecture.service.management.model.ServiceEntity;
import com.abeldevelop.architecture.service.management.model.ServiceEntity_;

@Component
public class ServiceSpecification {

	public Specification<ServiceEntity> nameLike(String name) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.upper(root.get(ServiceEntity_.name)), "%" + name.toUpperCase() + "%");
    }
	
}