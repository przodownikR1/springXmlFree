package pl.java.scalatech.service.invoice.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.java.scalatech.domain.Invoice;
import pl.java.scalatech.domain.InvoiceType;
import pl.java.scalatech.repository.InvoiceRepository;
import pl.java.scalatech.service.invoice.InvoiceService;

/**
 * @author Sławomir Borowiec 
 * Module name : spring4WithoutXml
 * Creating time :  31 lip 2014 17:55:37
 
 */
@Service
@Transactional(readOnly = true)
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;

    @Autowired
    public InvoiceServiceImpl(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
        Date date = new Date(LocalDate.of(2015, 1, 1).toEpochDay());
        invoiceRepository.save(new Invoice.InvoiceBuilder("slawek", new BigDecimal(12), InvoiceType.BUSINESS, date).build());
        date = new Date(LocalDate.of(2015, 1, 2).toEpochDay());
        invoiceRepository.save(new Invoice.InvoiceBuilder("slawek2", new BigDecimal(12), InvoiceType.BUSINESS, date).build());
    }

    @Override
    public Page<Invoice> paging(Pageable pageable) {
        return invoiceRepository.findAll(pageable);
    }

    @Override
    public Invoice save(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }

    @Override
    public Invoice findByName(String name) {
        return invoiceRepository.findByName(name);
    }

    @Override
    public Invoice findById(Long id) {
        return invoiceRepository.findOne(id);
    }

    @Override
    public void removeInvoice(Invoice invoice) {
        invoiceRepository.delete(invoice);
    }

}
