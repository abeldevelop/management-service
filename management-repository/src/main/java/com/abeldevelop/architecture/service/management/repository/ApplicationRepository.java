package com.abeldevelop.architecture.service.management.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.abeldevelop.architecture.service.management.model.ApplicationEntity;

public interface ApplicationRepository {

	public ApplicationEntity executeSave(ApplicationEntity applicationEntity);
    
	public boolean executeExistsById(String id);
	
    public Optional<ApplicationEntity> executeFindById(String id);
    
    public Optional<ApplicationEntity> executeFindByName(String name);
    
    public void executeDeleteById(String id);

    public Page<ApplicationEntity> executeFindAll(Pageable pageable);
    
    public Page<ApplicationEntity> executeFindAll(Specification<ApplicationEntity> spec, Pageable pageable);
    
}