package pl.java.scalatech.web;

import java.time.LocalDate;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    @RequestMapping(value = "/json", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public SimpleModel getJson() {
        return new SimpleModel("przodownik", LocalDate.of(2010, 2, 4));
    }
    
    @RequestMapping(value = "/xml", method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE)
    public SimpleModel getXml() {
        return new SimpleModel("przodownik", LocalDate.of(2010, 2, 4));
    }
    
    
    @AllArgsConstructor
    @NoArgsConstructor
    @XmlRootElement
    public static class SimpleModel {
        @Getter
        private String name;
        @Getter
        @XmlJavaTypeAdapter( LocalDateAdapter.class )
        private LocalDate date;
    }
    
    public static class LocalDateAdapter extends XmlAdapter<String, LocalDate> {

        @Override
        public LocalDate unmarshal(String v) throws Exception {
            return LocalDate.parse(v);
        }

        @Override
        public String marshal(LocalDate v) throws Exception {
            return v.toString();
        }

    }
}
