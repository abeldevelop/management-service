package com.abeldevelop.architecture.service.management.dto.route;

public class RouteConstants {

	private RouteConstants() {
		
	}
	
	public static final int NAME_MIN_SIZE = 5;
    public static final int NAME_MAX_SIZE = 50;
    
    public static final int DESCRIPTION_MIN_SIZE = 5;
    public static final int DESCRIPTION_MAX_SIZE = 255;
    
    public static final int GIT_URL_MIN_SIZE = 5;
    public static final int GIT_URL_MAX_SIZE = 255;
    
    public static final int DOCUMENTATION_URL_MIN_SIZE = 5;
    public static final int DOCUMENTATION_URL_MAX_SIZE = 255;
    
    public static final String ID_FIELD_NOTES = "ID of the route";
    public static final String ID_FIELD_EXAMPLE = "1";
    
    public static final String DESCRIPTION_FIELD_NOTES = "Description of the service";
    
    public static final String PORT_FIELD_NOTES = "Port of the service";
    public static final String PORT_FIELD_EXAMPLE = "8080";
    
    public static final String GIT_URL_FIELD_NOTES = "Git URL of the service";
    public static final String GIT_URL_FIELD_EXAMPLE = "http://local.com/architecture";
    
    public static final String DOCUMENTATION_URL_FIELD_NOTES = "Documentation URL for the application";
    public static final String DOCUMENTATION_URL_FIELD_EXAMPLE = "http://local.com/doc/architecture/";
    
    public static final String APPLICATION_VERSION_FIELD_NOTES = "Version of the application";
    public static final String APPLICATION_VERSION_FIELD_EXAMPLE = "1";
    
    public static final String PAGINATION_FIELD_NOTES = "Resource with pagination information";
    
    public static final String SERVICES_FIELD_NOTES = "List of services";
}
