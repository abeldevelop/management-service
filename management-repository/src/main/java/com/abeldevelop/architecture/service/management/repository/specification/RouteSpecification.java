package com.abeldevelop.architecture.service.management.repository.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.abeldevelop.architecture.service.management.model.RouteEntity;
import com.abeldevelop.architecture.service.management.model.RouteEntity_;
import com.abeldevelop.architecture.service.management.model.ServiceEntity_;

@Component
public class RouteSpecification {

	public Specification<RouteEntity> serviceLike(String service) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.upper(root.join(RouteEntity_.service).get(ServiceEntity_.name)), "%" + service.toUpperCase() + "%");
    }
	
}