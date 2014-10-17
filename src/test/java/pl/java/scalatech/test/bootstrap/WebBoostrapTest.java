package pl.java.scalatech.test.bootstrap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import javax.servlet.http.HttpSession;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.web.context.WebApplicationContext;

import pl.java.scalatech.config.MvcConfig;
import pl.java.scalatech.config.ServiceConfig;
/**
 * @author Sławomir Borowiec 
 * Module name : spring4WithoutXml
 * Creating time :  31 lip 2014 17:56:44
 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ServiceConfig.class,MvcConfig.class })
@WebAppConfiguration
public class WebBoostrapTest {
    private static String SEC_CONTEXT_ATTR = HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;
    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private FilterChainProxy springSecurityFilterChain;
    
    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = webAppContextSetup(this.wac).addFilters(this.springSecurityFilterChain).build();
    }

    @Test
    public void thatStatusIsOk() throws Exception {
        mockMvc.perform(get("/hello")).andDo(print());
        //.andExpect(status().is4xxClientError()).andExpect(content().string(containsString(HelloController.RESPONSE_BODY)));
    }
    @Test
    public void userAuthenticates() throws Exception {
    final String username = "przodownik";
    ResultMatcher matcher = new ResultMatcher() {
    public void match(MvcResult mvcResult) throws Exception {
    HttpSession session = mvcResult.getRequest().getSession();
    SecurityContext securityContext = (SecurityContext) session.getAttribute(SEC_CONTEXT_ATTR);
    Assert.assertEquals(securityContext.getAuthentication().getName(), username);
    }
    };
    mockMvc.perform(post("/welcome").param("username", username).param("password", "test"))
    /*.andExpect(redirectedUrl("/welcome"))*/
    .andExpect(matcher);
    }
}
