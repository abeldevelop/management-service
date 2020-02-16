package com.abeldevelop.architecture.service.management.repository.springdata;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.abeldevelop.architecture.service.management.model.ServiceEntity;

public interface ServiceSpringDataRepository extends JpaRepository<ServiceEntity, String>, JpaSpecificationExecutor<ServiceEntity> {

	public Optional<ServiceEntity> findByName(String name);

	public Optional<ServiceEntity> findByPort(Integer port);
	
//	@Query("SELECT s FROM Service ORDER BY s.port DESC")
//	public List<ServiceEntity> findAllOrderByPortDesc();

	
	@Query("SELECT MAX(s.port) FROM ServiceEntity s")
	public Integer findMaxPort();
}
