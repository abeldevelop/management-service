package com.abeldevelop.architecture.service.management.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.abeldevelop.architecture.service.management.model.RouteEntity;

public interface RouteRepository {

	public RouteEntity executeSave(RouteEntity routeEntity);
    
    public void executeDeleteById(Long id);
    
    public Optional<RouteEntity> executeFindById(Long id);

    public Page<RouteEntity> executeFindAll(Pageable pageable);
    
    public Page<RouteEntity> executeFindAll(Specification<RouteEntity> spec, Pageable pageable);
    
}
