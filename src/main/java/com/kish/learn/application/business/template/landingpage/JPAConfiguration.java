package com.kish.learn.application.business.template.landingpage;

import io.hypersistence.utils.spring.repository.BaseJpaRepositoryImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(
        value = "com.kish.learn.application.business.template",
        repositoryBaseClass = BaseJpaRepositoryImpl.class,
        basePackages = {
                "io.hypersistence.utils.spring.repository",
        }
)
public class JPAConfiguration {
}
