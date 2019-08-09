package kz.kasya.bitlab.RXCourse;

import kz.kasya.bitlab.RXCourse.modules.file.configurations.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.Ordered;
import org.springframework.web.filter.CharacterEncodingFilter;

@SpringBootApplication
@ComponentScan("kz.kasya.bitlab.RXCourse")
@EnableConfigurationProperties({
        FileStorageProperties.class
})
public class RXCourseApplication
        extends SpringBootServletInitializer {


    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(RXCourseApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(RXCourseApplication.class, args);
    }

}
