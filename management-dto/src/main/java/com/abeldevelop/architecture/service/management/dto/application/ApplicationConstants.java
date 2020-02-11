package com.abeldevelop.architecture.service.management.dto.application;

public class ApplicationConstants {

	private ApplicationConstants() {
		
	}
	
	public static final int NAME_MIN_SIZE = 3;
    public static final int NAME_MAX_SIZE = 50;
    
    public static final int DESCRIPTION_MIN_SIZE = 5;
    public static final int DESCRIPTION_MAX_SIZE = 255;
    
    public static final int HOME_URI_MIN_SIZE = 5;
    public static final int HOME_URI_MAX_SIZE = 255;
    
    public static final int DOCUMENTATION_URL_MIN_SIZE = 5;
    public static final int DOCUMENTATION_URL_MAX_SIZE = 255;
    
    
    public static final String ID_FIELD_NOTES = "ID of the application";
    public static final String ID_FIELD_EXAMPLE = "blog";
    
    public static final String NAME_FIELD_NOTES = "Name of the application";
    public static final String NAME_FIELD_EXAMPLE = "Blog";
    
    public static final String DESCRIPTION_FIELD_NOTES = "Description of the application";
    
    public static final String ENABLED_FIELD_NOTES = "Set if application is enabled or disabled (Default false)";
    public static final String ENABLED_FIELD_EXAMPLE = "true";
    
    public static final String HOME_URI_FIELD_NOTES = "Home URI for the application";
    public static final String HOME_URI_FIELD_EXAMPLE = "http://local.com/architecture";
    
    public static final String DOCUMENTATION_URL_FIELD_NOTES = "Documentation URL for the application";
    public static final String DOCUMENTATION_URL_FIELD_EXAMPLE = "http://local.com/doc/architecture/";
    
    public static final String VERSION_FIELD_NOTES = "Version of the application";
    public static final String VERSION_FIELD_EXAMPLE = "1";
    
    public static final String PAGINATION_FIELD_NOTES = "Resource with pagination information";
    
    public static final String APPLICATIONS_FIELD_NOTES = "List of applications";
}