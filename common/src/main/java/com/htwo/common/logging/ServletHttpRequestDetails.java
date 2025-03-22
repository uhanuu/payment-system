package com.htwo.common.logging;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import lombok.NoArgsConstructor;
import org.springframework.web.util.ContentCachingRequestWrapper;

@NoArgsConstructor
public final class ServletHttpRequestDetails {

  //HTTP 헤더 필드는 HTTP 프록시 또는 로드 밸런서를 통해 웹 서버 에 연결하는 클라이언트의 원래 IP 주소를 식별하는 일반적인 방법입니다 .
  private static final String X_FORWARDED_FOR_HEADER_NAME = "X-FORWARDED-FOR";
  private static final String DEFAULT_CHARACTERS_ENCODING = "UTF-8";
  private String requestId;
  private LocalDateTime requestStartTime;
  private String httpMethod;
  private String requestUri;
  private String clientIp;
  private Map<String, String> headers;
  private Map<String, String> requestParam;
  private String requestBody;

  private ServletHttpRequestDetails(
      String requestId,
      LocalDateTime requestStartTime,
      ContentCachingRequestWrapper requestWrapper
  ) {
    this.requestId = requestId;
    this.requestStartTime = requestStartTime;
    this.httpMethod = requestWrapper.getMethod();
    this.requestUri = requestWrapper.getRequestURI();
    this.clientIp = Optional.ofNullable(
            requestWrapper.getHeader(X_FORWARDED_FOR_HEADER_NAME))
        .orElse(requestWrapper.getRemoteAddr());

    this.headers = getHeaders(requestWrapper);
    this.requestParam = getParameters(requestWrapper);
    this.requestBody = getRequestBody(requestWrapper);
  }

  private Map<String, String> getHeaders(ContentCachingRequestWrapper request) {
    Map<String, String> headerMap = new HashMap<>();
    Enumeration<String> headerNames = request.getHeaderNames();
    while (headerNames.hasMoreElements()) {
      String headerName = headerNames.nextElement();
      headerMap.put(headerName, request.getHeader(headerName));
    }
    return headerMap;
  }

  private Map<String, String> getParameters(ContentCachingRequestWrapper request) {
    Map<String, String> parameterMap = new HashMap<>();
    Enumeration<String> parameterNames = request.getParameterNames();
    while (parameterNames.hasMoreElements()) {
      String headerName = parameterNames.nextElement();
      parameterMap.put(headerName, request.getParameter(headerName));
    }
    return parameterMap;
  }

  private String getRequestBody(ContentCachingRequestWrapper request) {
    byte[] contentAsByteArray = request.getContentAsByteArray();
    if (contentAsByteArray.length > 0) {
      try {
        return new String(contentAsByteArray, request.getCharacterEncoding());
      } catch (UnsupportedEncodingException e) {
        return "{}";
      }
    }
    return "{}";
  }

  public static String getRequestDetailsLogMessage(
      String requestId,
      LocalDateTime requestStartTime,
      ContentCachingRequestWrapper requestWrapper
  )
      throws UnsupportedEncodingException {
    requestWrapper.setCharacterEncoding(DEFAULT_CHARACTERS_ENCODING);
    return new ServletHttpRequestDetails(requestId, requestStartTime, requestWrapper).toPrettierLog();
  }

  public String toPrettierLog() {
    String logFormat = """
        [%s] %s %s
        >> REQUEST_START_TIME: %s
        >> CLIENT_IP: %s
        >> HEADERS: %s
        >> REQUEST_PARAM: %s
        >> REQUEST_BODY: %s
        """;
    final String requestTime = this.requestStartTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

    return String.format(logFormat,
        this.requestId,
        this.httpMethod,
        this.requestUri,
        requestTime,
        this.clientIp,
        this.headers,
        this.requestParam,
        this.requestBody
    );
  }
}