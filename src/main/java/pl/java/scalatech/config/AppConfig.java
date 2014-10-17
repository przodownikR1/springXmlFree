package pl.java.scalatech.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
@Configuration
@Import(value={DsConfig.class,EncryptConfig.class,RepositoryConfiguration.class,ServiceConfig.class})
public class AppConfig {

}
