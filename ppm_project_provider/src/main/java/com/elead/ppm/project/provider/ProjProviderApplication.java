package com.elead.ppm.project.provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * Spring-boot 启动入口
 *
 * @author wangxz
 */
@RestController
@ServletComponentScan
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class } )
@EnableTransactionManagement //启用事务
@ImportResource("classpath:dubbo-provider.xml")
public class ProjProviderApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjProviderApplication.class);

    @RequestMapping
    public String hello() {
        return "Hello World!";
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws IOException the io exception
     */
    public static void main(String[] args) throws IOException {
        SpringApplication application = new SpringApplication(ProjProviderApplication.class);
        application.setRegisterShutdownHook(false);
        application.setBannerMode(Banner.Mode.OFF);
        application.run(args);
        LOGGER.info("ppm_project_provider started!!!");
    }
}
