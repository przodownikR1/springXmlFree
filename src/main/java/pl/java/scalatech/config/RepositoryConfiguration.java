package pl.java.scalatech.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

@Configuration

@EnableJpaRepositories(basePackages="pl.java.scalatech.repository")
@EnableJpaAuditing(auditorAwareRef="springSecurityAuditorAware")
@ComponentScan(basePackages="pl.java.scalatech.audit",useDefaultFilters=false,includeFilters={@Filter(Component.class)})
public class RepositoryConfiguration {

  @Bean InvoiceEventHandler invoiceEventHandler() {
    return new InvoiceEventHandler();
  }

}