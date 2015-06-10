package pl.java.scalatech.test.logger;

import lombok.extern.slf4j.Slf4j;

import org.junit.Test;
import org.slf4j.MDC;

@Slf4j
public class LogMdcTest {
    @Test
    public void shouldMdcWork() {
        // You can put values in the MDC at any time. Before anything else
        // we put the first name
        MDC.put("first", "Dorothy");

        // We now put the last name
        MDC.put("last", "Parker");

        // The most beautiful two words in the English language according
        // to Dorothy Parker:
        log.info("Check enclosed.");
        log.info("The most beautiful two words in English.");

        MDC.put("first", "Richard");
        MDC.put("last", "Nixon");
        log.info("I am not a crook.");
        log.info("Attributed to the former US president. 17 Nov 1973.");
    }

}
