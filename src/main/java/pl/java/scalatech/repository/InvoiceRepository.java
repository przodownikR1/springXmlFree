package pl.java.scalatech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import pl.java.scalatech.domain.Invoice;

/**
 * @author Sławomir Borowiec
 *         Module name : spring4WithoutXml
 *         Creating time : 25 lip 2014 13:23:13
 */

//@RepositoryRestResource(collectionResourceRel = "invoice", path = "invoice")
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    Invoice findByName(@Param("name") String name);
}
