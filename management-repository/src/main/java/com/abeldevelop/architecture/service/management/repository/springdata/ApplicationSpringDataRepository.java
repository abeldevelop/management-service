package com.abeldevelop.architecture.service.management.repository.springdata;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.abeldevelop.architecture.service.management.model.ApplicationEntity;

public interface ApplicationSpringDataRepository extends JpaRepository<ApplicationEntity, String>, JpaSpecificationExecutor<ApplicationEntity> {

	public Optional<ApplicationEntity> findByName(String name);

}
