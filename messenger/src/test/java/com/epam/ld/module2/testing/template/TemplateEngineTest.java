package com.epam.ld.module2.testing.template;

import com.epam.ld.module2.testing.Client;
import com.epam.ld.module2.testing.MailServer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TemplateEngineTest {

    @Mock
    private TemplateEngine templateEngine;

    @Mock
    private Client client;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        templateEngine = Mockito.spy(new TemplateEngine());
    }

    @Test
    @Tag("fast")
    void generateMessageTest_positive() throws Exception {
        Template template = new Template();
                template.setMessage("Test Україна with #{subject} and other #{day} for extraction.");
        Map<String, String> replacementMap = new HashMap<>();
        replacementMap.put("#{subject}", "Vacation");
        replacementMap.put("#{day}", "Monday");
        String message = templateEngine.generateMessage(template, replacementMap);
        assertEquals("Test Україна with Vacation and other Monday for extraction.", message);
    }

    @Test
    @Tag("fast")
    void generateMessageTestThrowsException_positive() throws Exception {
        Template template = new Template();
        template.setMessage("Test template with #{subject} and other #{day} for #{error} extraction.");
        Map<String, String> replacementMap = new HashMap<>();
        replacementMap.put("#{subject}", "Vacation");
        replacementMap.put("#{day}", "Monday");
        Exception thrown = assertThrows(
                Exception.class, () -> {String message = templateEngine.generateMessage(template, replacementMap);}
        );
        assertTrue(thrown.getMessage().contentEquals("Exception - missed placeholder."));
    }

}
