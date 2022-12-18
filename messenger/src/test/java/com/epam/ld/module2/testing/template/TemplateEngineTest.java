package com.epam.ld.module2.testing.template;

import com.epam.ld.module2.testing.Client;
import com.epam.ld.module2.testing.MailServer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class TemplateEngineTest {


    private TemplateEngine templateEngine;

    @Mock
    private Template template;
    @Mock
    private Client client;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        templateEngine = new TemplateEngine();
    }

    @Test
    void sendMailTest_positive() {
        String message = "message";
        String address = "address";
        assertEquals("message sent.", templateEngine.generateMessage(template, client));
    }
}
