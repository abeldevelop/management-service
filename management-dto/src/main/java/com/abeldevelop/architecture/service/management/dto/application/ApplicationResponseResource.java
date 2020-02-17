package com.abeldevelop.architecture.service.management.dto.application;

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

@ApiModel(description="Application resource")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class ApplicationResponseResource {

	@ApiModelProperty(notes=ApplicationConstants.ID_FIELD_NOTES, example=ApplicationConstants.ID_FIELD_EXAMPLE, required = true, position = 0)
    @NotNull
    private String id;
	
	@ApiModelProperty(notes=ApplicationConstants.NAME_FIELD_NOTES, example=ApplicationConstants.NAME_FIELD_EXAMPLE, required = true, position = 1)
	@NotNull
	private String name;
	
	@ApiModelProperty(notes=ApplicationConstants.DESCRIPTION_FIELD_NOTES, required = true, position = 2)
	@NotNull
	private String description;
	
	@ApiModelProperty(notes=ApplicationConstants.ENABLED_FIELD_NOTES, example=ApplicationConstants.ENABLED_FIELD_EXAMPLE, required = true, position = 3)
	@NotNull
	private Boolean enabled;
	
	@ApiModelProperty(notes=ApplicationConstants.HOME_URI_FIELD_NOTES, example=ApplicationConstants.HOME_URI_FIELD_NOTES, required = true, position = 4)
	@NotNull
	private String homeUri;
	
	@ApiModelProperty(notes=ApplicationConstants.DOCUMENTATION_URL_FIELD_NOTES, example=ApplicationConstants.DOCUMENTATION_URL_FIELD_EXAMPLE, required = true, position = 5)
	@NotNull
	private String documentationUrl;
	
}