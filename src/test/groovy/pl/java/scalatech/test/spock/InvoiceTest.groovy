package pl.java.scalatech.test.spock

import pl.java.scalatech.domain.Invoice;
import pl.java.scalatech.domain.InvoiceType;
import spock.lang.Specification;

class InvoiceTest extends Specification {
    Invoice invoice = new Invoice();
    
    def "create invoice"() {
       
        expect:
          
          invoice.type == InvoiceType.BUSINESS;
        
        
    }
}
