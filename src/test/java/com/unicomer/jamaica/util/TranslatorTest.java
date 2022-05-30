package com.unicomer.jamaica.util;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig
@SpringBootTest
class TranslatorTest {

    @Autowired
    Translator translator;

    @Mock
    Translator translatorMock;

    String code;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void toLocale() {
        code = "deleted.customer";
        assertNotNull(translator.toLocale(code));
    }

    @Test
    void toLocale_mock() {
        code = "deleted.customer";
        when(translatorMock.toLocale(code)).thenReturn(code);
        assertNotNull(translatorMock.toLocale(code));
    }
}