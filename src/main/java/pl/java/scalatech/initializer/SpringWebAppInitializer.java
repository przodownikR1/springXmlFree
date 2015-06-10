package pl.java.scalatech.initializer;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.FilterRegistration.Dynamic;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import lombok.extern.slf4j.Slf4j;

import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.filter.ShallowEtagHeaderFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import pl.java.scalatech.config.AppConfig;
import pl.java.scalatech.config.MvcConfig;
import ch.qos.logback.classic.helpers.MDCInsertingServletFilter;

@Slf4j
public class SpringWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        log.info("+++                    getRootConfigClasses");
        return new Class<?>[] { AppConfig.class };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        log.info("+++                    getServletConfigClasses");
        return new Class<?>[] { MvcConfig.class };
    }

    @Override
    protected String[] getServletMappings() {
        log.info("+++                    getServletMappings");
        return new String[] { "/" };
    }

    @Override
    protected void registerContextLoaderListener(ServletContext servletContext) {
        log.info("()()()()                        registerContextLoaderListener");
        super.registerContextLoaderListener(servletContext);

    }

    private static final String CONFIG_LOCATION = "pl.java.scalatech.config";
    private static final String MAPPING_URL = "/*";

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        log.info("+++                                              onStartup!!!");
        WebApplicationContext context = getContext();
        servletContext.addListener(new ContextLoaderListener(context));
        servletContext.setInitParameter("defaultHtmlEscape", "true");

        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("DispatcherServlet", new DispatcherServlet(context));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping(MAPPING_URL);
        securityFilter(servletContext);
        encodingFilter(servletContext);
        registerShallowEtagHeaderFilter(servletContext);
        registerHiddenHttpMethodFilter(servletContext);
        mdcFilter(servletContext);

    }

    private void securityFilter(ServletContext servletContext) {
        servletContext.addListener(new HttpSessionEventPublisher());
        DelegatingFilterProxy delegatingFilterProxy = new DelegatingFilterProxy();
        Dynamic filter = servletContext.addFilter("springSecurityFilterChain", delegatingFilterProxy);
        filter.addMappingForUrlPatterns(null, true, "/*");
        log.info("+++                        register security filter");
    }

    private AnnotationConfigWebApplicationContext getContext() {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.setConfigLocation(CONFIG_LOCATION);
        return context;
    }

    private void encodingFilter(ServletContext servletContext) {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        FilterRegistration.Dynamic fr = servletContext.addFilter("encodingFilter", characterEncodingFilter);
        fr.addMappingForUrlPatterns(null, true, "/*");
    }

    private FilterRegistration.Dynamic registerShallowEtagHeaderFilter(ServletContext servletContext) {
        FilterRegistration.Dynamic shallowEtagHeaderFilter = servletContext.addFilter("shallowEtagHeaderFilter", new ShallowEtagHeaderFilter());
        shallowEtagHeaderFilter.addMappingForServletNames(EnumSet.allOf(DispatcherType.class), true, "dispatcher");
        return shallowEtagHeaderFilter;
    }

    private FilterRegistration.Dynamic mdcFilter(ServletContext context) {
        FilterRegistration.Dynamic mdcFilter = context.addFilter("mdc", new MDCInsertingServletFilter());
        mdcFilter.addMappingForUrlPatterns(null, false, "/*");
        return mdcFilter;
    }

    private FilterRegistration.Dynamic registerHiddenHttpMethodFilter(ServletContext servletContext) {
        FilterRegistration.Dynamic hiddenHttpMethodFilter = servletContext.addFilter("hiddenHttpMethodFilter", new HiddenHttpMethodFilter());
        hiddenHttpMethodFilter.addMappingForServletNames(EnumSet.allOf(DispatcherType.class), true, "dispatcher");
        return hiddenHttpMethodFilter;
    }

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        registration.setInitParameter("defaultHtmlEscape", "true");
        registration.setInitParameter("spring.profiles.active", "default");
    }
}