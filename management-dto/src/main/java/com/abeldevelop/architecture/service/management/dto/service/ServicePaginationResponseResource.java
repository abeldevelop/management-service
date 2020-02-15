package com.abeldevelop.architecture.service.management.dto.service;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.abeldevelop.architecture.library.common.dto.pagination.PaginationResponseResource;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;
import lombok.ToString;

@ApiModel(description="Resource with services and pagination information")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class ServicePaginationResponseResource {

	@ApiModelProperty(notes=ServiceConstants.PAGINATION_FIELD_NOTES, required = true, position = 0)
    @NotNull
    private PaginationResponseResource pagination;

    @ApiModelProperty(notes=ServiceConstants.SERVICES_FIELD_NOTES, required = true, position = 1)
    @NotNull
    @Singular
    private List<ServiceResponseResource> services;
    
}