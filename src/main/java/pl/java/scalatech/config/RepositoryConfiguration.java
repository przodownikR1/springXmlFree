package pl.java.scalatech.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfiguration {

  @Bean InvoiceEventHandler invoiceEventHandler() {
    return new InvoiceEventHandler();
  }

}