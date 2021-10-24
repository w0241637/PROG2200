package hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Random;

/**
 *
 * Spring01 - https://spring.io/guides/gs/rest-service/
 *
 * This main line starts the Spring framework.  It appears to call nothing, but
 * by starting up the framework, the annotations in the other classes force those
 * objects to be instantiated by the framework.
 *
 * Spring Docs: https://docs.spring.io/spring/docs/4.3.12.RELEASE/spring-framework-reference/htmlsingle/
 * Spring Boot Docs: https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/
 *
 * Spring boot makes Spring easier to use.
 *
 * See https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#using-boot-using-springbootapplication-annotation
 *
 * So...
 * The tomcat server is started, and listening per "application properties" file
 * The @RestController is started up and listening for when URL @RequestMapping("/whatever") is hit
 * ...other annotations can start up controllers, ....
 *
 * Handy Spring annotation reference: https://javabeat.net/spring-annotations/
 *
 */
@SpringBootApplication
public class Application {

//    public static int int_random;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}