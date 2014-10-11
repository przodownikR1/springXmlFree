package pl.java.scalatech.web;

import java.time.LocalDate;
import java.util.Locale;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pl.java.scalatech.service.invoice.InvoiceService;

/**
 * @author Sławomir Borowiec 
 * Module name : spring4WithoutXml
 * Creating time :  31 lip 2014 17:55:45
 
 */
@RestController
@Slf4j
public class HelloController {
    public static final String RESPONSE_BODY = "Spring xml free";
    @Autowired
    private InvoiceService InvoiceService;
    
    @Autowired
    private MessageSource messageSource;

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
    
    @RequestMapping(value = "/message/{text:.+}", method = RequestMethod.GET)
    public ResponseEntity<String> getMessage(@PathVariable("text") String message, Locale locale) {
        log.info("+++  {} ", message);
        return new ResponseEntity<>(messageSource.getMessage(message, new Object[] {}, locale), HttpStatus.OK);
    }

    @RequestMapping(value = "/message/1/{text}", method = RequestMethod.GET)
    public ResponseEntity<String> getMessage1(@PathVariable("text") String message, Locale locale) {
        log.info("+++  {} ", message);
        return new ResponseEntity<>(messageSource.getMessage(message, new Object[] {}, locale), HttpStatus.OK);
    }

    @RequestMapping(value = "/message/2/{text}", method = RequestMethod.GET)
    public ResponseEntity<String> getMessage2(Locale locale) {

        return new ResponseEntity<>(messageSource.getMessage("hello.txt", new Object[] {}, locale), HttpStatus.OK);
    }
    
    @AllArgsConstructor
    @NoArgsConstructor
    @XmlRootElement
    public static class SimpleModel {
        @Getter
        @Setter
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
