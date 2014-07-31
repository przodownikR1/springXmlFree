package pl.java.scalatech.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Sławomir Borowiec 
 * Module name : spring4WithoutXml
 * Creating time :  31 lip 2014 17:55:50
 
 */
@Controller
public class IndexPage {

    @RequestMapping("/")
    String info() {
        return "info";
    }


}
