package com.abeldevelop.architecture.service.management.dto.application;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ApiModel(description="Update Application resource")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class UpdateApplicationRequestResource {

	@ApiModelProperty(notes=ApplicationConstants.DESCRIPTION_FIELD_NOTES, required = true, position = 2)
	@NotNull
	@Size(min = ApplicationConstants.DESCRIPTION_MIN_SIZE, max = ApplicationConstants.DESCRIPTION_MAX_SIZE)
	private String description;
	
	@ApiModelProperty(notes=ApplicationConstants.ENABLED_FIELD_NOTES, example=ApplicationConstants.ENABLED_FIELD_EXAMPLE, required = true, position = 3)
	@NotNull
	private Boolean enabled;
	
	@ApiModelProperty(notes=ApplicationConstants.HOME_URI_FIELD_NOTES, example=ApplicationConstants.HOME_URI_FIELD_EXAMPLE, required = true, position = 4)
	@NotNull
	@Size(min = ApplicationConstants.HOME_URI_MIN_SIZE, max = ApplicationConstants.HOME_URI_MAX_SIZE)
	private String homeUri;
	
	@ApiModelProperty(notes=ApplicationConstants.DOCUMENTATION_URL_FIELD_NOTES, example=ApplicationConstants.DOCUMENTATION_URL_FIELD_EXAMPLE, required = true, position = 5)
	@NotNull
	@Size(min = ApplicationConstants.DOCUMENTATION_URL_MIN_SIZE, max = ApplicationConstants.DOCUMENTATION_URL_MAX_SIZE)
	private String documentationUrl;
	
	@ApiModelProperty(notes=ApplicationConstants.VERSION_FIELD_NOTES, example=ApplicationConstants.VERSION_FIELD_EXAMPLE, required = true, position = 6)
	@NotNull
	private Integer version;
	
}