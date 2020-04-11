package com.abeldevelop.architecture.service.management.dto.route;

import javax.validation.constraints.NotNull;

import com.abeldevelop.architecture.library.common.enums.Method;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ApiModel(description="Route resource")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@ToString
public class RouteResponseResource {

	@ApiModelProperty(notes=RouteConstants.ID_FIELD_NOTES, example=RouteConstants.ID_FIELD_EXAMPLE, required = true, position = 0)
	@NotNull
	private Long id;
	
	@ApiModelProperty(notes=RouteConstants.NAME_FIELD_NOTES, example=RouteConstants.NAME_FIELD_EXAMPLE, required = true, position = 1)
	@NotNull
	private String path;
	
	@ApiModelProperty(notes=RouteConstants.NAME_FIELD_NOTES, example=RouteConstants.NAME_FIELD_EXAMPLE, required = true, position = 2)
	@NotNull
	private Method method;
	
}