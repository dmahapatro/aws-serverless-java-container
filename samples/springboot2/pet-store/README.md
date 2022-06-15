### Reproduce issue Faced with Springboot2 and spring-native 0.12.0

Java - 17
* GraalVM - 22.1.0
* aws-serverless-java-container-springboot2 - 1.8
* Spring boot - 2.7.0
* Spring Native - 0.12.0

### To run the Build
`docker run --mount type=bind,source=$(pwd),destination=/project -it --entrypoint /bin/bash muthumv/amazonlinux-graalvm-22.1`

**Build**
`mvn -Pnative clean package`

Copy the `function.zip` from target folder and deploy to lambda

* Handler - `com.amazonaws.serverless.sample.springboot2.StreamLambdaHandler`
* Runtime - `Custom runtime on Amazon Linux 2`
* Architecture - `x86_64`

Copy the `function.zip` from target folder

<img width="1292" alt="image" src="https://user-images.githubusercontent.com/33663725/173910104-8f4d5c98-474b-4109-a7bf-813ac86c5c37.png">


Logs Output:

```
 active profile set, falling back to 1 default profile: "default"
2022-06-15 19:38:29.299  WARN 10 --- [           main] w.s.c.ServletWebServerApplicationContext : Exception encountered during context initialization - cancelling refresh attempt: org.springframework.context.ApplicationContextException: Unable to start web server; nested exception is org.springframework.boot.web.context.MissingWebServerFactoryBeanException: No qualifying bean of type 'org.springframework.boot.web.servlet.server.ServletWebServerFactory' available: Unable to start ServletWebServerApplicationContext due to missing ServletWebServerFactory bean
2022-06-15 19:38:29.300 ERROR 10 --- [           main] o.s.b.d.LoggingFailureAnalysisReporter   : 
***************************
APPLICATION FAILED TO START
***************************
Description:
Web application could not be started as there was no org.springframework.boot.web.servlet.server.ServletWebServerFactory bean defined in the context.
Action:
Check your application's dependencies for a supported servlet web server.
Check the configured web application type.
Error loading class com.amazonaws.serverless.sample.springboot2.StreamLambdaHandler: java.lang.ExceptionInInitializerError
java.lang.ExceptionInInitializerError
at java.lang.Class.ensureInitialized(DynamicHub.java:518)
at java.lang.Class.forName(DynamicHub.java:1124)
Caused by: org.springframework.context.ApplicationContextException: Unable to start web server; nested exception is org.springframework.boot.web.context.MissingWebServerFactoryBeanException: No qualifying bean of type 'org.springframework.boot.web.servlet.server.ServletWebServerFactory' available: Unable to start ServletWebServerApplicationContext due to missing ServletWebServerFactory bean
at org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext.onRefresh(ServletWebServerApplicationContext.java:165)
at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:577)
at org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext.refresh(ServletWebServerApplicationContext.java:147)
at org.springframework.boot.SpringApplication.refresh(SpringApplication.java:734)
at org.springframework.boot.SpringApplication.refreshContext(SpringApplication.java:408)
at org.springframework.boot.SpringApplication.run(SpringApplication.java:308)
at org.springframework.boot.builder.SpringApplicationBuilder.run(SpringApplicationBuilder.java:164)
at com.amazonaws.serverless.proxy.spring.SpringBootLambdaContainerHandler.initialize(SpringBootLambdaContainerHandler.java:195)
at com.amazonaws.serverless.proxy.InitializationWrapper.start(InitializationWrapper.java:35)
at com.amazonaws.serverless.proxy.spring.SpringBootProxyHandlerBuilder.buildAndInitialize(SpringBootProxyHandlerBuilder.java:81)
at com.amazonaws.serverless.proxy.spring.SpringBootLambdaContainerHandler.getAwsProxyHandler(SpringBootLambdaContainerHandler.java:93)
at com.amazonaws.serverless.sample.springboot2.StreamLambdaHandler.<clinit>(StreamLambdaHandler.java:21)
... 2 more
Caused by: org.springframework.boot.web.context.MissingWebServerFactoryBeanException: No qualifying bean of type 'org.springframework.boot.web.servlet.server.ServletWebServerFactory' available: Unable to start ServletWebServerApplicationContext due to missing ServletWebServerFactory bean
at org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext.getWebServerFactory(ServletWebServerApplicationContext.java:212)
at org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext.createWebServer(ServletWebServerApplicationContext.java:182)
at org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext.onRefresh(ServletWebServerApplicationContext.java:162)
... 13 more
END RequestId: 66c598b5-4b32-4414-808c-a89ba602b983
REPORT RequestId: 66c598b5-4b32-4414-808c-a89ba602b983	Duration: 385.73 ms	Billed Duration: 386 ms	Memory Size: 128 MB	Max Memory Used: 11 MB	
Unknown application error occurred
java.lang.ExceptionInInitializerError
```
