package pl.pilaf.inz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import pl.pilaf.inz.config.AppConfig;
import pl.pilaf.inz.config.ControllerConfig;
import pl.pilaf.inz.config.RepositoryConfig;
import pl.pilaf.inz.server.ServletContainerCustomizer;

@Configuration
@EnableAutoConfiguration
@PropertySource("classpath:application.properties")
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(new Object[] { AppConfig.class, ServletContainerCustomizer.class, ControllerConfig.class,
				RepositoryConfig.class }, args);
	}
}
