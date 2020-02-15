package com.abeldevelop.architecture.service.management.dto.service;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ApiModel(description="Create Service resource")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class CreateServiceRequestResource {

	@ApiModelProperty(notes=ServiceConstants.NAME_FIELD_NOTES, example=ServiceConstants.NAME_FIELD_EXAMPLE, required = true, position = 0)
	@NotNull
	private String name;
	
	@ApiModelProperty(notes=ServiceConstants.DESCRIPTION_FIELD_NOTES, required = true, position = 1)
	@NotNull
	private String description;
	
	@ApiModelProperty(notes=ServiceConstants.PORT_FIELD_NOTES, example=ServiceConstants.PORT_FIELD_EXAMPLE + "If null give one enabled", required = true, position = 2)
	private Integer port;
	
	@ApiModelProperty(notes=ServiceConstants.GIT_URL_FIELD_NOTES, example=ServiceConstants.GIT_URL_FIELD_EXAMPLE, required = true, position = 3)
	@NotNull
	private String gitUrl;
	
	@ApiModelProperty(notes=ServiceConstants.DOCUMENTATION_URL_FIELD_NOTES, example=ServiceConstants.DOCUMENTATION_URL_FIELD_EXAMPLE, required = true, position = 4)
	@NotNull
	private String documentationUrl;
	
}
