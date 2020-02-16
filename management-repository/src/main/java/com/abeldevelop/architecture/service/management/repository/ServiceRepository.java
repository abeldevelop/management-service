package com.abeldevelop.architecture.service.management.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.abeldevelop.architecture.service.management.model.ServiceEntity;

public interface ServiceRepository {

	public ServiceEntity executeSave(ServiceEntity serviceEntity);
    
	public boolean executeExistsByName(String name);
	
	public boolean executeExistsByPort(Integer port);
	
    public Optional<ServiceEntity> executeFindByName(String name);
    
    public void executeDeleteByName(String name);

    public Page<ServiceEntity> executeFindAll(Pageable pageable);
    
    public Page<ServiceEntity> executeFindAll(Specification<ServiceEntity> spec, Pageable pageable);
    
    public Integer executeFindLastPort();
}
