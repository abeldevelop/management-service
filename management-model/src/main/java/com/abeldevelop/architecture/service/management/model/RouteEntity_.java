package com.abeldevelop.architecture.service.management.model;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.abeldevelop.architecture.library.common.enums.Method;

@StaticMetamodel(RouteEntity.class)
public class RouteEntity_ {

	public static volatile SingularAttribute<RouteEntity, Long> id;
	public static volatile SingularAttribute<RouteEntity, String> path;
	public static volatile SingularAttribute<RouteEntity, Method> method;
	public static volatile SingularAttribute<RouteEntity, ServiceEntity> service;
	
}