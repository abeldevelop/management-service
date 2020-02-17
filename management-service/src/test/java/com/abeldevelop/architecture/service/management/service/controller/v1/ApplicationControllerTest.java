package com.abeldevelop.architecture.service.management.service.controller.v1;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

import com.abeldevelop.architecture.library.common.dto.exception.ErrorResponseResource;
import com.abeldevelop.architecture.service.management.dto.application.ApplicationPaginationResponseResource;
import com.abeldevelop.architecture.service.management.dto.application.ApplicationResponseResource;
import com.abeldevelop.architecture.service.management.dto.application.CreateApplicationRequestResource;
import com.abeldevelop.architecture.service.management.dto.application.UpdateApplicationRequestResource;
import com.abeldevelop.architecture.service.management.model.ApplicationEntity;
import com.abeldevelop.architecture.service.management.repository.springdata.ApplicationSpringDataRepository;
import com.abeldevelop.architecture.starter.test.CommonTestController;
import com.abeldevelop.architecture.starter.test.EndpointData;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ApplicationControllerTest extends CommonTestController {

	private static final String BASE_V1_ENDPOINT = "/v1/application";
	
	private static final String ID_OK_VALUE = "blog";
	private static final String NAME_OK_VALUE = "Blog";
	private static final String DESCRIPTION_OK_VALUE = "Blog Application";
	private static final String HOME_URI_OK_VALUE = "http://blog.com";
	private static final String DOCUMENTATION_URL_OK_VALUE = "http://blog.com";
	
	private static final String VALUE_WITH_MORE_THAN_50_CHARACTERS = "012345678901234567890123456789012345678901234567890";
	private static final String VALUE_WITH_MORE_THAN_255_CHARACTERS = "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345";
	
	@Autowired
	private ApplicationSpringDataRepository applicationSpringDataRepository;
	
	//////////////////////////////
	//		executeCreate		//
	//////////////////////////////
	
	@Test
	public void createApplication_ko_nameNotNull() throws Exception {
		EndpointData<ErrorResponseResource> endpointData = EndpointData.<ErrorResponseResource>builder()
				.method(HttpMethod.POST)
				.endpoint(BASE_V1_ENDPOINT)
				.content(createApplicationRequestResource(null, DESCRIPTION_OK_VALUE, true, HOME_URI_OK_VALUE, DOCUMENTATION_URL_OK_VALUE))
				.expectedStatus(HttpStatus.BAD_REQUEST)
				.typeReturn(ErrorResponseResource.class)
				.build();
		
		ErrorResponseResource response = makeRestRequest(endpointData);
		
		assertEquals("El nombre de la aplicacion es obligatorio", response.getMessage());
	}
	
	@Test
	public void createApplication_ko_nameMinSize() throws Exception {
		EndpointData<ErrorResponseResource> endpointData = EndpointData.<ErrorResponseResource>builder()
				.method(HttpMethod.POST)
				.endpoint(BASE_V1_ENDPOINT)
				.content(createApplicationRequestResource("a", DESCRIPTION_OK_VALUE, true, HOME_URI_OK_VALUE, DOCUMENTATION_URL_OK_VALUE))
				.expectedStatus(HttpStatus.BAD_REQUEST)
				.typeReturn(ErrorResponseResource.class)
				.build();
		
		ErrorResponseResource response = makeRestRequest(endpointData);
		
		assertEquals("El nombre de la aplicacion debe tener entre 3 y 50 caracteres", response.getMessage());
	}
	
	@Test
	public void createApplication_ko_nameMaxSize() throws Exception {
		EndpointData<ErrorResponseResource> endpointData = EndpointData.<ErrorResponseResource>builder()
				.method(HttpMethod.POST)
				.endpoint(BASE_V1_ENDPOINT)
				.content(createApplicationRequestResource(VALUE_WITH_MORE_THAN_50_CHARACTERS, DESCRIPTION_OK_VALUE, true, HOME_URI_OK_VALUE, DOCUMENTATION_URL_OK_VALUE))
				.expectedStatus(HttpStatus.BAD_REQUEST)
				.typeReturn(ErrorResponseResource.class)
				.build();
		
		ErrorResponseResource response = makeRestRequest(endpointData);
		
		assertEquals("El nombre de la aplicacion debe tener entre 3 y 50 caracteres", response.getMessage());
	}
	
	@Test
	public void createApplication_ko_descriptionNotNull() throws Exception {
		EndpointData<ErrorResponseResource> endpointData = EndpointData.<ErrorResponseResource>builder()
				.method(HttpMethod.POST)
				.endpoint(BASE_V1_ENDPOINT)
				.content(createApplicationRequestResource(NAME_OK_VALUE, null, true, HOME_URI_OK_VALUE, DOCUMENTATION_URL_OK_VALUE))
				.expectedStatus(HttpStatus.BAD_REQUEST)
				.typeReturn(ErrorResponseResource.class)
				.build();
		
		ErrorResponseResource response = makeRestRequest(endpointData);
		
		assertEquals("La descripcion de la aplicacion es obligatorio", response.getMessage());
	}
	
	@Test
	public void createApplication_ko_descriptionMinSize() throws Exception {
		EndpointData<ErrorResponseResource> endpointData = EndpointData.<ErrorResponseResource>builder()
				.method(HttpMethod.POST)
				.endpoint(BASE_V1_ENDPOINT)
				.content(createApplicationRequestResource(NAME_OK_VALUE, "a", true, HOME_URI_OK_VALUE, DOCUMENTATION_URL_OK_VALUE))
				.expectedStatus(HttpStatus.BAD_REQUEST)
				.typeReturn(ErrorResponseResource.class)
				.build();
		
		ErrorResponseResource response = makeRestRequest(endpointData);
		
		assertEquals("La descripcion de la aplicacion debe tener entre 5 y 255 caracteres", response.getMessage());
	}
	
	@Test
	public void createApplication_ko_descriptionMaxSize() throws Exception {
		EndpointData<ErrorResponseResource> endpointData = EndpointData.<ErrorResponseResource>builder()
				.method(HttpMethod.POST)
				.endpoint(BASE_V1_ENDPOINT)
				.content(createApplicationRequestResource(NAME_OK_VALUE, VALUE_WITH_MORE_THAN_255_CHARACTERS, true, HOME_URI_OK_VALUE, DOCUMENTATION_URL_OK_VALUE))
				.expectedStatus(HttpStatus.BAD_REQUEST)
				.typeReturn(ErrorResponseResource.class)
				.build();
		
		ErrorResponseResource response = makeRestRequest(endpointData);

		assertEquals("La descripcion de la aplicacion debe tener entre 5 y 255 caracteres", response.getMessage());
	}
	
	@Test
	public void createApplication_ko_enabledNotNull() throws Exception {
		EndpointData<ErrorResponseResource> endpointData = EndpointData.<ErrorResponseResource>builder()
				.method(HttpMethod.POST)
				.endpoint(BASE_V1_ENDPOINT)
				.content(createApplicationRequestResource(NAME_OK_VALUE, DESCRIPTION_OK_VALUE, null, HOME_URI_OK_VALUE, DOCUMENTATION_URL_OK_VALUE))
				.expectedStatus(HttpStatus.BAD_REQUEST)
				.typeReturn(ErrorResponseResource.class)
				.build();
		
		ErrorResponseResource response = makeRestRequest(endpointData);
		
		assertEquals("Es obligatorio indicar si la aplicacion esta habilitada", response.getMessage());
	}
	
	@Test
	public void createApplication_ko_homeUriNotNull() throws Exception {
		EndpointData<ErrorResponseResource> endpointData = EndpointData.<ErrorResponseResource>builder()
				.method(HttpMethod.POST)
				.endpoint(BASE_V1_ENDPOINT)
				.content(createApplicationRequestResource(NAME_OK_VALUE, DESCRIPTION_OK_VALUE, true, null, DOCUMENTATION_URL_OK_VALUE))
				.expectedStatus(HttpStatus.BAD_REQUEST)
				.typeReturn(ErrorResponseResource.class)
				.build();
		
		ErrorResponseResource response = makeRestRequest(endpointData);

		assertEquals("La URL principal de la aplicacion es obligatorio", response.getMessage());
	}
	
	@Test
	public void createApplication_ko_homeUriMinSize() throws Exception {
		EndpointData<ErrorResponseResource> endpointData = EndpointData.<ErrorResponseResource>builder()
				.method(HttpMethod.POST)
				.endpoint(BASE_V1_ENDPOINT)
				.content(createApplicationRequestResource(NAME_OK_VALUE, DESCRIPTION_OK_VALUE, true, "a", DOCUMENTATION_URL_OK_VALUE))
				.expectedStatus(HttpStatus.BAD_REQUEST)
				.typeReturn(ErrorResponseResource.class)
				.build();
		
		ErrorResponseResource response = makeRestRequest(endpointData);

		assertEquals("La URL principal de la aplicacion debe tener entre 5 y 255 caracteres", response.getMessage());
	}
	
	@Test
	public void createApplication_ko_homeUriMaxSize() throws Exception {
		EndpointData<ErrorResponseResource> endpointData = EndpointData.<ErrorResponseResource>builder()
				.method(HttpMethod.POST)
				.endpoint(BASE_V1_ENDPOINT)
				.content(createApplicationRequestResource(NAME_OK_VALUE, DESCRIPTION_OK_VALUE, true, VALUE_WITH_MORE_THAN_255_CHARACTERS, DOCUMENTATION_URL_OK_VALUE))
				.headers(getDefaultHeaders())
				.expectedStatus(HttpStatus.BAD_REQUEST)
				.typeReturn(ErrorResponseResource.class)
				.build();
		
		ErrorResponseResource response = makeRestRequest(endpointData);

		assertEquals("La URL principal de la aplicacion debe tener entre 5 y 255 caracteres", response.getMessage());
	}
	
	@Test
	public void createApplication_ko_documentationUrlNotNull() throws Exception {
		EndpointData<ErrorResponseResource> endpointData = EndpointData.<ErrorResponseResource>builder()
				.method(HttpMethod.POST)
				.endpoint(BASE_V1_ENDPOINT)
				.content(createApplicationRequestResource(NAME_OK_VALUE, DESCRIPTION_OK_VALUE, true, HOME_URI_OK_VALUE, null))
				.expectedStatus(HttpStatus.BAD_REQUEST)
				.typeReturn(ErrorResponseResource.class)
				.build();
		
		ErrorResponseResource response = makeRestRequest(endpointData);
		
		assertEquals("La URL de documentacion de la aplicacion es obligatorio", response.getMessage());
	}
	
	@Test
	public void createApplication_ko_documentationUrlMinSize() throws Exception {
		EndpointData<ErrorResponseResource> endpointData = EndpointData.<ErrorResponseResource>builder()
				.method(HttpMethod.POST)
				.endpoint(BASE_V1_ENDPOINT)
				.content(createApplicationRequestResource(NAME_OK_VALUE, DESCRIPTION_OK_VALUE, true, HOME_URI_OK_VALUE, "a"))
				.expectedStatus(HttpStatus.BAD_REQUEST)
				.typeReturn(ErrorResponseResource.class)
				.build();
		
		ErrorResponseResource response = makeRestRequest(endpointData);

		assertEquals("La URL de documentacion de la aplicacion debe tener entre 5 y 255 caracteres", response.getMessage());
	}
	
	@Test
	public void createApplication_ko_documentationUrlMaxSize() throws Exception {
		EndpointData<ErrorResponseResource> endpointData = EndpointData.<ErrorResponseResource>builder()
				.method(HttpMethod.POST)
				.endpoint(BASE_V1_ENDPOINT)
				.content(createApplicationRequestResource(NAME_OK_VALUE, DESCRIPTION_OK_VALUE, true, HOME_URI_OK_VALUE, VALUE_WITH_MORE_THAN_255_CHARACTERS))
				.expectedStatus(HttpStatus.BAD_REQUEST)
				.typeReturn(ErrorResponseResource.class)
				.build();
		
		ErrorResponseResource response = makeRestRequest(endpointData);

		assertEquals("La URL de documentacion de la aplicacion debe tener entre 5 y 255 caracteres", response.getMessage());
	}
	
	@Test
	public void createApplication_ko_applicationWithNameExist() throws Exception {
		applicationSpringDataRepository.deleteAll();
		saveApplicationEntity(ID_OK_VALUE, NAME_OK_VALUE, DESCRIPTION_OK_VALUE, true, HOME_URI_OK_VALUE, DOCUMENTATION_URL_OK_VALUE);
		
		EndpointData<ErrorResponseResource> endpointData = EndpointData.<ErrorResponseResource>builder()
				.method(HttpMethod.POST)
				.endpoint(BASE_V1_ENDPOINT)
				.content(createApplicationRequestResource(NAME_OK_VALUE, DESCRIPTION_OK_VALUE, true, HOME_URI_OK_VALUE, DOCUMENTATION_URL_OK_VALUE))
				.expectedStatus(HttpStatus.BAD_REQUEST)
				.typeReturn(ErrorResponseResource.class)
				.build();
		
		ErrorResponseResource response = makeRestRequest(endpointData);
		
		assertEquals("Ya existe una aplicacion con el nombre " + NAME_OK_VALUE, response.getMessage());
	}
	
	@Test
	public void createApplication_ok() throws Exception {
		EndpointData<ApplicationResponseResource> endpointData = EndpointData.<ApplicationResponseResource>builder()
				.method(HttpMethod.POST)
				.endpoint(BASE_V1_ENDPOINT)
				.content(createApplicationRequestResource(NAME_OK_VALUE, DESCRIPTION_OK_VALUE, true, HOME_URI_OK_VALUE, DOCUMENTATION_URL_OK_VALUE))
				.expectedStatus(HttpStatus.CREATED)
				.typeReturn(ApplicationResponseResource.class)
				.build();
		
		applicationSpringDataRepository.deleteAll();

		ApplicationResponseResource response = makeRestRequest(endpointData);
		
		assertEquals(ID_OK_VALUE, response.getId());
		assertEquals(NAME_OK_VALUE, response.getName());
		assertEquals(DESCRIPTION_OK_VALUE, response.getDescription());
		assertEquals(true, response.getEnabled().booleanValue());
		assertEquals(HOME_URI_OK_VALUE, response.getHomeUri());
		assertEquals(DOCUMENTATION_URL_OK_VALUE, response.getDocumentationUrl());
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
	
	@Test
	public void updateApplication_ko_descriptionNotNull() throws Exception {
		String id = "blog";
		
		EndpointData<ErrorResponseResource> endpointData = EndpointData.<ErrorResponseResource>builder()
				.method(HttpMethod.PUT)
				.endpoint(BASE_V1_ENDPOINT + "/" + id)
				.content(updateApplicationRequestResource(null, true, HOME_URI_OK_VALUE, DOCUMENTATION_URL_OK_VALUE))
				.expectedStatus(HttpStatus.BAD_REQUEST)
				.typeReturn(ErrorResponseResource.class)
				.build();
		
		ErrorResponseResource response = makeRestRequest(endpointData);
		
		assertEquals("La descripcion de la aplicacion es obligatorio", response.getMessage());
	}
	
	@Test
	public void updateApplication_ko_descriptionMinSize() throws Exception {
		String id = "blog";
		
		EndpointData<ErrorResponseResource> endpointData = EndpointData.<ErrorResponseResource>builder()
				.method(HttpMethod.PUT)
				.endpoint(BASE_V1_ENDPOINT + "/" + id)
				.content(updateApplicationRequestResource("a", true, HOME_URI_OK_VALUE, DOCUMENTATION_URL_OK_VALUE))
				.expectedStatus(HttpStatus.BAD_REQUEST)
				.typeReturn(ErrorResponseResource.class)
				.build();
		
		ErrorResponseResource response = makeRestRequest(endpointData);
		
		assertEquals("La descripcion de la aplicacion debe tener entre 5 y 255 caracteres", response.getMessage());
	}
	
	@Test
	public void updateApplication_ko_descriptionMaxSize() throws Exception {
		String id = "blog";
		
		EndpointData<ErrorResponseResource> endpointData = EndpointData.<ErrorResponseResource>builder()
				.method(HttpMethod.PUT)
				.endpoint(BASE_V1_ENDPOINT + "/" + id)
				.content(updateApplicationRequestResource(VALUE_WITH_MORE_THAN_255_CHARACTERS, true, HOME_URI_OK_VALUE, DOCUMENTATION_URL_OK_VALUE))
				.expectedStatus(HttpStatus.BAD_REQUEST)
				.typeReturn(ErrorResponseResource.class)
				.build();
		
		ErrorResponseResource response = makeRestRequest(endpointData);
		
		assertEquals("La descripcion de la aplicacion debe tener entre 5 y 255 caracteres", response.getMessage());
	}
	
	@Test
	public void updateApplication_ko_enabledNotNull() throws Exception {
		String id = "blog";
		
		EndpointData<ErrorResponseResource> endpointData = EndpointData.<ErrorResponseResource>builder()
				.method(HttpMethod.PUT)
				.endpoint(BASE_V1_ENDPOINT + "/" + id)
				.content(updateApplicationRequestResource(DESCRIPTION_OK_VALUE, null, HOME_URI_OK_VALUE, DOCUMENTATION_URL_OK_VALUE))
				.expectedStatus(HttpStatus.BAD_REQUEST)
				.typeReturn(ErrorResponseResource.class)
				.build();
		
		ErrorResponseResource response = makeRestRequest(endpointData);
		
		assertEquals("Es obligatorio indicar si la aplicacion esta habilitada", response.getMessage());
	}
	
	@Test
	public void updateApplication_ko_homeUriNotNull() throws Exception {
		String id = "blog";
		
		EndpointData<ErrorResponseResource> endpointData = EndpointData.<ErrorResponseResource>builder()
				.method(HttpMethod.PUT)
				.endpoint(BASE_V1_ENDPOINT + "/" + id)
				.content(updateApplicationRequestResource(DESCRIPTION_OK_VALUE, true, null, DOCUMENTATION_URL_OK_VALUE))
				.expectedStatus(HttpStatus.BAD_REQUEST)
				.typeReturn(ErrorResponseResource.class)
				.build();
		
		ErrorResponseResource response = makeRestRequest(endpointData);
		
		assertEquals("La URL principal de la aplicacion es obligatorio", response.getMessage());
	}
	
	@Test
	public void updateApplication_ko_homeUriMinSize() throws Exception {
		String id = "blog";
		
		EndpointData<ErrorResponseResource> endpointData = EndpointData.<ErrorResponseResource>builder()
				.method(HttpMethod.PUT)
				.endpoint(BASE_V1_ENDPOINT + "/" + id)
				.content(updateApplicationRequestResource(DESCRIPTION_OK_VALUE, true, "a", DOCUMENTATION_URL_OK_VALUE))
				.expectedStatus(HttpStatus.BAD_REQUEST)
				.typeReturn(ErrorResponseResource.class)
				.build();
		
		ErrorResponseResource response = makeRestRequest(endpointData);
		
		assertEquals("La URL principal de la aplicacion debe tener entre 5 y 255 caracteres", response.getMessage());
	}
	
	@Test
	public void updateApplication_ko_homeUriMaxSize() throws Exception {
		String id = "blog";
		
		EndpointData<ErrorResponseResource> endpointData = EndpointData.<ErrorResponseResource>builder()
				.method(HttpMethod.PUT)
				.endpoint(BASE_V1_ENDPOINT + "/" + id)
				.content(updateApplicationRequestResource(DESCRIPTION_OK_VALUE, true, VALUE_WITH_MORE_THAN_255_CHARACTERS, DOCUMENTATION_URL_OK_VALUE))
				.expectedStatus(HttpStatus.BAD_REQUEST)
				.typeReturn(ErrorResponseResource.class)
				.build();
		
		ErrorResponseResource response = makeRestRequest(endpointData);
		
		assertEquals("La URL principal de la aplicacion debe tener entre 5 y 255 caracteres", response.getMessage());
	}
	
	@Test
	public void updateApplication_ko_documentationUrlNotNull() throws Exception {
		String id = "blog";
		
		EndpointData<ErrorResponseResource> endpointData = EndpointData.<ErrorResponseResource>builder()
				.method(HttpMethod.PUT)
				.endpoint(BASE_V1_ENDPOINT + "/" + id)
				.content(updateApplicationRequestResource(DESCRIPTION_OK_VALUE, true, HOME_URI_OK_VALUE, null))
				.expectedStatus(HttpStatus.BAD_REQUEST)
				.typeReturn(ErrorResponseResource.class)
				.build();
		
		ErrorResponseResource response = makeRestRequest(endpointData);
		
		assertEquals("La URL de documentacion de la aplicacion es obligatorio", response.getMessage());
	}
	
	@Test
	public void updateApplication_ko_documentationUrlMinSize() throws Exception {
		String id = "blog";
		
		EndpointData<ErrorResponseResource> endpointData = EndpointData.<ErrorResponseResource>builder()
				.method(HttpMethod.PUT)
				.endpoint(BASE_V1_ENDPOINT + "/" + id)
				.content(updateApplicationRequestResource(DESCRIPTION_OK_VALUE, true, HOME_URI_OK_VALUE, "a"))
				.expectedStatus(HttpStatus.BAD_REQUEST)
				.typeReturn(ErrorResponseResource.class)
				.build();
		
		ErrorResponseResource response = makeRestRequest(endpointData);
		
		assertEquals("La URL de documentacion de la aplicacion debe tener entre 5 y 255 caracteres", response.getMessage());
	}
	
	@Test
	public void updateApplication_ko_documentationUrlMaxSize() throws Exception {
		String id = "blog";
		
		EndpointData<ErrorResponseResource> endpointData = EndpointData.<ErrorResponseResource>builder()
				.method(HttpMethod.PUT)
				.endpoint(BASE_V1_ENDPOINT + "/" + id)
				.content(updateApplicationRequestResource(DESCRIPTION_OK_VALUE, true, HOME_URI_OK_VALUE, VALUE_WITH_MORE_THAN_255_CHARACTERS))
				.expectedStatus(HttpStatus.BAD_REQUEST)
				.typeReturn(ErrorResponseResource.class)
				.build();
		
		ErrorResponseResource response = makeRestRequest(endpointData);
		
		assertEquals("La URL de documentacion de la aplicacion debe tener entre 5 y 255 caracteres", response.getMessage());
	}
	
	@Test
	public void updateApplication_ko_applicationWithIdNotExist() throws Exception {
		applicationSpringDataRepository.deleteAll();
		String id = "blog";
		
		EndpointData<ErrorResponseResource> endpointData = EndpointData.<ErrorResponseResource>builder()
				.method(HttpMethod.PUT)
				.endpoint(BASE_V1_ENDPOINT + "/" + id)
				.content(updateApplicationRequestResource(DESCRIPTION_OK_VALUE, true, HOME_URI_OK_VALUE, DOCUMENTATION_URL_OK_VALUE))
				.expectedStatus(HttpStatus.BAD_REQUEST)
				.typeReturn(ErrorResponseResource.class)
				.build();
		
		ErrorResponseResource response = makeRestRequest(endpointData);
		
		assertEquals("No existe una aplicacion con el id blog", response.getMessage());
	}
	
	@Test
	public void updateApplication_ok() throws Exception {
		applicationSpringDataRepository.deleteAll();
		saveApplicationEntity(ID_OK_VALUE, NAME_OK_VALUE, DESCRIPTION_OK_VALUE, true, HOME_URI_OK_VALUE, DOCUMENTATION_URL_OK_VALUE);
		String id = "blog";
		
		EndpointData<ApplicationResponseResource> endpointData = EndpointData.<ApplicationResponseResource>builder()
				.method(HttpMethod.PUT)
				.endpoint(BASE_V1_ENDPOINT + "/" + id)
				.content(updateApplicationRequestResource(DESCRIPTION_OK_VALUE+"2", true, HOME_URI_OK_VALUE+"2", DOCUMENTATION_URL_OK_VALUE+"2"))
				.expectedStatus(HttpStatus.OK)
				.typeReturn(ApplicationResponseResource.class)
				.build();
		
		ApplicationResponseResource response = makeRestRequest(endpointData);
		
		assertEquals(ID_OK_VALUE, response.getId());
		assertEquals(NAME_OK_VALUE, response.getName());
		assertEquals(DESCRIPTION_OK_VALUE+"2", response.getDescription());
		assertEquals(true, response.getEnabled().booleanValue());
		assertEquals(HOME_URI_OK_VALUE+"2", response.getHomeUri());
		assertEquals(DOCUMENTATION_URL_OK_VALUE+"2", response.getDocumentationUrl());
	}
	
	private UpdateApplicationRequestResource updateApplicationRequestResource(String description, Boolean enabled, String homeUri, String documentationUrl) {
		return UpdateApplicationRequestResource.builder()
				.description(description)
				.enabled(enabled)
				.homeUri(homeUri)
				.documentationUrl(documentationUrl)
				.build();
	}
	
	//////////////////////////////
	//		executeDelete		//
	//////////////////////////////
	
	@Test
	public void deleteApplication_ko_applicationWithIdNotExist() throws Exception {
		applicationSpringDataRepository.deleteAll();
		
		String id = "blog";
		
		EndpointData<ErrorResponseResource> endpointData = EndpointData.<ErrorResponseResource>builder()
				.method(HttpMethod.DELETE)
				.endpoint(BASE_V1_ENDPOINT + "/" + id)
				.expectedStatus(HttpStatus.BAD_REQUEST)
				.typeReturn(ErrorResponseResource.class)
				.build();
		
		ErrorResponseResource response = makeRestRequest(endpointData);

		assertEquals("No existe una aplicacion con el id blog", response.getMessage());
	}
	
	@Test
	public void deleteApplication_ok() throws Exception {
		applicationSpringDataRepository.deleteAll();
		saveApplicationEntity(ID_OK_VALUE, NAME_OK_VALUE, DESCRIPTION_OK_VALUE, true, HOME_URI_OK_VALUE, DOCUMENTATION_URL_OK_VALUE);
		String id = "blog";
		
		EndpointData<Void> endpointData = EndpointData.<Void>builder()
				.method(HttpMethod.DELETE)
				.endpoint(BASE_V1_ENDPOINT + "/" + id)
				.expectedStatus(HttpStatus.NO_CONTENT)
				.typeReturn(Void.class)
				.build();
		
		makeRestRequest(endpointData);
	}
	
	//////////////////////////////
	//		executeFindById		//
	//////////////////////////////
	
	@Test
	public void findApplication_ko_applicationWithIdNotExist() throws Exception {
		applicationSpringDataRepository.deleteAll();
		
		String id = "blog";
		
		EndpointData<ErrorResponseResource> endpointData = EndpointData.<ErrorResponseResource>builder()
				.method(HttpMethod.GET)
				.endpoint(BASE_V1_ENDPOINT + "/" + id)
				.expectedStatus(HttpStatus.BAD_REQUEST)
				.typeReturn(ErrorResponseResource.class)
				.build();
		
		ErrorResponseResource response = makeRestRequest(endpointData);

		assertEquals("No existe una aplicacion con el id blog", response.getMessage());
	}
	
	@Test
	public void findApplication_ok() throws Exception {
		applicationSpringDataRepository.deleteAll();
		saveApplicationEntity(ID_OK_VALUE, NAME_OK_VALUE, DESCRIPTION_OK_VALUE, true, HOME_URI_OK_VALUE, DOCUMENTATION_URL_OK_VALUE);
		String id = "blog";
		
		EndpointData<ApplicationResponseResource> endpointData = EndpointData.<ApplicationResponseResource>builder()
				.method(HttpMethod.GET)
				.endpoint(BASE_V1_ENDPOINT + "/" + id)
				.content(createApplicationRequestResource("a", DESCRIPTION_OK_VALUE, true, HOME_URI_OK_VALUE, DOCUMENTATION_URL_OK_VALUE))
				.expectedStatus(HttpStatus.OK)
				.typeReturn(ApplicationResponseResource.class)
				.build();
		
		ApplicationResponseResource response = makeRestRequest(endpointData);
		
		assertEquals(ID_OK_VALUE, response.getId());
		assertEquals(NAME_OK_VALUE, response.getName());
		assertEquals(DESCRIPTION_OK_VALUE, response.getDescription());
		assertEquals(true, response.getEnabled().booleanValue());
		assertEquals(HOME_URI_OK_VALUE, response.getHomeUri());
		assertEquals(DOCUMENTATION_URL_OK_VALUE, response.getDocumentationUrl());
	}
	
	//////////////////////////////
	//		executeFindAll		//
	//////////////////////////////
	
	@Test
	public void findAllApplications_ko_minPageNumberError() throws Exception {
		Map<String, String> params = new HashMap<>();
		params.put("page", "0");
		
		EndpointData<ErrorResponseResource> endpointData = EndpointData.<ErrorResponseResource>builder()
				.method(HttpMethod.GET)
				.endpoint(BASE_V1_ENDPOINT)
				.params(params)
				.expectedStatus(HttpStatus.BAD_REQUEST)
				.typeReturn(ErrorResponseResource.class)
				.build();
		
		ErrorResponseResource response = makeRestRequest(endpointData);
		
		assertEquals("El numero de pagina no puede ser inferior a 1", response.getMessage());
	}
	
	@Test
	public void findAllApplications_ko_minPageSizeError() throws Exception {
		Map<String, String> params = new HashMap<>();
		params.put("size", "0");
		
		EndpointData<ErrorResponseResource> endpointData = EndpointData.<ErrorResponseResource>builder()
				.method(HttpMethod.GET)
				.endpoint(BASE_V1_ENDPOINT)
				.params(params)
				.expectedStatus(HttpStatus.BAD_REQUEST)
				.typeReturn(ErrorResponseResource.class)
				.build();
		
		ErrorResponseResource response = makeRestRequest(endpointData);
		
		assertEquals("El tamaño de pagina no puede ser inferior a 1", response.getMessage());
	}
	
	@Test
	public void findAllApplications_ko_sortError() throws Exception {
		Map<String, String> params = new HashMap<>();
		params.put("sort", "CODE_ASC");
		
		EndpointData<ErrorResponseResource> endpointData = EndpointData.<ErrorResponseResource>builder()
				.method(HttpMethod.GET)
				.endpoint(BASE_V1_ENDPOINT)
				.params(params)
				.expectedStatus(HttpStatus.BAD_REQUEST)
				.typeReturn(ErrorResponseResource.class)
				.build();
		
		ErrorResponseResource response = makeRestRequest(endpointData);
		
		assertEquals("El valor de entrada CODE_ASC no es valido", response.getMessage());
	}
	
	@Test
	public void findAllApplications_ok_noParams() throws Exception {
		applicationSpringDataRepository.deleteAll();
		saveApplicationEntity(ID_OK_VALUE, NAME_OK_VALUE, DESCRIPTION_OK_VALUE, true, HOME_URI_OK_VALUE, DOCUMENTATION_URL_OK_VALUE);
		Integer expected = 1;
		
		EndpointData<ApplicationPaginationResponseResource> endpointData = EndpointData.<ApplicationPaginationResponseResource>builder()
				.method(HttpMethod.GET)
				.endpoint(BASE_V1_ENDPOINT)
				.expectedStatus(HttpStatus.OK)
				.typeReturn(ApplicationPaginationResponseResource.class)
				.build();
		
		ApplicationPaginationResponseResource response = makeRestRequest(endpointData);

		assertEquals(expected, response.getApplications().size());
	}
	
	@Test
	public void findAllApplications_ok() throws Exception {
		applicationSpringDataRepository.deleteAll();
		saveApplicationEntity(ID_OK_VALUE, NAME_OK_VALUE, DESCRIPTION_OK_VALUE, true, HOME_URI_OK_VALUE, DOCUMENTATION_URL_OK_VALUE);
		Integer expected = 1;
		
		Map<String, String> params = new HashMap<>();
		params.put("name", "o");
		params.put("enabled", "true");
		
		EndpointData<ApplicationPaginationResponseResource> endpointData = EndpointData.<ApplicationPaginationResponseResource>builder()
				.method(HttpMethod.GET)
				.endpoint(BASE_V1_ENDPOINT)
				.params(params)
				.expectedStatus(HttpStatus.OK)
				.typeReturn(ApplicationPaginationResponseResource.class)
				.build();
		
		ApplicationPaginationResponseResource response = makeRestRequest(endpointData);

		assertEquals(expected, response.getApplications().size());
	}
	
	//////////////////////////////
	//		Auxiliar Methos		//
	//////////////////////////////
	
	private void saveApplicationEntity(String id, String name, String description, Boolean enabled, String homeUri, String documentationUrl) {
		ApplicationEntity applicationEntity = ApplicationEntity.builder()
				.id(id)
				.name(name)
				.description(description)
				.enabled(enabled)
				.homeUri(homeUri)
				.documentationUrl(documentationUrl)
				.build();
		applicationSpringDataRepository.save(applicationEntity);
	}
	
}
