package pl.java.scalatech.web;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Sławomir Borowiec 
 * Module name : spring4WithoutXml
 * Creating time :  31 lip 2014 17:55:45
 
 */
@RestController
public class HelloController {
    public static final String RESPONSE_BODY = "Spring xml free";

    @RequestMapping(value = "/hello", produces = MediaType.TEXT_PLAIN_VALUE)
    public String sayHello() {
        return RESPONSE_BODY;
    }
}
