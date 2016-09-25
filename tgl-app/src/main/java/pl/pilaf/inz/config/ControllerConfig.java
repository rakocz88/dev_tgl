package pl.pilaf.inz.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = {"pl.pilaf.inz.controller", "pl.pilaf.inz.biz"})
public class ControllerConfig {
}
