package com.abeldevelop.architecture.service.management.repository.springdata;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.abeldevelop.architecture.service.management.model.RouteEntity;

public interface RouteSpringDataRepository extends JpaRepository<RouteEntity, Long>, JpaSpecificationExecutor<RouteEntity> {

}
