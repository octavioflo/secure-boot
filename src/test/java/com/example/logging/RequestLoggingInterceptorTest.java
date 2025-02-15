package com.example.logging;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import static org.mockito.Mockito.when;

public class RequestLoggingInterceptorTest {
    // private final Logger log = LoggerFactory.getLogger(RequestLoggingInterceptorTest.class);
    private RequestLoggingInterceptor interceptor;
    private HttpServletRequest request;
    private HttpServletResponse response;

    @BeforeEach
    public void setUp() {
        MDC.clear();
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
        assertEquals("", MDC.get(LoggingField.COOKIE));
        assertEquals("127.0.0.1", MDC.get(LoggingField.DEST));
        assertEquals("8080", MDC.get(LoggingField.DEST_PORT));
        assertEquals("application/json", MDC.get(LoggingField.HTTP_CONTENT_TYPE));
        assertEquals("GET", MDC.get(LoggingField.HTTP_METHOD));
        assertEquals("http://example.com", MDC.get(LoggingField.HTTP_REFERRER));
        assertEquals("Mozilla/5.0", MDC.get(LoggingField.HTTP_USER_AGENT));
        assertEquals("127.0.0.1", MDC.get(LoggingField.SRC));
    }
}
