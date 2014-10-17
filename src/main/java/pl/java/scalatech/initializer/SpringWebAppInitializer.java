package pl.java.scalatech.initializer;

import java.util.EnumSet;

import javax.servlet.FilterRegistration.Dynamic;
import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

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

public class SpringWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[] { AppConfig.class };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] { MvcConfig.class };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }

    @Override
    protected void registerContextLoaderListener(ServletContext servletContext) {
        super.registerContextLoaderListener(servletContext);
        servletContext.addListener(new HttpSessionEventPublisher());
        DelegatingFilterProxy delegatingFilterProxy = new DelegatingFilterProxy();
        Dynamic filter = servletContext.addFilter("springSecurityFilterChain", delegatingFilterProxy);
        filter.addMappingForUrlPatterns(null, true, "/*");
    }

    private static final String CONFIG_LOCATION = "pl.java.scalatech.config";
    private static final String MAPPING_URL = "/*";

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        WebApplicationContext context = getContext();
        servletContext.addListener(new ContextLoaderListener(context));
        servletContext.setInitParameter("defaultHtmlEscape", "true");

        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("DispatcherServlet", new DispatcherServlet(context));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping(MAPPING_URL);

        encodingFilter(servletContext);
        registerShallowEtagHeaderFilter(servletContext);
        registerHiddenHttpMethodFilter(servletContext);

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

    private FilterRegistration.Dynamic registerHiddenHttpMethodFilter(ServletContext servletContext) {
        FilterRegistration.Dynamic hiddenHttpMethodFilter = servletContext.addFilter("hiddenHttpMethodFilter", new HiddenHttpMethodFilter());
        hiddenHttpMethodFilter.addMappingForServletNames(EnumSet.allOf(DispatcherType.class), true, "dispatcher");
        return hiddenHttpMethodFilter;
    }
}