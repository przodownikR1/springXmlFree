package pl.java.scalatech.test.bootstrap;

import lombok.extern.slf4j.Slf4j;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.java.scalatech.config.DsConfig;

/**
 * @author Sławomir Borowiec 
 * Module name : spring4WithoutXml
 * Creating time :  31 lip 2014 17:56:39
 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DsConfig.class})
@Slf4j
public class DaoBootstrapTest {
    @Test
    public void shouldRepositoryBootstrapWork(){
        
    }
    
}
