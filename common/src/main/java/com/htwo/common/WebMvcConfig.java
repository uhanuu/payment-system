package com.htwo.common;

import com.htwo.common.logging.LoggerInterceptor;
import com.htwo.common.logging.LoggingProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {

  private final LoggingProducer loggingProducer;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new LoggerInterceptor(loggingProducer))
        .excludePathPatterns("/css/**", "/images/**", "/js/**");
  }
}
