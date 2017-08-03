package com.elead.ppm.project.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type  consumer application.
 *
 * @author wangxz
 * @date 2017/3/22
 */
@RestController
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
@ImportResource("classpath:dubbo-consumer.xml")
public class ProjConsumerApplication {
    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ProjConsumerApplication.class);

    /**
     * Hello string.
     *
     * @return the string
     */
    @RequestMapping("/hello")
    public String hello() {
        return "Hello World!";
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(ProjConsumerApplication.class);
        application.setBannerMode(Banner.Mode.OFF);
        application.run(args);
        LOGGER.info("ppm_project_consumer started!!!");
    }
}
