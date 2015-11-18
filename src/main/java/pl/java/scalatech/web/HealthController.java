package pl.java.scalatech.web;

import static java.util.stream.Collectors.toList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

import lombok.extern.slf4j.Slf4j;


@RestController
@Slf4j
public class HealthController {

    @Autowired
    private ApplicationContext applicationContext;

    @RequestMapping(value = "/api/appContext", method = RequestMethod.GET)
    public ResponseEntity<String> appContext() {
        List<String> names = Lists.newArrayList(applicationContext.getBeanDefinitionNames());
        List<String> result = names.stream().filter(t->t.contains(".")).map(t -> t.substring(t.lastIndexOf(".")+1, t.length())).collect(toList());
        result.sort((String s1, String s2) -> s1.compareTo(s2));
        String appContext = Joiner.on("<br/>").join(result);
        log.info("beans : {}", result);
        return new ResponseEntity<>(appContext, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/ping", method = RequestMethod.GET)
    ResponseEntity<String> ping() {
        return new ResponseEntity<>("pong", HttpStatus.OK);
    }

}
