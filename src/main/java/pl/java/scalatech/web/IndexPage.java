package pl.java.scalatech.web;

import java.time.LocalDate;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Sławomir Borowiec 
 * Module name : spring4WithoutXml
 * Creating time :  31 lip 2014 17:55:50
 
 */
@Controller
public class IndexPage {

    @RequestMapping("/")
    String info(Model map) {
        
        map.asMap().put("currentTime", LocalDate.now());
        map.asMap().put("greeting", "Hi :)");
        return "info";
    }


}
