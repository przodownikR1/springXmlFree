package pl.java.scalatech.config;

import lombok.extern.slf4j.Slf4j;

import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;

import pl.java.scalatech.domain.Invoice;

@RepositoryEventHandler
@Slf4j
public class InvoiceEventHandler {

  @HandleBeforeSave(Invoice.class) public void handleInvoiceSave(Invoice p) {
    log.info("==========    {} " ,p  );
  }

  

}