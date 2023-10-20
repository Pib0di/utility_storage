package com.thewhite.study;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class EchoTest {

    private final List<String> history = mock(List.class);
    private final Echo echo = new Echo(history);

    @Test
    void echoNull() {
        // Act
        var result = echo.echo(null);

        // Assert
        assertNull(result);
        verify(history).add(null);
    }

    @ParameterizedTest
    @ValueSource(strings = {"text", "long text with spaces", "123", "\t\n"})
    void manyEcho(String text) {
        // Act
        var result = echo.echo(text);

        // Assert
        assertEquals(result, text);
        verify(history).add(text);
    }

}













