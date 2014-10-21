package pl.java.scalatech.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ro.isdc.wro.http.ConfigurableWroFilter;
import ro.isdc.wro.manager.factory.ConfigurableWroManagerFactory;
import ro.isdc.wro.manager.factory.WroManagerFactory;

//@Configuration
public class WroConfig {
    public WroManagerFactory wroManagerFactory() {
        ConfigurableWroManagerFactory wroManagerFactory = new ConfigurableWroManagerFactory();
        wroManagerFactory.setConfigProperties(new Properties() {
           
            {
                setProperty("debug", "false");
                setProperty("preProcessors", "cssImport,semicolonAppender");
                setProperty("postProcessors", "less4j,yuiCssMin");
                setProperty("cacheGzippedContent", "true");
                setProperty("hashStrategy", "MD5"); // should drive the naming strategy to fingerprint resource urls
                setProperty("namingStrategy", "hashEncoder-CRC32"); // should drive the naming strategy to fingerprint resource urls
            }
        });
        return wroManagerFactory;
    }

    @Bean
    public ConfigurableWroFilter configurableWroFilter() {
        ConfigurableWroFilter configurableWroFilter = new ConfigurableWroFilter();
        configurableWroFilter.setWroManagerFactory(wroManagerFactory());
        return configurableWroFilter;
    }
}

//setProperty("preProcessors", "cssImport,semicolonAppender");
//setProperty("postProcessors", "less4j,cssMinJawr");