package pl.java.scalatech.test.bootstrap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import pl.java.scalatech.config.ServiceConfig;
import pl.java.scalatech.service.invoice.InvoiceService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ServiceConfig.class})
@Transactional
public class GenerotorInvoiceTest {

    @Autowired 
    private InvoiceService invoiceService;
    
    @Test
    public void shouldGeneratorInvoicesWork(){
        this.invoiceService.generateInvoices();
    }
    
}
