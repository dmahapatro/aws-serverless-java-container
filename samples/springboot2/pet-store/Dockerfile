FROM amazonlinux:2

RUN yum -y update \
    && yum install -y tar unzip gzip bzip2-devel ed gcc gcc-c++ gcc-gfortran \
    less libcurl-devel openssl openssl-devel readline-devel xz-devel \
    zlib-devel glibc-static libcxx libcxx-devel llvm-toolset-7 zlib-static \
    && rm -rf /var/cache/yum

ENV GRAAL_VERSION 22.1.0
ENV GRAAL_FOLDERNAME graalvm-ce-java17-${GRAAL_VERSION}
ENV GRAAL_FILENAME graalvm-ce-java17-linux-amd64-${GRAAL_VERSION}.tar.gz
RUN curl -4 -L https://github.com/graalvm/graalvm-ce-builds/releases/download/vm-${GRAAL_VERSION}/${GRAAL_FILENAME} | tar -xvz
RUN mv $GRAAL_FOLDERNAME /usr/lib/graalvm
RUN rm -rf $GRAAL_FOLDERNAME

# Graal maven plugin requires Maven 3.3.x
ENV MVN_VERSION 3.6.3
ENV MVN_FOLDERNAME apache-maven-${MVN_VERSION}
ENV MVN_FILENAME apache-maven-${MVN_VERSION}-bin.tar.gz
RUN curl -4 -L https://mirrors.ukfast.co.uk/sites/ftp.apache.org/maven/maven-3/${MVN_VERSION}/binaries/${MVN_FILENAME} | tar -xvz
RUN mv $MVN_FOLDERNAME /usr/lib/maven
RUN rm -rf $MVN_FOLDERNAME
RUN echo \
        "<settings xmlns='http://maven.apache.org/SETTINGS/1.0.0\' \
        xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance' \
        xsi:schemaLocation='http://maven.apache.org/SETTINGS/1.0.0 https://maven.apache.org/xsd/settings-1.0.0.xsd'> \
            <localRepository>${user.home}/.m2/repository</localRepository> \
            <interactiveMode>true</interactiveMode> \
            <usePluginRegistry>false</usePluginRegistry> \
            <offline>false</offline> \
        </settings>" \
        > /usr/share/maven/conf/settings.xml; \
        mkdir /root/.m2/; \
        echo \
        "<settings xmlns='http://maven.apache.org/SETTINGS/1.0.0\' \
        xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance' \
        xsi:schemaLocation='http://maven.apache.org/SETTINGS/1.0.0 https://maven.apache.org/xsd/settings-1.0.0.xsd'> \
            <localRepository>/root/.m2/repository</localRepository> \
            <interactiveMode>true</interactiveMode> \
            <usePluginRegistry>false</usePluginRegistry> \
            <offline>false</offline> \
        </settings>" \
        > /root/.m2/settings.xml

# Gradle
VOLUME /project
WORKDIR /project

RUN /usr/lib/graalvm/bin/gu install native-image
RUN ln -s /usr/lib/graalvm/bin/native-image /usr/bin/native-image
RUN ln -s /usr/lib/maven/bin/mvn /usr/bin/mvn

ENV JAVA_HOME /usr/lib/graalvm

ENTRYPOINT ["sh"]