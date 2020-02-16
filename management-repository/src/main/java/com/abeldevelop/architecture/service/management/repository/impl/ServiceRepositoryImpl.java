package com.abeldevelop.architecture.service.management.repository.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import com.abeldevelop.architecture.service.management.model.ServiceEntity;
import com.abeldevelop.architecture.service.management.repository.ServiceRepository;
import com.abeldevelop.architecture.service.management.repository.springdata.ServiceSpringDataRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class ServiceRepositoryImpl implements ServiceRepository {
	
	private final ServiceSpringDataRepository serviceSpringDataRepository;
	
	@Override
	public ServiceEntity executeSave(ServiceEntity serviceEntity) {
		return serviceSpringDataRepository.save(serviceEntity);
	}

	@Override
	public boolean executeExistsByName(String name) {
		return serviceSpringDataRepository.existsById(name);
	}

	@Override
	public boolean executeExistsByPort(Integer port) {
		Optional<ServiceEntity> serviceEntityOptional = serviceSpringDataRepository.findByPort(port);
		return serviceEntityOptional.isPresent();
	}
	
	@Override
	public Optional<ServiceEntity> executeFindByName(String name) {
		return serviceSpringDataRepository.findByName(name);
	}

	@Override
	public void executeDeleteByName(String name) {
		serviceSpringDataRepository.deleteById(name);
	}

	@Override
	public Page<ServiceEntity> executeFindAll(Pageable pageable) {
		return serviceSpringDataRepository.findAll(pageable);
	}

	@Override
	public Page<ServiceEntity> executeFindAll(Specification<ServiceEntity> spec, Pageable pageable) {
		return serviceSpringDataRepository.findAll(spec, pageable);
	}

	@Override
	public Integer executeFindLastPort() {
//		List<ServiceEntity> services = serviceSpringDataRepository.findAllOrderByPortDesc();
//		if(services == null || services.isEmpty()) {
//			return 8001;
//		} else {
//			return services.get(services.size()-1).getPort() + 1;
//		}
		Integer maxPort = serviceSpringDataRepository.findMaxPort();
		if(maxPort == null) {
			return 8001;
		} else {
			return maxPort + 1;
		}
	}
	
}
