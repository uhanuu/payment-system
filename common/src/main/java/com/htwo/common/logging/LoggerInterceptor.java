package com.htwo.common.logging;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.jboss.logging.MDC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

@RequiredArgsConstructor
public class LoggerInterceptor implements HandlerInterceptor {

  private static final String REQUEST_ID = "request_id";
  private final LoggingProducer loggingProducer;
  @Value("${spring.application.name}")
  private String applicationName;

  @Override
  public boolean preHandle(
      HttpServletRequest request,
      HttpServletResponse response,
      Object handler
  ) throws Exception {
    final ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);

    final String requestId = CustomUUID.getCustomUUID(8);
    final String requestLogMessage = ServletHttpRequestDetails.getRequestDetailsLogMessage(
        requestId,
        LocalDateTime.now(),
        requestWrapper
    );
    loggingProducer.sendMessage(applicationName, requestLogMessage);
    MDC.put(REQUEST_ID, requestId);
    return HandlerInterceptor.super.preHandle(request, response, handler);
  }

  @Override
  public void postHandle(
      HttpServletRequest request,
      HttpServletResponse response,
      Object handler,
      ModelAndView modelAndView
  ) throws Exception {
    final ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);

    final String responseLogMessage = ServletHttpResponseDetails.getResponseDetailsLogMessage(
        (String) MDC.get(REQUEST_ID),
        LocalDateTime.now(),
        responseWrapper
    );
    loggingProducer.sendMessage(applicationName, responseLogMessage);
    MDC.remove(REQUEST_ID);

    HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
  }
}
