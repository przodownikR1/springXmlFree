package pl.java.scalatech.tool;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import com.google.common.collect.Lists;

import pl.java.scalatech.domain.Invoice;
import pl.java.scalatech.domain.InvoiceType;
import pl.java.scalatech.domain.Product;

public final class InvoiceGenerator {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    private InvoiceGenerator() {
    }

    public static List<Invoice> generate() {
        List<Invoice> invoices = Lists.newArrayList();
        Invoice invoice = new Invoice();
        invoice.setAmount(new BigDecimal("99"));
        Instant instant = LocalDate.parse("1999-10-01").atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        invoice.setCreataDate(Date.from(instant));

        invoice.setPayDate(Date.from(LocalDate.parse("2015-10-01").atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));

        invoice.setDescription("description.....");
        invoice.setPaid(true);
        invoice.setType(InvoiceType.BUSINESS);
      
        invoice.setName("computers");

        invoice.getProducts().add(Product.builder().name("toy").price(new BigDecimal(213)).description("toy desc").build());
        invoice.getProducts().add(Product.builder().name("spoon").price(new BigDecimal(13)).description("spoon desc").build());
        invoice.getProducts().add(Product.builder().name("knife").price(new BigDecimal(35)).description("knife desc").build());
        
        invoices.add(invoice);

        invoice = new Invoice();
        invoice.setAmount(new BigDecimal("992"));
        
        invoice.setCreataDate(Date.from(LocalDate.parse("1999-12-01").atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));

        invoice.setPayDate(Date.from(LocalDate.parse("2015-11-01").atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));

        
        invoice.setDescription("etst....");
        invoice.setPaid(true);
        invoice.setType(InvoiceType.CUSTOMER);
        
        invoice.setName("pens");
        invoice.getProducts().add(Product.builder().name("mouse").price(new BigDecimal(45)).description("mouse desc").build());
        invoice.getProducts().add(Product.builder().name("key").price(new BigDecimal(7)).description("key desc").build());
        invoice.getProducts().add(Product.builder().name("wire").price(new BigDecimal(5)).description("wire desc").build());
        invoices.add(invoice);

        invoice = new Invoice();
        invoice.setAmount(new BigDecimal("139"));

        invoice.setCreataDate(Date.from(LocalDate.parse("2001-12-01").atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));

        invoice.setPayDate(Date.from(LocalDate.parse("2015-03-09").atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));


        invoice.setDescription("hhhhh....");
        invoice.setPaid(true);
        invoice.setType(InvoiceType.CUSTOMER);

        invoice.setName("boxer");
        invoice.getProducts().add(Product.builder().name("helmet").price(new BigDecimal(45)).description("helmet desc").build());
        invoice.getProducts().add(Product.builder().name("shoe").price(new BigDecimal(7)).description("shoe desc").build());
        invoice.getProducts().add(Product.builder().name("gloves").price(new BigDecimal(5)).description("gloves desc").build());
        invoices.add(invoice);
        return invoices;
    }

}