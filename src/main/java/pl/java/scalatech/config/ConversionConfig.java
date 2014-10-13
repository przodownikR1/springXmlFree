package pl.java.scalatech.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.ConverterRegistry;
import org.springframework.core.convert.support.ConversionServiceFactory;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;

import pl.java.scalatech.conversion.Invoice2String;

import com.google.common.collect.Sets;

@Configuration
public class ConversionConfig {
    @Bean
    public ConversionService conversionService() {
        final ConversionServiceFactoryBean conversionServiceFactoryBean = conversionServiceFactoryBean();
        final ConversionService conversionService = conversionServiceFactoryBean.getObject();
        ConverterRegistry converterRegistry = (ConverterRegistry) conversionService;
        ConversionServiceFactory.registerConverters(Sets.newHashSet(new Invoice2String()), converterRegistry);
        return conversionService;
    }

    @Bean
    protected ConversionServiceFactoryBean conversionServiceFactoryBean() {
        return new ConversionServiceFactoryBean();
    }

    @Bean
    protected FormattingConversionServiceFactoryBean formattingConversionServiceFactoryBean() {
        return new FormattingConversionServiceFactoryBean();
    }

    @Bean
    public FormattingConversionService formattingConversionService() {
        final FormattingConversionService formattingConversionService = formattingConversionServiceFactoryBean().getObject();
        return formattingConversionService;
    }
    
   /* @Bean
    public FormattingConversionService conversionService2() {
        DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService(false);

        conversionService.addFormatterForFieldAnnotation(new NumberFormatAnnotationFormatterFactory());

        DateFormatterRegistrar registrar = new DateFormatterRegistrar();
        registrar.setFormatter(new DateFormatter("yyyy-MM-dd"));
        registrar.registerFormatters(conversionService);
        return conversionService;
    }*/
}