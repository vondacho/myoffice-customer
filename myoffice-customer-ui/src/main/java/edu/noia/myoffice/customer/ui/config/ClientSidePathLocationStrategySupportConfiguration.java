package edu.noia.myoffice.customer.ui.config;

import org.springframework.boot.autoconfigure.web.ErrorViewResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;

//@Configuration
public class ClientSidePathLocationStrategySupportConfiguration {

  /*
   * Collaboration behaviour for Angular PathLocationStrategy
   * https://codecraft.tv/courses/angular/routing/routing-strategies/
   */
  @Bean
  ErrorViewResolver supportPathLocationStrategy() {
    return (request, status, model) -> status == HttpStatus.NOT_FOUND
      ? new ModelAndView("index.html", Collections.emptyMap(), HttpStatus.OK)
      : null;
  }

}
