package pl.java.scalatech.config;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = "pl.java.scalatech.aop", useDefaultFilters = false, includeFilters = { @Filter(Aspect.class) })
public class AOPConfig {

}
