package com.unicomer.jamaica.config;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

@Configuration
public class LocaleConfiguration {
	
	@Value("${app.baseName}")
	private String baseName;
	@Value("${app.defaultLocale}")
	private String defaultLocale;
	
	
	@Bean(name="messages")
	public ResourceBundleMessageSource resourceBundleMessageSource() {
		ResourceBundleMessageSource rs = new ResourceBundleMessageSource();
		rs.setBasename(baseName);
		rs.setDefaultEncoding("UTF-8");
		//rs.setUseCodeAsDefaultMessage(true);
		return rs;
	}


	@Bean
	public LocaleResolver localResolver() {
		AcceptHeaderLocaleResolver acceptHeaderLocaleResolver = new AcceptHeaderLocaleResolver();
		acceptHeaderLocaleResolver.setDefaultLocale(new Locale(defaultLocale));
		return acceptHeaderLocaleResolver;
	}

	@Bean
	public LocalValidatorFactoryBean getValidator() {
		LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
		bean.setValidationMessageSource(resourceBundleMessageSource());
		return bean;
	}

}
