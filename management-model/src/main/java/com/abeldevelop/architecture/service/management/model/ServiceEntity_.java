package com.abeldevelop.architecture.service.management.model;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(ServiceEntity.class)
public class ServiceEntity_ {

	public static volatile SingularAttribute<ServiceEntity, String> name;
	public static volatile SingularAttribute<ServiceEntity, String> description;
	public static volatile SingularAttribute<ServiceEntity, Integer> port;
	public static volatile SingularAttribute<ServiceEntity, String> gitUrl;
	public static volatile SingularAttribute<ServiceEntity, String> documentationUrl;
	public static volatile SingularAttribute<ServiceEntity, ApplicationEntity> application;
	
}