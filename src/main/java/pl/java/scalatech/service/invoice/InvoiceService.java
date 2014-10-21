package pl.java.scalatech.service.invoice;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import pl.java.scalatech.domain.Invoice;

/**
 * @author Sławomir Borowiec 
 * Module name : spring4WithoutXml
 * Creating time :  31 lip 2014 17:55:41
 
 */
public interface InvoiceService {
  
    Page<Invoice> paging(Pageable pageable);
    Invoice save(Invoice invoice);
    Invoice findByName(String name);
    Invoice findById(Long id);
    void removeInvoice(Invoice invoice);
    void generateInvoices();
    
}
