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
import com.abeldevelop.architecture.service.management.dto.application.ApplicationPaginationResponseResource;
import com.abeldevelop.architecture.service.management.dto.application.ApplicationResponseResource;
import com.abeldevelop.architecture.service.management.dto.application.ApplicationSort;
import com.abeldevelop.architecture.service.management.dto.application.CreateApplicationRequestResource;
import com.abeldevelop.architecture.service.management.dto.application.UpdateApplicationRequestResource;
import com.abeldevelop.architecture.starter.api.SpringFoxConstants;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = { "Applications" })
@RequestMapping("/v1/application")
public interface ApplicationApi {

	@ApiOperation(value = "Create new application")
	@ApiResponses({
			@ApiResponse(code = 201, response = ApplicationResponseResource.class, message = SpringFoxConstants.API_RESPONSE_CODE_201_MESSAGE),
			@ApiResponse(code = 400, response = ErrorResponseResource.class, message = SpringFoxConstants.API_RESPONSE_CODE_400_MESSAGE),
			@ApiResponse(code = 500, response = ErrorResponseResource.class, message = SpringFoxConstants.API_RESPONSE_CODE_500_MESSAGE) })
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ApplicationResponseResource executeCreate(
			@ApiParam(name = "application", value = "application to create", required = true) @RequestBody CreateApplicationRequestResource createApplicationRequestResource);

	@ApiOperation(value = "Update application")
	@ApiResponses({
			@ApiResponse(code = 200, response = ApplicationResponseResource.class, message = SpringFoxConstants.API_RESPONSE_CODE_200_MESSAGE),
			@ApiResponse(code = 400, response = ErrorResponseResource.class, message = SpringFoxConstants.API_RESPONSE_CODE_400_MESSAGE),
			@ApiResponse(code = 404, response = ErrorResponseResource.class, message = SpringFoxConstants.API_RESPONSE_CODE_404_MESSAGE),
			@ApiResponse(code = 500, response = ErrorResponseResource.class, message = SpringFoxConstants.API_RESPONSE_CODE_500_MESSAGE) })
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ApplicationResponseResource executeUpdate(
			@ApiParam(name = "id", value = "ID of the application", required = true, example = "blog") @PathVariable("id") String id,
			@ApiParam(name = "application", value = "application to updated", required = true) @RequestBody UpdateApplicationRequestResource updateApplicationRequestResource);

	@ApiOperation(value = "Delete application")
	@ApiResponses({
			@ApiResponse(code = 204, response = Void.class, message = SpringFoxConstants.API_RESPONSE_CODE_204_MESSAGE),
			@ApiResponse(code = 400, response = ErrorResponseResource.class, message = SpringFoxConstants.API_RESPONSE_CODE_400_MESSAGE),
			@ApiResponse(code = 404, response = ErrorResponseResource.class, message = SpringFoxConstants.API_RESPONSE_CODE_404_MESSAGE),
			@ApiResponse(code = 500, response = ErrorResponseResource.class, message = SpringFoxConstants.API_RESPONSE_CODE_500_MESSAGE) })
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void executeDelete(
			@ApiParam(name = "id", value = "ID of the application", required = true, example = "blog") @PathVariable("id") String id);

	@ApiOperation(value = "Find application by ID")
	@ApiResponses({
			@ApiResponse(code = 200, response = ApplicationResponseResource.class, message = SpringFoxConstants.API_RESPONSE_CODE_204_MESSAGE),
			@ApiResponse(code = 400, response = ErrorResponseResource.class, message = SpringFoxConstants.API_RESPONSE_CODE_400_MESSAGE),
			@ApiResponse(code = 404, response = ErrorResponseResource.class, message = SpringFoxConstants.API_RESPONSE_CODE_404_MESSAGE),
			@ApiResponse(code = 500, response = ErrorResponseResource.class, message = SpringFoxConstants.API_RESPONSE_CODE_500_MESSAGE) })
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ApplicationResponseResource executeFindById(
			@ApiParam(name = "id", value = "ID of the application", required = true, example = "blog") @PathVariable("id") String id);

	@ApiOperation(value = "Find all applications")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "page", value = "Number of page", required = false, example = "1", defaultValue = "1", dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "size", value = "Size of page", required = false, example = "1", defaultValue = "10", dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "sort", value = "Field and type to sort the fields", required = false, defaultValue = "NAME_DESC", example = "NAME_DESC", dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "name", value = "Part of application name to search", required = false, example = "fir", dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "enabled", value = "Filter by enabled or disabled", required = false, defaultValue = "true", example = "true", dataType = "string", paramType = "query")})
	@ApiResponses({
			@ApiResponse(code = 200, response = ApplicationPaginationResponseResource.class, message = SpringFoxConstants.API_RESPONSE_CODE_200_MESSAGE),
			@ApiResponse(code = 400, response = ErrorResponseResource.class, message = SpringFoxConstants.API_RESPONSE_CODE_400_MESSAGE),
			@ApiResponse(code = 404, response = ErrorResponseResource.class, message = SpringFoxConstants.API_RESPONSE_CODE_404_MESSAGE),
			@ApiResponse(code = 500, response = ErrorResponseResource.class, message = SpringFoxConstants.API_RESPONSE_CODE_500_MESSAGE) })
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public ApplicationPaginationResponseResource executeFindAll(
			@RequestParam(name = "page", required = false) Integer page,
			@RequestParam(name = "size", required = false) Integer size,
			@RequestParam(name = "sort", required = false) ApplicationSort sort,
			@RequestParam(name = "name", required = false) String name,
			@RequestParam(name = "enabled", required = false) Boolean enabled);

}