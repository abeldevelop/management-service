package com.abeldevelop.architecture.service.management.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@ToString
@Table(name = "services", schema = "management_db")
@Entity
public class ServiceEntity {

	@Id
	@Column(name = "name", nullable = false, unique = true)
	private String name;

	@Column(name = "description", nullable = false, unique = true)
	private String description;
	
	@Column(name = "port", nullable = false, unique = true)
	private Integer port;
	
	@Column(name = "git_url", nullable = false)
	private String gitUrl;
	
	@Column(name = "documentation_url", nullable = false)
	private String documentationUrl;
	
	 @ManyToOne(fetch = FetchType.LAZY)
	 private ApplicationEntity application;
}