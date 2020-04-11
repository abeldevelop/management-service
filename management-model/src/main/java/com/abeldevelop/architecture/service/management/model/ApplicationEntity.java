package com.abeldevelop.architecture.service.management.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "applications", schema = "management_db")
@Entity
public class ApplicationEntity {

	@Id
	@Column(name = "id", nullable = false, unique = true)
	private String id;
	
	@Column(name = "name", nullable = false, unique = true)
	private String name;
	
	@Column(name = "description", nullable = false)
	private String description;
	
	@Column(name = "enabled", nullable = false)
	private Boolean enabled;
	
	@Column(name = "home_uri", nullable = false)
	private String homeUri;
	
	@Column(name = "documentation_url", nullable = false)
	private String documentationUrl;
	
}