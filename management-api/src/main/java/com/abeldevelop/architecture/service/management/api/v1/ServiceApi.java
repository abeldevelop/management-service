package com.abeldevelop.architecture.service.management.api.v1;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.abeldevelop.architecture.library.common.dto.exception.ErrorResponseResource;
import com.abeldevelop.architecture.service.management.dto.application.ApplicationConstants;
import com.abeldevelop.architecture.service.management.dto.service.CreateServiceRequestResource;
import com.abeldevelop.architecture.service.management.dto.service.ServiceConstants;
import com.abeldevelop.architecture.service.management.dto.service.ServicePaginationResponseResource;
import com.abeldevelop.architecture.service.management.dto.service.ServiceResponseResource;
import com.abeldevelop.architecture.service.management.dto.service.ServiceSort;
import com.abeldevelop.architecture.service.management.dto.service.UpdateServiceRequestResource;
import com.abeldevelop.architecture.starter.api.SpringFoxConstants;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = { "Services" })
@RequestMapping("/v1/application/{id}/service")
public interface ServiceApi {

	@ApiOperation(value = "Create new service")
	@ApiResponses({
			@ApiResponse(code = 201, response = ServiceResponseResource.class, message = SpringFoxConstants.API_RESPONSE_CODE_201_MESSAGE),
			@ApiResponse(code = 400, response = ErrorResponseResource.class, message = SpringFoxConstants.API_RESPONSE_CODE_400_MESSAGE),
			@ApiResponse(code = 500, response = ErrorResponseResource.class, message = SpringFoxConstants.API_RESPONSE_CODE_500_MESSAGE) })
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ServiceResponseResource executeCreate(
			@ApiParam(name = "id", value = "ID of the application", required = true, example = ApplicationConstants.ID_FIELD_EXAMPLE) @PathVariable("id") String id,
			@ApiParam(name = "service", value = "service to create", required = true) @RequestBody CreateServiceRequestResource createServiceRequestResource);

	@ApiOperation(value = "Update service")
	@ApiResponses({
			@ApiResponse(code = 200, response = ServiceResponseResource.class, message = SpringFoxConstants.API_RESPONSE_CODE_200_MESSAGE),
			@ApiResponse(code = 400, response = ErrorResponseResource.class, message = SpringFoxConstants.API_RESPONSE_CODE_400_MESSAGE),
			@ApiResponse(code = 404, response = ErrorResponseResource.class, message = SpringFoxConstants.API_RESPONSE_CODE_404_MESSAGE),
			@ApiResponse(code = 500, response = ErrorResponseResource.class, message = SpringFoxConstants.API_RESPONSE_CODE_500_MESSAGE) })
	@PutMapping("/{name}")
	@ResponseStatus(HttpStatus.OK)
	public ServiceResponseResource executeUpdate(
			@ApiParam(name = "id", value = "ID of the application", required = true, example = ApplicationConstants.ID_FIELD_EXAMPLE) @PathVariable("id") String id,
			@ApiParam(name = "name", value = "Name of the service", required = true, example = ServiceConstants.NAME_FIELD_EXAMPLE) @PathVariable("name") String name,
			@ApiParam(name = "service", value = "service to updated", required = true) @RequestBody UpdateServiceRequestResource updateServiceRequestResource);

	@ApiOperation(value = "Delete service")
	@ApiResponses({
			@ApiResponse(code = 204, response = Void.class, message = SpringFoxConstants.API_RESPONSE_CODE_204_MESSAGE),
			@ApiResponse(code = 400, response = ErrorResponseResource.class, message = SpringFoxConstants.API_RESPONSE_CODE_400_MESSAGE),
			@ApiResponse(code = 404, response = ErrorResponseResource.class, message = SpringFoxConstants.API_RESPONSE_CODE_404_MESSAGE),
			@ApiResponse(code = 500, response = ErrorResponseResource.class, message = SpringFoxConstants.API_RESPONSE_CODE_500_MESSAGE) })
	@DeleteMapping("/{name}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void executeDelete(
			@ApiParam(name = "id", value = "ID of the application", required = true, example = ApplicationConstants.ID_FIELD_EXAMPLE) @PathVariable("id") String id,
			@ApiParam(name = "name", value = "Name of the service", required = true, example = ServiceConstants.NAME_FIELD_EXAMPLE) @PathVariable("name") String name);

	@ApiOperation(value = "Find service by name")
	@ApiResponses({
			@ApiResponse(code = 200, response = ServiceResponseResource.class, message = SpringFoxConstants.API_RESPONSE_CODE_204_MESSAGE),
			@ApiResponse(code = 400, response = ErrorResponseResource.class, message = SpringFoxConstants.API_RESPONSE_CODE_400_MESSAGE),
			@ApiResponse(code = 404, response = ErrorResponseResource.class, message = SpringFoxConstants.API_RESPONSE_CODE_404_MESSAGE),
			@ApiResponse(code = 500, response = ErrorResponseResource.class, message = SpringFoxConstants.API_RESPONSE_CODE_500_MESSAGE) })
	@GetMapping("/{name}")
	@ResponseStatus(HttpStatus.OK)
	public ServiceResponseResource executeFindById(
			@ApiParam(name = "id", value = "ID of the application", required = true, example = ApplicationConstants.ID_FIELD_EXAMPLE) @PathVariable("id") String id,
			@ApiParam(name = "name", value = "Name of the service", required = true, example = ServiceConstants.NAME_FIELD_EXAMPLE) @PathVariable("name") String name);

	@ApiOperation(value = "Find all services")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "page", value = "Number of page", required = false, example = "1", defaultValue = "1", dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "size", value = "Size of page", required = false, example = "1", defaultValue = "10", dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "sort", value = "Field and type to sort the fields", required = false, defaultValue = "NAME_DESC", example = "NAME_DESC", dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "name", value = "Part of service name to search", required = false, example = "fir", dataType = "string", paramType = "query")})
	@ApiResponses({
			@ApiResponse(code = 200, response = ServicePaginationResponseResource.class, message = SpringFoxConstants.API_RESPONSE_CODE_200_MESSAGE),
			@ApiResponse(code = 400, response = ErrorResponseResource.class, message = SpringFoxConstants.API_RESPONSE_CODE_400_MESSAGE),
			@ApiResponse(code = 404, response = ErrorResponseResource.class, message = SpringFoxConstants.API_RESPONSE_CODE_404_MESSAGE),
			@ApiResponse(code = 500, response = ErrorResponseResource.class, message = SpringFoxConstants.API_RESPONSE_CODE_500_MESSAGE) })
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public ServicePaginationResponseResource executeFindAll(
			@ApiParam(name = "id", value = "ID of the application", required = true, example = ApplicationConstants.ID_FIELD_EXAMPLE) @PathVariable("id") String id,
			@RequestParam(name = "page", required = false) Integer page,
			@RequestParam(name = "size", required = false) Integer size,
			@RequestParam(name = "sort", required = false) ServiceSort sort,
			@RequestParam(name = "name", required = false) String name);

}