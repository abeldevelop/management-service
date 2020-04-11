package com.abeldevelop.architecture.service.management.repository.impl;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import com.abeldevelop.architecture.service.management.model.RouteEntity;
import com.abeldevelop.architecture.service.management.repository.RouteRepository;
import com.abeldevelop.architecture.service.management.repository.springdata.RouteSpringDataRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class RouteRepositoryImpl implements RouteRepository {
	
	private final RouteSpringDataRepository routeSpringDataRepository;

	@Override
	public RouteEntity executeSave(RouteEntity routeEntity) {
		return routeSpringDataRepository.save(routeEntity);
	}

	@Override
	public void executeDeleteById(Long id) {
		routeSpringDataRepository.deleteById(id);
	}

	@Override
	public Optional<RouteEntity> executeFindById(Long id) {
		return routeSpringDataRepository.findById(id);
	}

	@Override
	public Page<RouteEntity> executeFindAll(Pageable pageable) {
		return routeSpringDataRepository.findAll(pageable);
	}

	@Override
	public Page<RouteEntity> executeFindAll(Specification<RouteEntity> spec, Pageable pageable) {
		return routeSpringDataRepository.findAll(spec, pageable);
	}

	
	
}
