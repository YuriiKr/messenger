package com.epam.ld.module2.testing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class MailServerTest {

    @Mock
    private MailServer mailServer;



    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mailServer = Mockito.spy(new MailServer());

    }

    @Test
    void sendMailTest_positive() {
        String message = "message";
        String address = "address";
        doNothing().when(mailServer).send(any(), any());
        mailServer.send(address, message);
        verify(mailServer, times(1)).send(any(), any());
    }
}
