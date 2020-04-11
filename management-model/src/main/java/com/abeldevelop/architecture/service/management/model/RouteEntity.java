package com.abeldevelop.architecture.service.management.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.abeldevelop.architecture.library.common.enums.Method;

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
@Entity
@Table(name = "routes", schema = "management_db", uniqueConstraints = @UniqueConstraint(columnNames = {"application_id", "method", "path"}))
public class RouteEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "path", nullable = false)
	private String path;
	
	@Column(name = "method", nullable = false)
	private Method method;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private ServiceEntity service;
	
}