package pl.java.scalatech.test.spock

import pl.java.scalatech.domain.Invoice
import spock.lang.Specification

class InvoiceTest extends Specification {
    Invoice invoice = new Invoice();

    def "create invoice"() {

        expect:

        invoice.type == null;
    }
}
