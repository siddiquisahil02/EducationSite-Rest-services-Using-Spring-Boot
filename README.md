# EducationSite-Rest-services-Using-Spring-Boot

![](https://img.shields.io/badge/Sahil-Siddiqui-red)
![](https://img.shields.io/github/languages/top/siddiquisahil02/EducationSite-Rest-services-Using-Spring-Boot)
![](https://img.shields.io/github/last-commit/siddiquisahil02/EducationSite-Rest-services-Using-Spring-Boot)

Minimal [Spring Boot](http://projects.spring.io/spring-boot/) sample app.


## API Endpoints

[Click here](https://documenter.getpostman.com/view/16026763/UVysxFcL) to get all the API Endpoints.
## Requirements

For building and running the application you need:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)

## Packages used

This application uses the following packages from [mvnrepository.com](https://mvnrepository.com/)

- [spring-boot-starter-web](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-web)
- [spring-boot-starter-data-jpa](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-data-jpa)
- [spring-boot-starter-security](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-security)
- [spring-boot-starter-validation](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-validation)
- [mysql-connector-java](https://mvnrepository.com/artifact/mysql/mysql-connector-java)
- [spring-boot-devtools](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-devtools)
- [spring-boot-starter-test](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-test)

### Storage Used
This application uses [imagekit.io](https://imagekit.io/) as it's storage and CDN, but maximum upload size is 25 MB only.

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `src.main.java.com.camp.educationalsite.EducationalsiteApplication` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```
