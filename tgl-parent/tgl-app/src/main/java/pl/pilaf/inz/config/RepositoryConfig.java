package pl.pilaf.inz.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "pl.pilaf.inz.repository")
@EnableAutoConfiguration
@EntityScan(basePackages = {"pl.pilaf.inz.model"})
public class RepositoryConfig {
}
