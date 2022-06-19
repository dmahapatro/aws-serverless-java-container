package com.amazonaws.serverless.sample.springboot2;

import com.amazonaws.serverless.proxy.spring.embedded.ServerlessServletEmbeddedServerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({ ServerlessServletEmbeddedServerFactory.class })
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}