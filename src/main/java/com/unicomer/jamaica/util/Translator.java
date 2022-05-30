package com.unicomer.jamaica.util;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

@Component
public class Translator {
	
	@Value("${app.defaultLocale}")
	private String defaultLocale;
	
	private ResourceBundleMessageSource resourceBundleMessageSource;
	
	public Translator(@Qualifier("messages") ResourceBundleMessageSource resourceBundleMessageSource) {
		this.resourceBundleMessageSource = resourceBundleMessageSource;
	}
	
	public String toLocale(String code) {
		Locale locale = new Locale(defaultLocale);
		return resourceBundleMessageSource.getMessage(code, null,locale);
	}

}
