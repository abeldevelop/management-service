package com.abeldevelop.architecture.service.management.repository.impl;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import com.abeldevelop.architecture.service.management.model.ApplicationEntity;
import com.abeldevelop.architecture.service.management.repository.ApplicationRepository;
import com.abeldevelop.architecture.service.management.repository.springdata.ApplicationSpringDataRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class ApplicationRepositoryImpl implements ApplicationRepository {
	
	private final ApplicationSpringDataRepository applicationSpringDataRepository;
	
	@Override
	public ApplicationEntity executeSave(ApplicationEntity applicationEntity) {
		return applicationSpringDataRepository.save(applicationEntity);
	}

	@Override
	public boolean executeExistsById(String id) {
		return applicationSpringDataRepository.existsById(id);
	}
	
	@Override
	public Optional<ApplicationEntity> executeFindById(String id) {
		return applicationSpringDataRepository.findById(id);
	}
	
	@Override
	public Optional<ApplicationEntity> executeFindByName(String name) {
		return applicationSpringDataRepository.findByName(name);
	}

	@Override
	public void executeDeleteById(String id) {
		applicationSpringDataRepository.deleteById(id);
	}

	@Override
	public Page<ApplicationEntity> executeFindAll(Pageable pageable) {
		return applicationSpringDataRepository.findAll(pageable);
	}

	@Override
	public Page<ApplicationEntity> executeFindAll(Specification<ApplicationEntity> spec, Pageable pageable) {
		return applicationSpringDataRepository.findAll(spec, pageable);
	}

}