package com.abeldevelop.architecture.service.management.model;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(ApplicationEntity.class)
public class ApplicationEntity_ {

	public static volatile SingularAttribute<ApplicationEntity, String> id;
	public static volatile SingularAttribute<ApplicationEntity, String> name;
	public static volatile SingularAttribute<ApplicationEntity, String> description;
	public static volatile SingularAttribute<ApplicationEntity, Boolean> enabled;
	public static volatile SingularAttribute<ApplicationEntity, String> homeUri;
	public static volatile SingularAttribute<ApplicationEntity, String> documentationUrl;
	public static volatile SingularAttribute<ApplicationEntity, Integer> version;
	public static volatile ListAttribute<ApplicationEntity, ServiceEntity> services;
	
}