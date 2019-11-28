package kz.kasya.bitlab.RXCourse;

import kz.kasya.bitlab.RXCourse.modules.file.configurations.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
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
