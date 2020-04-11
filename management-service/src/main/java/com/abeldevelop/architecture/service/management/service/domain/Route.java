package com.abeldevelop.architecture.service.management.service.domain;

import com.abeldevelop.architecture.library.common.enums.Method;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@ToString
public class Route {

	private Long id;
	private String path;
	private Method method;
	
}