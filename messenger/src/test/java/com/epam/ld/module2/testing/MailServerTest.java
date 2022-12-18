package com.epam.ld.module2.testing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MailServerTest {

    private MailServer mailServer;



    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mailServer = new MailServer();
        String message = "message";
        String address = "address";
    }

    @Test
    void sendMailTest_positive() {
        String message = "message";
        String address = "address";
        verify(mailServer).send(address, message);
    }
}
