package pl.java.scalatech.test;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.java.scalatech.config.SecurityBasicConfig;
import pl.java.scalatech.config.ServiceConfig;
import pl.java.scalatech.service.invoice.InvoiceService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { SecurityBasicConfig.class, ServiceConfig.class })
public class AuditTest {
    @Autowired
    private InvoiceService invoiceService;
    @Autowired
    private ApplicationContext context;
    
    private Authentication authentication;
   

    @Before
    public void init() {
        AuthenticationManager authenticationManager = this.context.getBean(AuthenticationManager.class);
        this.authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken("przodownik", "test"));
        System.err.println("===========");
    }

    @Test
    public void shouldRoleSetWork() {

        assertThat(authentication.getAuthorities()).hasSize(1);
        assertThat(authentication.getName()).isEqualTo("przodownik");
        assertThat(authentication.isAuthenticated()).isTrue();

    }

    @After
    public void close() {
        SecurityContextHolder.clearContext();
    }
}