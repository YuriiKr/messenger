package com.epam.ld.module2.testing;

import com.epam.ld.module2.testing.extension.TestExtension;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.io.TempDir;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.junit.jupiter.api.Test;
import org.mockito.exceptions.base.MockitoException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoException.class)
public class MailServerTest {

    private Path workingDir;

    @Mock
    private MailServer mailServer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mailServer = Mockito.spy(new MailServer());
        this.workingDir = Path.of("", "src/test/resources");
    }

    @TempDir
    Path directory;

    @Test
    @Tag("fast")
    void sendMailTest_positive() {
        String message = "message";
        String address = "address";
        doNothing().when(mailServer).send(any(), any());
        mailServer.send(address, message);
        verify(mailServer, times(1)).send(address, message);
    }

    @ParameterizedTest
//    @ValueSource(strings = {"message"," ", "1", "@"})
    @MethodSource("generateMessages")
    void sendMailTest_ParametrizedPositive(String messages) {
        String address = "address";
        doNothing().when(mailServer).send(any(), any());
        mailServer.send(address, messages);
        verify(mailServer, times(1)).send(address, messages);
    }

    private Stream<Arguments> generateMessages() {
       return Stream.of(
               Arguments.of(null),
               Arguments.of(" "),
               Arguments.of("1"),
               Arguments.of("@"));
    }

    @TestFactory
    Collection<DynamicTest> sendMailTest_DynamicPositive() {

        String message = "message";
        String address = "address";
        return Arrays.asList(
                DynamicTest.dynamicTest("Send email",
                        () -> {
                            doNothing().when(mailServer).send(any(), any());
                            mailServer.send(address, message);
                            verify(mailServer, times(1)).send(address, message);
                        }));

    }

    @Test
    @Tag("fast")
    void sendMailTest_TempDirPositive(@TempDir Path directory) throws IOException {
        Path messagePath = directory.resolve("message.txt");
        List<String> lines = Arrays.asList("message","address");
        Files.write(messagePath, lines);
        assertAll(
                () -> assertTrue(Files.exists(messagePath), "File should exist"),
                () -> assertLinesMatch(lines, Files.readAllLines(messagePath)));
    }

    @Test
    @Tag("fast")
    void sendMailTest_FilePositive() throws IOException {
        String message = "message";
        Path file = this.workingDir.resolve("message.txt");
        String content = Files.readString(file);
        assertEquals(content, message);

    }

    @Test
    @Tag("fast")
    @ExtendWith(TestExtension.class)
    void sendMailTest_WithExtensionPositive() {
        String message = "message";
        String address = "address";
        doNothing().when(mailServer).send(any(), any());
        mailServer.send(address, message);
        verify(mailServer, times(1)).send(address, message);


    }

    @Test
    @Tag("fast")
    void sendMailTest_WithExpectedExceptionPositive() {
        String message = "message";
        String address = "address";
        doNothing().when(mailServer).send(any(), any());
        mailServer.send(address, message);
        verify(mailServer, times(1)).send(address, message);


    }

//    @Rule
//    public ExpectedException thrown = ExpectedException.none();
//
//    @Test
//    public void testDivisionWithException() {
//
//        thrown.expect(ArithmeticException.class);
//        thrown.expectMessage(containsString("/ by zero"));
//
//        int i = 1 / 0;
//
//    }

}
