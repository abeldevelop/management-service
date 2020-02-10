package com.abeldevelop.architecture.service.management.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.abeldevelop.architecture.library.common.model.BaseEntity;

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
public class ServiceEntity extends BaseEntity {

	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "port", nullable = false)
	private Integer port;
	
	@Column(name = "git_url", nullable = false)
	private String gitUrl;
	
	@Column(name = "documentation_url", nullable = false)
	private String documentationUrl;
	
}