### Reproduce issue Faced with Springboot2 and spring-native 0.10.0

Java - 17
GraalVM - 22.1.0
aws-serverless-java-container-springboot2 - 1.8
Spring boot - 2.7.0
Spring Native - 0.12.0

### To run the Build
`docker run --mount type=bind,source=$(pwd),destination=/project -it --entrypoint /bin/bash muthumv/amazonlinux-graalvm-22.1`

**Build**
`mvn -Pnative clean package`

Copy the `function.zip` from target folder and deploy to lambda

* Handler - `com.amazonaws.serverless.sample.springboot2.StreamLambdaHandler`
* Runtime - `Custom runtime on Amazon Linux 2`
* Architecture - `x86_64`