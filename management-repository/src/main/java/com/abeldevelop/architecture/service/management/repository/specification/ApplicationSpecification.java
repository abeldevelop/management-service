package com.abeldevelop.architecture.service.management.repository.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.abeldevelop.architecture.service.management.model.ApplicationEntity;
import com.abeldevelop.architecture.service.management.model.ApplicationEntity_;

@Component
public class ApplicationSpecification {

	public Specification<ApplicationEntity> nameLike(String name) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.upper(root.get(ApplicationEntity_.name)), "%" + name.toUpperCase() + "%");
    }
	
	public Specification<ApplicationEntity> enabled(Boolean enabled) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get(ApplicationEntity_.enabled), enabled);
    }
	
}