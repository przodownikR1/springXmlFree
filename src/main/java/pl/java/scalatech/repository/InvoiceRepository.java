package pl.java.scalatech.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.java.scalatech.domain.Invoice;

/**
 * @author Sławomir Borowiec
 *         Module name : spring4WithoutXml
 *         Creating time : 25 lip 2014 13:23:13
 */
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    Invoice findByName(String name);
}
