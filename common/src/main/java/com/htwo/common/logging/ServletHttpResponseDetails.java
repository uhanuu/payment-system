package com.htwo.common.logging;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.util.ContentCachingResponseWrapper;

@NoArgsConstructor
public final class ServletHttpResponseDetails {

  //HTTP 헤더 필드는 HTTP 프록시 또는 로드 밸런서를 통해 웹 서버 에 연결하는 클라이언트의 원래 IP 주소를 식별하는 일반적인 방법입니다 .
  private static final String X_FORWARDED_FOR_HEADER_NAME = "X-FORWARDED-FOR";
  private static final String DEFAULT_CHARACTERS_ENCODING = "UTF-8";
  private String requestId;
  private LocalDateTime requestEndTime;
  private HttpStatus httpStatus;
  private String responseBody;

  private ServletHttpResponseDetails(
      String requestId,
      LocalDateTime requestEndTime,
      ContentCachingResponseWrapper responseWrapper
  ) throws IOException {
    this.requestId = requestId;
    this.requestEndTime = requestEndTime;
    this.httpStatus = HttpStatus.valueOf(responseWrapper.getStatus());
    this.responseBody = getResponseBody(responseWrapper);
  }

  private String getResponseBody(ContentCachingResponseWrapper response) throws IOException {
    String payload = null;
    byte[] contentAsByteArray = response.getContentAsByteArray();
    if (contentAsByteArray.length > 0) {
      payload = new String(contentAsByteArray, response.getCharacterEncoding());
      response.copyBodyToResponse();
    }
    return payload != null ? payload : "{}";
  }

  public static String getResponseDetailsLogMessage(
      String requestId,
      LocalDateTime requestEndTime,
      ContentCachingResponseWrapper responseWrapper
  ) throws IOException {
    responseWrapper.setCharacterEncoding(DEFAULT_CHARACTERS_ENCODING);
    return new ServletHttpResponseDetails(requestId, requestEndTime, responseWrapper).toPrettierLog();
  }

  public String toPrettierLog() {
    String logFormat = """
        [%s] REQUEST_END_TIME: %s
        >> HTTP_STATUS: %s
        >> RESPONSE_BODY: %s"
        """;
    final String requestEndTime = this.requestEndTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

    return String.format(logFormat,
        this.requestId,
        requestEndTime,
        this.httpStatus,
        this.responseBody
    );
  }
}