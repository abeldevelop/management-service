package com.abeldevelop.architecture.service.management.dto.service;

public enum ServiceSort {

	NAME_DESC("NAME_DESC");
    
    private String value;
    
    private ServiceSort(String value) {
        this.value = value;
    }
    
    public String getSort() {
        return value;
    }
}