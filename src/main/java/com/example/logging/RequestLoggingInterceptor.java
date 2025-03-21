package com.example.logging;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class RequestLoggingInterceptor implements HandlerInterceptor {

  private static final Logger log = LoggerFactory.getLogger(RequestLoggingInterceptor.class);
  private final String REFERER = "referer";
  private final String HTTP_USER_AGENT = "User-Agent";
  private final String COMMA = ",";
  private final String COLON = ":";

  @Override
  public boolean preHandle(
      HttpServletRequest request, HttpServletResponse response, Object handler) {
    Cookie[] cookies = request.getCookies();
    StringBuilder cookiesSb = new StringBuilder();
    // MDC.put(LoggingField.APP, "");

    if (cookies != null) {
      int index = 0;
      int cookiesLength = cookies.length;
      for (Cookie cookie : request.getCookies()) {

        cookiesSb.append(cookie.getName()).append(COLON).append(cookie.getValue());
        if (index == cookiesLength) {
          break;
        }
        cookiesSb.append(COMMA);
        index++;
      }
    }
    MDC.put(LoggingField.COOKIE, cookiesSb.toString());
    MDC.put(LoggingField.DEST, request.getRemoteAddr());
    MDC.put(LoggingField.DEST_PORT, String.valueOf(request.getRemotePort()));
    MDC.put(LoggingField.HTTP_CONTENT_TYPE, request.getContentType());
    MDC.put(LoggingField.HTTP_METHOD, request.getMethod());
    MDC.put(LoggingField.HTTP_REFERRER, request.getHeader(REFERER));
    MDC.put(LoggingField.HTTP_USER_AGENT, request.getHeader(HTTP_USER_AGENT));
    MDC.put(LoggingField.SRC, request.getRemoteAddr());
    return true;
  }

  @Override
  public void afterCompletion(
      HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
    MDC.clear();
  }
}
