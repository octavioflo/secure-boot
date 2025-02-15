package com.example.logging;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.MDC;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import static org.mockito.Mockito.when;


public class RequestLoggingInterceptorTest {
    private RequestLoggingInterceptor interceptor;
    private HttpServletRequest request;
    private HttpServletResponse response;

    @BeforeEach
    public void setUp() {
        interceptor = new RequestLoggingInterceptor();
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
    }

    @Test
    public void testPreHandle() throws Exception {
        when(request.getCookies()).thenReturn(null);
        when(request.getRemoteAddr()).thenReturn("127.0.0.1");
        when(request.getRemotePort()).thenReturn(8080);
        when(request.getContentType()).thenReturn("application/json");
        when(request.getMethod()).thenReturn("GET");
        when(request.getHeader("referer")).thenReturn("http://example.com");
        when(request.getHeader("User-Agent")).thenReturn("Mozilla/5.0");

        boolean result = interceptor.preHandle(request, response, new Object());

        assertTrue(result);
        assertTrue(MDC.get(LoggingField.COOKIE).isEmpty());
        assertTrue(MDC.get(LoggingField.DEST).equals("127.0.0.1"));
        assertTrue(MDC.get(LoggingField.DEST_PORT).equals("8080"));
        assertTrue(MDC.get(LoggingField.HTTP_CONTENT_TYPE).equals("application/json"));
        assertTrue(MDC.get(LoggingField.HTTP_METHOD).equals("GET"));
        assertTrue(MDC.get(LoggingField.HTTP_REFERRER).equals("http://example.com"));
        assertTrue(MDC.get(LoggingField.HTTP_USER_AGENT).equals("Mozilla/5.0"));
        assertTrue(MDC.get(LoggingField.SRC).equals("127.0.0.1"));
    }
}
