package com.unicomer.jamaica.config;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.web.servlet.LocaleResolver;

@SpringJUnitConfig
@SpringBootTest
class LocaleConfigurationTest {

    @Autowired
    private LocaleConfiguration localeConfiguration;

    @Mock
    private LocaleConfiguration localeConfigurationMock;

    ResourceBundleMessageSource rs;
    LocaleResolver localeResolver;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        rs = new ResourceBundleMessageSource();
        rs.setBasename("messages");
        rs.setDefaultEncoding("UTF-8");
        rs.setUseCodeAsDefaultMessage(true);

    }

    @Test
    void messageSource() {
        assertNotNull(localeConfiguration.resourceBundleMessageSource());
    }

    @Test
    void messageSource_mock() {
        ResourceBundleMessageSource rs = mock(ResourceBundleMessageSource.class);
        when(localeConfigurationMock.resourceBundleMessageSource()).thenReturn(rs);
        assertNotNull(localeConfigurationMock.resourceBundleMessageSource());
    }

    @Test
    void localResolver() {
        assertNotNull(localeConfiguration.localResolver());
    }

    @Test
    void localResolver_mock() {
        LocaleResolver localeResolver = mock(LocaleResolver.class);
        when(localeConfigurationMock.localResolver()).thenReturn(localeResolver);
        assertNotNull(localeConfigurationMock.localResolver());
    }
}