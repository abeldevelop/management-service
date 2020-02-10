package com.abeldevelop.architecture.service.management.service.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Component
@ConfigurationProperties("management-error-code")
public class ErrorCodeProperties {

}