package com.abeldevelop.architecture.service.management.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import com.abeldevelop.architecture.library.common.model.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;
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
public class ApplicationEntity extends BaseEntity {

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
	
	@Version
	@Column(name = "version", nullable = false)
	private Integer version;
	
	@OneToMany(orphanRemoval = true, fetch = FetchType.EAGER)
	@JoinColumn(name = "application_id")
	@Singular
	private List<ServiceEntity> services;
	
}