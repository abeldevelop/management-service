package com.abeldevelop.architecture.service.management.service.controller.v1;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

import com.abeldevelop.architecture.library.common.dto.exception.ErrorResponseResource;
import com.abeldevelop.architecture.service.management.dto.application.ApplicationResponseResource;
import com.abeldevelop.architecture.service.management.dto.application.CreateApplicationRequestResource;
import com.abeldevelop.architecture.starter.test.CommonTestController;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
public class ApplicationControllerTest extends CommonTestController {

	private static final String BASE_V1_ENDPOINT = "/v1/application";
	
	private static final String NAME_OK_VALUE = "Blog";
	private static final String DESCRIPTION_OK_VALUE = "Blog Application";
	private static final String HOME_URI_OK_VALUE = "http://blog.com";
	private static final String DOCUMENTATION_URL_OK_VALUE = "http://blog.com";
	
	//////////////////////////////
	//		executeCreate		//
	//////////////////////////////
	
	@Test
	public void createApplication_ko_nameNotNull() throws Exception {
		CreateApplicationRequestResource createApplicationRequestResource = createApplicationRequestResource(null, DESCRIPTION_OK_VALUE, true, HOME_URI_OK_VALUE, DOCUMENTATION_URL_OK_VALUE);
		ErrorResponseResource response = callPostEndpoint(BASE_V1_ENDPOINT, createApplicationRequestResource, HttpStatus.BAD_REQUEST.value(), ErrorResponseResource.class);
		assertEquals("El nombre de la aplicacion es obligatorio", response.getMessage());
	}
	
	@Test
	public void createApplication_ko_nameMinSize() throws Exception {
		CreateApplicationRequestResource createApplicationRequestResource = createApplicationRequestResource("a", DESCRIPTION_OK_VALUE, true, HOME_URI_OK_VALUE, DOCUMENTATION_URL_OK_VALUE);
		ErrorResponseResource response = callPostEndpoint(BASE_V1_ENDPOINT, createApplicationRequestResource, HttpStatus.BAD_REQUEST.value(), ErrorResponseResource.class);
		assertEquals("El nombre de la aplicacion debe tener entre 3 y 50 caracteres", response.getMessage());
	}
	
	@Test
	public void createApplication_ko_nameMaxSize() throws Exception {
		CreateApplicationRequestResource createApplicationRequestResource = createApplicationRequestResource("012345678901234567890123456789012345678901234567890", DESCRIPTION_OK_VALUE, true, HOME_URI_OK_VALUE, DOCUMENTATION_URL_OK_VALUE);
		ErrorResponseResource response = callPostEndpoint(BASE_V1_ENDPOINT, createApplicationRequestResource, HttpStatus.BAD_REQUEST.value(), ErrorResponseResource.class);
		assertEquals("El nombre de la aplicacion debe tener entre 3 y 50 caracteres", response.getMessage());
	}
	
	@Test
	public void createApplication_ko_descriptionNotNull() throws Exception {
		CreateApplicationRequestResource createApplicationRequestResource = createApplicationRequestResource(null, DESCRIPTION_OK_VALUE, true, HOME_URI_OK_VALUE, DOCUMENTATION_URL_OK_VALUE);
		ErrorResponseResource response = callPostEndpoint(BASE_V1_ENDPOINT, createApplicationRequestResource, HttpStatus.BAD_REQUEST.value(), ErrorResponseResource.class);
		assertEquals("La descripcion de la aplicacion es obligatorio", response.getMessage());
	}
	
	@Test
	public void createApplication_ko_descriptionMinSize() throws Exception {
		CreateApplicationRequestResource createApplicationRequestResource = createApplicationRequestResource("a", DESCRIPTION_OK_VALUE, true, HOME_URI_OK_VALUE, DOCUMENTATION_URL_OK_VALUE);
		ErrorResponseResource response = callPostEndpoint(BASE_V1_ENDPOINT, createApplicationRequestResource, HttpStatus.BAD_REQUEST.value(), ErrorResponseResource.class);
		assertEquals("La descripcion de la aplicacion debe tener entre {} y {} caracteres", response.getMessage());
	}
	
	@Test
	public void createApplication_ko_descriptionMaxSize() throws Exception {
		CreateApplicationRequestResource createApplicationRequestResource = createApplicationRequestResource("012345678901234567890123456789012345678901234567890", DESCRIPTION_OK_VALUE, true, HOME_URI_OK_VALUE, DOCUMENTATION_URL_OK_VALUE);
		ErrorResponseResource response = callPostEndpoint(BASE_V1_ENDPOINT, createApplicationRequestResource, HttpStatus.BAD_REQUEST.value(), ErrorResponseResource.class);
		assertEquals("La descripcion de la aplicacion debe tener entre {} y {} caracteres", response.getMessage());
	}
	
	@Test
	public void createApplication_ko_enabledNotNull() throws Exception {
		CreateApplicationRequestResource createApplicationRequestResource = createApplicationRequestResource(null, DESCRIPTION_OK_VALUE, true, HOME_URI_OK_VALUE, DOCUMENTATION_URL_OK_VALUE);
		ErrorResponseResource response = callPostEndpoint(BASE_V1_ENDPOINT, createApplicationRequestResource, HttpStatus.BAD_REQUEST.value(), ErrorResponseResource.class);
		assertEquals("Es obligatorio indicar si la aplicacion esta habilitada", response.getMessage());
	}
	
	@Test
	public void createApplication_ko_homeUriNotNull() throws Exception {
		CreateApplicationRequestResource createApplicationRequestResource = createApplicationRequestResource(null, DESCRIPTION_OK_VALUE, true, HOME_URI_OK_VALUE, DOCUMENTATION_URL_OK_VALUE);
		ErrorResponseResource response = callPostEndpoint(BASE_V1_ENDPOINT, createApplicationRequestResource, HttpStatus.BAD_REQUEST.value(), ErrorResponseResource.class);
		assertEquals("La URL principal de la aplicacion es obligatorio", response.getMessage());
	}
	
	@Test
	public void createApplication_ko_homeUriMinSize() throws Exception {
		CreateApplicationRequestResource createApplicationRequestResource = createApplicationRequestResource("a", DESCRIPTION_OK_VALUE, true, HOME_URI_OK_VALUE, DOCUMENTATION_URL_OK_VALUE);
		ErrorResponseResource response = callPostEndpoint(BASE_V1_ENDPOINT, createApplicationRequestResource, HttpStatus.BAD_REQUEST.value(), ErrorResponseResource.class);
		assertEquals("La URL principal de la aplicacion debe tener entre {} y {} caracteres", response.getMessage());
	}
	
	@Test
	public void createApplication_ko_homeUriMaxSize() throws Exception {
		CreateApplicationRequestResource createApplicationRequestResource = createApplicationRequestResource("012345678901234567890123456789012345678901234567890", DESCRIPTION_OK_VALUE, true, HOME_URI_OK_VALUE, DOCUMENTATION_URL_OK_VALUE);
		ErrorResponseResource response = callPostEndpoint(BASE_V1_ENDPOINT, createApplicationRequestResource, HttpStatus.BAD_REQUEST.value(), ErrorResponseResource.class);
		assertEquals("La URL principal de la aplicacion debe tener entre {} y {} caracteres", response.getMessage());
	}
	
	@Test
	public void createApplication_ko_documentationUrlNotNull() throws Exception {
		CreateApplicationRequestResource createApplicationRequestResource = createApplicationRequestResource(null, DESCRIPTION_OK_VALUE, true, HOME_URI_OK_VALUE, DOCUMENTATION_URL_OK_VALUE);
		ErrorResponseResource response = callPostEndpoint(BASE_V1_ENDPOINT, createApplicationRequestResource, HttpStatus.BAD_REQUEST.value(), ErrorResponseResource.class);
		assertEquals("La URL de documentacion de la aplicacion es obligatorio", response.getMessage());
	}
	
	@Test
	public void createApplication_ko_documentationUrlMinSize() throws Exception {
		CreateApplicationRequestResource createApplicationRequestResource = createApplicationRequestResource("a", DESCRIPTION_OK_VALUE, true, HOME_URI_OK_VALUE, DOCUMENTATION_URL_OK_VALUE);
		ErrorResponseResource response = callPostEndpoint(BASE_V1_ENDPOINT, createApplicationRequestResource, HttpStatus.BAD_REQUEST.value(), ErrorResponseResource.class);
		assertEquals("La URL de documentacion de la aplicacion debe tener entre {} y {} caracteres", response.getMessage());
	}
	
	@Test
	public void createApplication_ko_documentationUrlMaxSize() throws Exception {
		CreateApplicationRequestResource createApplicationRequestResource = createApplicationRequestResource("012345678901234567890123456789012345678901234567890", DESCRIPTION_OK_VALUE, true, HOME_URI_OK_VALUE, DOCUMENTATION_URL_OK_VALUE);
		ErrorResponseResource response = callPostEndpoint(BASE_V1_ENDPOINT, createApplicationRequestResource, HttpStatus.BAD_REQUEST.value(), ErrorResponseResource.class);
		assertEquals("La URL de documentacion de la aplicacion debe tener entre {} y {} caracteres", response.getMessage());
	}
	
	@Test
	public void createApplication_ko_applicationWithNameExist() throws Exception {
		CreateApplicationRequestResource createApplicationRequestResource = createApplicationRequestResource("012345678901234567890123456789012345678901234567890", DESCRIPTION_OK_VALUE, true, HOME_URI_OK_VALUE, DOCUMENTATION_URL_OK_VALUE);
		ErrorResponseResource response = callPostEndpoint(BASE_V1_ENDPOINT, createApplicationRequestResource, HttpStatus.BAD_REQUEST.value(), ErrorResponseResource.class);
		assertEquals("Ya existe una aplicacion con el nombre {}", response.getMessage());
	}
	
	@Test
	public void createApplication_ok() throws Exception {
		CreateApplicationRequestResource createApplicationRequestResource = createApplicationRequestResource(NAME_OK_VALUE, DESCRIPTION_OK_VALUE, true, HOME_URI_OK_VALUE, DOCUMENTATION_URL_OK_VALUE);
		ApplicationResponseResource response = callPostEndpoint(BASE_V1_ENDPOINT, createApplicationRequestResource, HttpStatus.CREATED.value(), ApplicationResponseResource.class);
		assertEquals("blog", response.getId());
		assertEquals(NAME_OK_VALUE, response.getName());
		assertEquals(DESCRIPTION_OK_VALUE, response.getDescription());
		assertEquals(true, response.getEnabled().booleanValue());
		assertEquals(HOME_URI_OK_VALUE, response.getHomeUri());
		assertEquals(DOCUMENTATION_URL_OK_VALUE, response.getDocumentationUrl());
		assertEquals(0, response.getVersion());
	}
	
	private CreateApplicationRequestResource createApplicationRequestResource(String name, String description, Boolean enabled, String homeUri, String documentationUrl) {
		return CreateApplicationRequestResource.builder()
				.name(name)
				.description(description)
				.enabled(enabled)
				.homeUri(homeUri)
				.documentationUrl(documentationUrl)
				.build();
	}
	
	//////////////////////////////
	//		executeUpdate		//
	//////////////////////////////
	
	//////////////////////////////
	//		executeDelete		//
	//////////////////////////////
	
	//////////////////////////////
	//		executeFindById		//
	//////////////////////////////
	
	//////////////////////////////
	//		executeFindAll		//
	//////////////////////////////
}
