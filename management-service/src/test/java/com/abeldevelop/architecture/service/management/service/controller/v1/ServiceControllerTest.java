package com.abeldevelop.architecture.service.management.service.controller.v1;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

import com.abeldevelop.architecture.library.common.dto.exception.ErrorResponseResource;
import com.abeldevelop.architecture.service.management.dto.service.CreateServiceRequestResource;
import com.abeldevelop.architecture.service.management.dto.service.ServicePaginationResponseResource;
import com.abeldevelop.architecture.service.management.dto.service.ServiceResponseResource;
import com.abeldevelop.architecture.service.management.dto.service.UpdateServiceRequestResource;
import com.abeldevelop.architecture.service.management.model.ApplicationEntity;
import com.abeldevelop.architecture.service.management.model.ServiceEntity;
import com.abeldevelop.architecture.service.management.repository.springdata.ApplicationSpringDataRepository;
import com.abeldevelop.architecture.service.management.repository.springdata.ServiceSpringDataRepository;
import com.abeldevelop.architecture.starter.test.CommonTestController;
import com.abeldevelop.architecture.starter.test.EndpointData;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ServiceControllerTest extends CommonTestController {

	private static final String BASE_ENDPOINT = "/v1/application/blog/service";
	private static final String APPLICATION_ID = "blog";
	
	private static final String NAME_OK_VALUE = "blog-service";
	private static final String DESCRIPTION_OK_VALUE = "Blog Service";
	private static final Integer PORT_OK_VALUE = 8080;
	private static final String GIT_URL_OK_VALUE = "http://blog.com";
	private static final String DOCUMENTATION_URL_OK_VALUE = "http://blog.com";
	
	private static final String VALUE_WITH_MORE_THAN_50_CHARACTERS = "012345678901234567890123456789012345678901234567890";
	private static final String VALUE_WITH_MORE_THAN_255_CHARACTERS = "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345";
	
	@Autowired
	private ApplicationSpringDataRepository applicationSpringDataRepository;
	
	@Autowired
	private ServiceSpringDataRepository serviceSpringDataRepository;
	
	@BeforeEach
	public void setUp() {
		if(!applicationSpringDataRepository.findById(APPLICATION_ID).isPresent()) {
			ApplicationEntity applicationEntity = ApplicationEntity.builder()
					.id("blog")
					.name("Blog")
					.description("Blog Application")
					.enabled(true)
					.homeUri("http://blog.com")
					.documentationUrl("http://blog.com")
					.build();
			applicationSpringDataRepository.save(applicationEntity);
		}
	}
	
	//////////////////////////////
	//		executeCreate		//
	//////////////////////////////
	
	@Test
	public void createService_ko_nameNotNull() throws Exception {
		EndpointData<ErrorResponseResource> endpointData = EndpointData.<ErrorResponseResource>builder()
				.method(HttpMethod.POST)
				.endpoint(BASE_ENDPOINT)
				.content(createServiceRequestResource(null, DESCRIPTION_OK_VALUE, PORT_OK_VALUE, GIT_URL_OK_VALUE, DOCUMENTATION_URL_OK_VALUE))
				.expectedStatus(HttpStatus.BAD_REQUEST)
				.typeReturn(ErrorResponseResource.class)
				.build();
		
		ErrorResponseResource response = makeRestRequest(endpointData);
		
		assertEquals("El nombre del servicio es obligatorio", response.getMessage());
	}
	
	@Test
	public void createService_ko_nameMinSize() throws Exception {
		EndpointData<ErrorResponseResource> endpointData = EndpointData.<ErrorResponseResource>builder()
				.method(HttpMethod.POST)
				.endpoint(BASE_ENDPOINT)
				.content(createServiceRequestResource("a", DESCRIPTION_OK_VALUE, PORT_OK_VALUE, GIT_URL_OK_VALUE, DOCUMENTATION_URL_OK_VALUE))
				.expectedStatus(HttpStatus.BAD_REQUEST)
				.typeReturn(ErrorResponseResource.class)
				.build();
		
		ErrorResponseResource response = makeRestRequest(endpointData);
		
		assertEquals("El nombre del servicio debe tener entre 5 y 50 caracteres", response.getMessage());
	}
	
	@Test
	public void createService_ko_nameMaxSize() throws Exception {
		EndpointData<ErrorResponseResource> endpointData = EndpointData.<ErrorResponseResource>builder()
				.method(HttpMethod.POST)
				.endpoint(BASE_ENDPOINT)
				.content(createServiceRequestResource(VALUE_WITH_MORE_THAN_50_CHARACTERS, DESCRIPTION_OK_VALUE, PORT_OK_VALUE, GIT_URL_OK_VALUE, DOCUMENTATION_URL_OK_VALUE))
				.expectedStatus(HttpStatus.BAD_REQUEST)
				.typeReturn(ErrorResponseResource.class)
				.build();
		
		ErrorResponseResource response = makeRestRequest(endpointData);
		
		assertEquals("El nombre del servicio debe tener entre 5 y 50 caracteres", response.getMessage());
	}
	
	@Test
	public void createService_ko_descriptionNotNull() throws Exception {
		EndpointData<ErrorResponseResource> endpointData = EndpointData.<ErrorResponseResource>builder()
				.method(HttpMethod.POST)
				.endpoint(BASE_ENDPOINT)
				.content(createServiceRequestResource(NAME_OK_VALUE, null, PORT_OK_VALUE, GIT_URL_OK_VALUE, DOCUMENTATION_URL_OK_VALUE))
				.expectedStatus(HttpStatus.BAD_REQUEST)
				.typeReturn(ErrorResponseResource.class)
				.build();
		
		ErrorResponseResource response = makeRestRequest(endpointData);
		
		assertEquals("La descripcion del servicio es obligatoria", response.getMessage());
	}
	
	@Test
	public void createService_ko_descriptionMinSize() throws Exception {
		EndpointData<ErrorResponseResource> endpointData = EndpointData.<ErrorResponseResource>builder()
				.method(HttpMethod.POST)
				.endpoint(BASE_ENDPOINT)
				.content(createServiceRequestResource(NAME_OK_VALUE, "a", PORT_OK_VALUE, GIT_URL_OK_VALUE, DOCUMENTATION_URL_OK_VALUE))
				.expectedStatus(HttpStatus.BAD_REQUEST)
				.typeReturn(ErrorResponseResource.class)
				.build();
		
		ErrorResponseResource response = makeRestRequest(endpointData);
		
		assertEquals("La descripcion del servicio debe tener entre 5 y 255 caracteres", response.getMessage());
	}
	
	@Test
	public void createService_ko_descriptionMaxSize() throws Exception {
		EndpointData<ErrorResponseResource> endpointData = EndpointData.<ErrorResponseResource>builder()
				.method(HttpMethod.POST)
				.endpoint(BASE_ENDPOINT)
				.content(createServiceRequestResource(NAME_OK_VALUE, VALUE_WITH_MORE_THAN_255_CHARACTERS, PORT_OK_VALUE, GIT_URL_OK_VALUE, DOCUMENTATION_URL_OK_VALUE))
				.expectedStatus(HttpStatus.BAD_REQUEST)
				.typeReturn(ErrorResponseResource.class)
				.build();
		
		ErrorResponseResource response = makeRestRequest(endpointData);
		
		assertEquals("La descripcion del servicio debe tener entre 5 y 255 caracteres", response.getMessage());
	}
	
	@Test
	public void createService_ko_gitUrlNotNull() throws Exception {
		EndpointData<ErrorResponseResource> endpointData = EndpointData.<ErrorResponseResource>builder()
				.method(HttpMethod.POST)
				.endpoint(BASE_ENDPOINT)
				.content(createServiceRequestResource(NAME_OK_VALUE, DESCRIPTION_OK_VALUE, PORT_OK_VALUE, null, DOCUMENTATION_URL_OK_VALUE))
				.expectedStatus(HttpStatus.BAD_REQUEST)
				.typeReturn(ErrorResponseResource.class)
				.build();
		
		ErrorResponseResource response = makeRestRequest(endpointData);
		
		assertEquals("La URL del respositorio git es obligatoria", response.getMessage());
	}
	
	@Test
	public void createService_ko_gitUrlMinSize() throws Exception {
		EndpointData<ErrorResponseResource> endpointData = EndpointData.<ErrorResponseResource>builder()
				.method(HttpMethod.POST)
				.endpoint(BASE_ENDPOINT)
				.content(createServiceRequestResource(NAME_OK_VALUE, DESCRIPTION_OK_VALUE, PORT_OK_VALUE, "a", DOCUMENTATION_URL_OK_VALUE))
				.expectedStatus(HttpStatus.BAD_REQUEST)
				.typeReturn(ErrorResponseResource.class)
				.build();
		
		ErrorResponseResource response = makeRestRequest(endpointData);
		
		assertEquals("La URL del respositorio git debe tener entre 5 y 255 caracteres", response.getMessage());
	}
	
	@Test
	public void createService_ko_gitUrlMaxSize() throws Exception {
		EndpointData<ErrorResponseResource> endpointData = EndpointData.<ErrorResponseResource>builder()
				.method(HttpMethod.POST)
				.endpoint(BASE_ENDPOINT)
				.content(createServiceRequestResource(NAME_OK_VALUE, DESCRIPTION_OK_VALUE, PORT_OK_VALUE, VALUE_WITH_MORE_THAN_255_CHARACTERS, DOCUMENTATION_URL_OK_VALUE))
				.expectedStatus(HttpStatus.BAD_REQUEST)
				.typeReturn(ErrorResponseResource.class)
				.build();
		
		ErrorResponseResource response = makeRestRequest(endpointData);
		
		assertEquals("La URL del respositorio git debe tener entre 5 y 255 caracteres", response.getMessage());
	}
	
	@Test
	public void createService_ko_documentationUrlNotNull() throws Exception {
		EndpointData<ErrorResponseResource> endpointData = EndpointData.<ErrorResponseResource>builder()
				.method(HttpMethod.POST)
				.endpoint(BASE_ENDPOINT)
				.content(createServiceRequestResource(NAME_OK_VALUE, DESCRIPTION_OK_VALUE, PORT_OK_VALUE, GIT_URL_OK_VALUE, null))
				.expectedStatus(HttpStatus.BAD_REQUEST)
				.typeReturn(ErrorResponseResource.class)
				.build();
		
		ErrorResponseResource response = makeRestRequest(endpointData);
		
		assertEquals("La URL de la documentacion es obligatoria", response.getMessage());
	}
	
	@Test
	public void createService_ko_documentationUrlMinSize() throws Exception {
		EndpointData<ErrorResponseResource> endpointData = EndpointData.<ErrorResponseResource>builder()
				.method(HttpMethod.POST)
				.endpoint(BASE_ENDPOINT)
				.content(createServiceRequestResource(NAME_OK_VALUE, DESCRIPTION_OK_VALUE, PORT_OK_VALUE, GIT_URL_OK_VALUE, "a"))
				.expectedStatus(HttpStatus.BAD_REQUEST)
				.typeReturn(ErrorResponseResource.class)
				.build();
		
		ErrorResponseResource response = makeRestRequest(endpointData);
		
		assertEquals("La URL de la documentacion debe tener entre 5 y 255 caracteres", response.getMessage());
	}
	
	@Test
	public void createService_ko_documentationUrlMaxSize() throws Exception {
		EndpointData<ErrorResponseResource> endpointData = EndpointData.<ErrorResponseResource>builder()
				.method(HttpMethod.POST)
				.endpoint(BASE_ENDPOINT)
				.content(createServiceRequestResource(NAME_OK_VALUE, DESCRIPTION_OK_VALUE, PORT_OK_VALUE, GIT_URL_OK_VALUE, VALUE_WITH_MORE_THAN_255_CHARACTERS))
				.expectedStatus(HttpStatus.BAD_REQUEST)
				.typeReturn(ErrorResponseResource.class)
				.build();
		
		ErrorResponseResource response = makeRestRequest(endpointData);
		
		assertEquals("La URL de la documentacion debe tener entre 5 y 255 caracteres", response.getMessage());
	}
	
	@Test
	public void createService_ko_serviceExist() throws Exception {
		serviceSpringDataRepository.deleteAll();
		serviceSpringDataRepository.save(ServiceEntity.builder().name(NAME_OK_VALUE).description(DESCRIPTION_OK_VALUE).port(PORT_OK_VALUE).gitUrl(GIT_URL_OK_VALUE).documentationUrl(DOCUMENTATION_URL_OK_VALUE).application(applicationSpringDataRepository.findById(APPLICATION_ID).get()).build());
		EndpointData<ErrorResponseResource> endpointData = EndpointData.<ErrorResponseResource>builder()
				.method(HttpMethod.POST)
				.endpoint(BASE_ENDPOINT)
				.content(createServiceRequestResource(NAME_OK_VALUE, DESCRIPTION_OK_VALUE, PORT_OK_VALUE, GIT_URL_OK_VALUE, DOCUMENTATION_URL_OK_VALUE))
				.expectedStatus(HttpStatus.BAD_REQUEST)
				.typeReturn(ErrorResponseResource.class)
				.build();
		
		ErrorResponseResource response = makeRestRequest(endpointData);
		
		assertEquals("Ya existe un servicio con el nombre blog-service", response.getMessage());
	}
	
	@Test
	public void createService_ko_portExist() throws Exception {
		serviceSpringDataRepository.deleteAll();
		serviceSpringDataRepository.save(ServiceEntity.builder().name(NAME_OK_VALUE).description(DESCRIPTION_OK_VALUE).port(PORT_OK_VALUE).gitUrl(GIT_URL_OK_VALUE).documentationUrl(DOCUMENTATION_URL_OK_VALUE).application(applicationSpringDataRepository.findById(APPLICATION_ID).get()).build());
		EndpointData<ErrorResponseResource> endpointData = EndpointData.<ErrorResponseResource>builder()
				.method(HttpMethod.POST)
				.endpoint(BASE_ENDPOINT)
				.content(createServiceRequestResource(NAME_OK_VALUE+"2", DESCRIPTION_OK_VALUE, PORT_OK_VALUE, GIT_URL_OK_VALUE, DOCUMENTATION_URL_OK_VALUE))
				.expectedStatus(HttpStatus.BAD_REQUEST)
				.typeReturn(ErrorResponseResource.class)
				.build();
		
		ErrorResponseResource response = makeRestRequest(endpointData);
		
		assertEquals("Ya existe un servicio con el puerto 8080", response.getMessage());
	}
	
	@Test
	public void createService_ok_forcePort() throws Exception {
		serviceSpringDataRepository.deleteAll();
		EndpointData<ServiceResponseResource> endpointData = EndpointData.<ServiceResponseResource>builder()
				.method(HttpMethod.POST)
				.endpoint(BASE_ENDPOINT)
				.content(createServiceRequestResource(NAME_OK_VALUE, DESCRIPTION_OK_VALUE, PORT_OK_VALUE, GIT_URL_OK_VALUE, DESCRIPTION_OK_VALUE))
				.expectedStatus(HttpStatus.CREATED)
				.typeReturn(ServiceResponseResource.class)
				.build();
		
		ServiceResponseResource response = makeRestRequest(endpointData);
		
		assertEquals(NAME_OK_VALUE, response.getName());
		assertEquals(DESCRIPTION_OK_VALUE, response.getDescription());
		assertEquals(PORT_OK_VALUE, response.getPort());
		assertEquals(GIT_URL_OK_VALUE, response.getGitUrl());
		assertEquals(DESCRIPTION_OK_VALUE, response.getDocumentationUrl());
	}
	
	@Test
	public void createService_ok_dinamycPort() throws Exception {
		serviceSpringDataRepository.deleteAll();
		EndpointData<ServiceResponseResource> endpointData = EndpointData.<ServiceResponseResource>builder()
				.method(HttpMethod.POST)
				.endpoint(BASE_ENDPOINT)
				.content(createServiceRequestResource(NAME_OK_VALUE, DESCRIPTION_OK_VALUE, null, GIT_URL_OK_VALUE, DESCRIPTION_OK_VALUE))
				.expectedStatus(HttpStatus.CREATED)
				.typeReturn(ServiceResponseResource.class)
				.build();
		
		ServiceResponseResource response = makeRestRequest(endpointData);
		
		assertEquals(NAME_OK_VALUE, response.getName());
		assertEquals(DESCRIPTION_OK_VALUE, response.getDescription());
		assertEquals(8001, response.getPort());
		assertEquals(GIT_URL_OK_VALUE, response.getGitUrl());
		assertEquals(DESCRIPTION_OK_VALUE, response.getDocumentationUrl());
	}
	
	private CreateServiceRequestResource createServiceRequestResource(String name, String description, Integer port, String gitUrl, String documentationUrl) {
		return CreateServiceRequestResource.builder()
				.name(name)
				.description(description)
				.port(port)
				.gitUrl(gitUrl)
				.documentationUrl(documentationUrl)
				.build();
	}
	
	//////////////////////////////
	//		executeUpdate		//
	//////////////////////////////
	
	@Test
	public void updateService_ko_descriptionNotNull() throws Exception {
		EndpointData<ErrorResponseResource> endpointData = EndpointData.<ErrorResponseResource>builder()
				.method(HttpMethod.PUT)
				.endpoint(BASE_ENDPOINT + "/" + NAME_OK_VALUE)
				.content(updateServiceRequestResource(null, GIT_URL_OK_VALUE, DOCUMENTATION_URL_OK_VALUE))
				.expectedStatus(HttpStatus.BAD_REQUEST)
				.typeReturn(ErrorResponseResource.class)
				.build();
		
		ErrorResponseResource response = makeRestRequest(endpointData);
		
		assertEquals("La descripcion del servicio es obligatoria", response.getMessage());
	}
	
	@Test
	public void updateService_ko_descriptionMinSize() throws Exception {
		EndpointData<ErrorResponseResource> endpointData = EndpointData.<ErrorResponseResource>builder()
				.method(HttpMethod.PUT)
				.endpoint(BASE_ENDPOINT + "/" + NAME_OK_VALUE)
				.content(updateServiceRequestResource("a", GIT_URL_OK_VALUE, DOCUMENTATION_URL_OK_VALUE))
				.expectedStatus(HttpStatus.BAD_REQUEST)
				.typeReturn(ErrorResponseResource.class)
				.build();
		
		ErrorResponseResource response = makeRestRequest(endpointData);
		
		assertEquals("La descripcion del servicio debe tener entre 5 y 255 caracteres", response.getMessage());
	}
	
	@Test
	public void updateService_ko_descriptionMaxSize() throws Exception {
		EndpointData<ErrorResponseResource> endpointData = EndpointData.<ErrorResponseResource>builder()
				.method(HttpMethod.PUT)
				.endpoint(BASE_ENDPOINT + "/" + NAME_OK_VALUE)
				.content(updateServiceRequestResource(VALUE_WITH_MORE_THAN_255_CHARACTERS, GIT_URL_OK_VALUE, DOCUMENTATION_URL_OK_VALUE))
				.expectedStatus(HttpStatus.BAD_REQUEST)
				.typeReturn(ErrorResponseResource.class)
				.build();
		
		ErrorResponseResource response = makeRestRequest(endpointData);
		
		assertEquals("La descripcion del servicio debe tener entre 5 y 255 caracteres", response.getMessage());
	}
	
	@Test
	public void updateService_ko_gitUrlNotNull() throws Exception {
		EndpointData<ErrorResponseResource> endpointData = EndpointData.<ErrorResponseResource>builder()
				.method(HttpMethod.PUT)
				.endpoint(BASE_ENDPOINT + "/" + NAME_OK_VALUE)
				.content(updateServiceRequestResource(DESCRIPTION_OK_VALUE, null, DOCUMENTATION_URL_OK_VALUE))
				.expectedStatus(HttpStatus.BAD_REQUEST)
				.typeReturn(ErrorResponseResource.class)
				.build();
		
		ErrorResponseResource response = makeRestRequest(endpointData);
		
		assertEquals("La URL del respositorio git es obligatoria", response.getMessage());
	}
	
	@Test
	public void updateService_ko_gitUrlMinSize() throws Exception {
		EndpointData<ErrorResponseResource> endpointData = EndpointData.<ErrorResponseResource>builder()
				.method(HttpMethod.PUT)
				.endpoint(BASE_ENDPOINT + "/" + NAME_OK_VALUE)
				.content(updateServiceRequestResource(DESCRIPTION_OK_VALUE, "a", DOCUMENTATION_URL_OK_VALUE))
				.expectedStatus(HttpStatus.BAD_REQUEST)
				.typeReturn(ErrorResponseResource.class)
				.build();
		
		ErrorResponseResource response = makeRestRequest(endpointData);
		
		assertEquals("La URL del respositorio git debe tener entre 5 y 255 caracteres", response.getMessage());
	}
	
	@Test
	public void updateService_ko_gitUrlMaxSize() throws Exception {
		EndpointData<ErrorResponseResource> endpointData = EndpointData.<ErrorResponseResource>builder()
				.method(HttpMethod.PUT)
				.endpoint(BASE_ENDPOINT + "/" + NAME_OK_VALUE)
				.content(updateServiceRequestResource(DESCRIPTION_OK_VALUE, VALUE_WITH_MORE_THAN_255_CHARACTERS, DOCUMENTATION_URL_OK_VALUE))
				.expectedStatus(HttpStatus.BAD_REQUEST)
				.typeReturn(ErrorResponseResource.class)
				.build();
		
		ErrorResponseResource response = makeRestRequest(endpointData);
		
		assertEquals("La URL del respositorio git debe tener entre 5 y 255 caracteres", response.getMessage());
	}
	
	@Test
	public void updateService_ko_documentationUrlNotNull() throws Exception {
		EndpointData<ErrorResponseResource> endpointData = EndpointData.<ErrorResponseResource>builder()
				.method(HttpMethod.PUT)
				.endpoint(BASE_ENDPOINT + "/" + NAME_OK_VALUE)
				.content(updateServiceRequestResource(DESCRIPTION_OK_VALUE, GIT_URL_OK_VALUE, null))
				.expectedStatus(HttpStatus.BAD_REQUEST)
				.typeReturn(ErrorResponseResource.class)
				.build();
		
		ErrorResponseResource response = makeRestRequest(endpointData);
		
		assertEquals("La URL de la documentacion es obligatoria", response.getMessage());
	}
	
	@Test
	public void updateService_ko_documentationUrlMinSize() throws Exception {
		EndpointData<ErrorResponseResource> endpointData = EndpointData.<ErrorResponseResource>builder()
				.method(HttpMethod.PUT)
				.endpoint(BASE_ENDPOINT + "/" + NAME_OK_VALUE)
				.content(updateServiceRequestResource(DESCRIPTION_OK_VALUE, GIT_URL_OK_VALUE, "a"))
				.expectedStatus(HttpStatus.BAD_REQUEST)
				.typeReturn(ErrorResponseResource.class)
				.build();
		
		ErrorResponseResource response = makeRestRequest(endpointData);
		
		assertEquals("La URL de la documentacion debe tener entre 5 y 255 caracteres", response.getMessage());
	}
	
	@Test
	public void updateService_ko_documentationUrlMaxSize() throws Exception {
		EndpointData<ErrorResponseResource> endpointData = EndpointData.<ErrorResponseResource>builder()
				.method(HttpMethod.PUT)
				.endpoint(BASE_ENDPOINT + "/" + NAME_OK_VALUE)
				.content(updateServiceRequestResource(DESCRIPTION_OK_VALUE, GIT_URL_OK_VALUE, VALUE_WITH_MORE_THAN_255_CHARACTERS))
				.expectedStatus(HttpStatus.BAD_REQUEST)
				.typeReturn(ErrorResponseResource.class)
				.build();
		
		ErrorResponseResource response = makeRestRequest(endpointData);
		
		assertEquals("La URL de la documentacion debe tener entre 5 y 255 caracteres", response.getMessage());
	}
	
	@Test
	public void updateService_ko_serviceWithNameNotExist() throws Exception {
		serviceSpringDataRepository.deleteAll();
		EndpointData<ErrorResponseResource> endpointData = EndpointData.<ErrorResponseResource>builder()
				.method(HttpMethod.PUT)
				.endpoint(BASE_ENDPOINT + "/" + NAME_OK_VALUE)
				.content(updateServiceRequestResource(DESCRIPTION_OK_VALUE, GIT_URL_OK_VALUE, DOCUMENTATION_URL_OK_VALUE))
				.expectedStatus(HttpStatus.BAD_REQUEST)
				.typeReturn(ErrorResponseResource.class)
				.build();
		
		ErrorResponseResource response = makeRestRequest(endpointData);
		
		assertEquals("No existe un servicio con el nombre blog-service", response.getMessage());
	}
	
	@Test
	public void updateService_ok() throws Exception {
		serviceSpringDataRepository.deleteAll();
		applicationSpringDataRepository.findById(APPLICATION_ID).get();
		serviceSpringDataRepository.save(ServiceEntity.builder().name(NAME_OK_VALUE).description(DESCRIPTION_OK_VALUE).port(PORT_OK_VALUE).gitUrl(GIT_URL_OK_VALUE).documentationUrl(DOCUMENTATION_URL_OK_VALUE).application(applicationSpringDataRepository.findById(APPLICATION_ID).get()).build());
		EndpointData<ServiceResponseResource> endpointData = EndpointData.<ServiceResponseResource>builder()
				.method(HttpMethod.PUT)
				.endpoint(BASE_ENDPOINT + "/" + NAME_OK_VALUE)
				.content(updateServiceRequestResource(DESCRIPTION_OK_VALUE, GIT_URL_OK_VALUE, DOCUMENTATION_URL_OK_VALUE))
				.expectedStatus(HttpStatus.OK)
				.typeReturn(ServiceResponseResource.class)
				.build();
		
		ServiceResponseResource response = makeRestRequest(endpointData);
		
		assertEquals(NAME_OK_VALUE, response.getName());
		assertEquals(DESCRIPTION_OK_VALUE, response.getDescription());
		assertEquals(PORT_OK_VALUE, response.getPort());
		assertEquals(GIT_URL_OK_VALUE, response.getGitUrl());
		assertEquals(DOCUMENTATION_URL_OK_VALUE, response.getDocumentationUrl());
	}
	
	private UpdateServiceRequestResource updateServiceRequestResource(String description, String gitUrl, String documentationUrl) {
		return UpdateServiceRequestResource.builder()
				.description(description)
				.gitUrl(gitUrl)
				.documentationUrl(documentationUrl)
				.build();
	}
	
	//////////////////////////////
	//		executeDelete		//
	//////////////////////////////
	
	@Test
	public void deleteService_ko_serviceWithNameNotExist() throws Exception {
		serviceSpringDataRepository.deleteAll();
		EndpointData<ErrorResponseResource> endpointData = EndpointData.<ErrorResponseResource>builder()
				.method(HttpMethod.DELETE)
				.endpoint(BASE_ENDPOINT + "/" + NAME_OK_VALUE)
				.expectedStatus(HttpStatus.BAD_REQUEST)
				.typeReturn(ErrorResponseResource.class)
				.build();
		
		ErrorResponseResource response = makeRestRequest(endpointData);
		
		assertEquals("No existe un servicio con el nombre blog-service", response.getMessage());
	}
	
	@Test
	public void deleteService_ok() throws Exception {
		serviceSpringDataRepository.deleteAll();
		serviceSpringDataRepository.save(ServiceEntity.builder().name(NAME_OK_VALUE).description(DESCRIPTION_OK_VALUE).port(PORT_OK_VALUE).gitUrl(GIT_URL_OK_VALUE).documentationUrl(DOCUMENTATION_URL_OK_VALUE).application(applicationSpringDataRepository.findById(APPLICATION_ID).get()).build());
		EndpointData<Void> endpointData = EndpointData.<Void>builder()
				.method(HttpMethod.DELETE)
				.endpoint(BASE_ENDPOINT + "/" + NAME_OK_VALUE)
				.expectedStatus(HttpStatus.NO_CONTENT)
				.typeReturn(Void.class)
				.build();
		
		makeRestRequest(endpointData);
		
		Optional<ServiceEntity> result = serviceSpringDataRepository.findByName(NAME_OK_VALUE);
		
		assertEquals(false, result.isPresent());
	}
	
	//////////////////////////////
	//		executeFind			//
	//////////////////////////////
	
	@Test
	public void findServiceByName_ko_serviceWithNameNotExist() throws Exception {
		serviceSpringDataRepository.deleteAll();
		EndpointData<ErrorResponseResource> endpointData = EndpointData.<ErrorResponseResource>builder()
				.method(HttpMethod.GET)
				.endpoint(BASE_ENDPOINT + "/" + NAME_OK_VALUE)
				.expectedStatus(HttpStatus.BAD_REQUEST)
				.typeReturn(ErrorResponseResource.class)
				.build();
		
		ErrorResponseResource response = makeRestRequest(endpointData);
		
		assertEquals("No existe un servicio con el nombre blog-service", response.getMessage());
	}
	
	@Test
	public void findServiceByName_ok() throws Exception {
		serviceSpringDataRepository.deleteAll();
		serviceSpringDataRepository.save(ServiceEntity.builder().name(NAME_OK_VALUE).description(DESCRIPTION_OK_VALUE).port(PORT_OK_VALUE).gitUrl(GIT_URL_OK_VALUE).documentationUrl(DOCUMENTATION_URL_OK_VALUE).application(applicationSpringDataRepository.findById(APPLICATION_ID).get()).build());
		EndpointData<ServiceResponseResource> endpointData = EndpointData.<ServiceResponseResource>builder()
				.method(HttpMethod.GET)
				.endpoint(BASE_ENDPOINT + "/" + NAME_OK_VALUE)
				.expectedStatus(HttpStatus.OK)
				.typeReturn(ServiceResponseResource.class)
				.build();
		
		ServiceResponseResource response = makeRestRequest(endpointData);
		
		assertEquals(NAME_OK_VALUE, response.getName());
		assertEquals(DESCRIPTION_OK_VALUE, response.getDescription());
		assertEquals(PORT_OK_VALUE, response.getPort());
		assertEquals(GIT_URL_OK_VALUE, response.getGitUrl());
		assertEquals(DOCUMENTATION_URL_OK_VALUE, response.getDocumentationUrl());
	}
	
	//////////////////////////////
	//		executeFindAll		//
	//////////////////////////////
	
	@Test
	public void findAllServices_ok_noParams() throws Exception {
		EndpointData<ServicePaginationResponseResource> endpointData = EndpointData.<ServicePaginationResponseResource>builder()
				.method(HttpMethod.GET)
				.endpoint(BASE_ENDPOINT)
				.expectedStatus(HttpStatus.OK)
				.typeReturn(ServicePaginationResponseResource.class)
				.build();
		
		makeRestRequest(endpointData);
		
	}
	
	@Test
	public void findAllServices_ok_allParams() throws Exception {
		Map<String, String> params = new HashMap<>();
		params.put("name", "o");
		
		EndpointData<ServicePaginationResponseResource> endpointData = EndpointData.<ServicePaginationResponseResource>builder()
				.method(HttpMethod.GET)
				.endpoint(BASE_ENDPOINT)
				.params(params)
				.expectedStatus(HttpStatus.OK)
				.typeReturn(ServicePaginationResponseResource.class)
				.build();
		
		makeRestRequest(endpointData);
	}
}
