package pl.java.scalatech.web.invoice;

import javax.validation.Valid;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.java.scalatech.domain.Invoice;
import pl.java.scalatech.service.invoice.InvoiceService;

@Controller
@Slf4j
@RequestMapping("/invoice")
public class InvoiceController {
    private final static String INVOICE = "invoice";
    private final static String INVOICE_NEW = "invoice";
    private final static String INVOICE_ERROR = "invoiceError";
    @Autowired
    private InvoiceService invoiceService;

  

    @RequestMapping(method = RequestMethod.GET)
    public String getInvoices(Model model, Pageable pageable) {
        Page<Invoice> page = invoiceService.paging(pageable);
        model.addAttribute("page", page);
        return "/invoices/invoice";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addInvoice(@Valid Invoice invoice, BindingResult result, Errors errors) {
        log.info("+++  invoice save :  {}", invoice);

        if (result.hasErrors()) {
            log.info("+++  invoice error  {}", result);
            return INVOICE_NEW;
        }
        invoiceService.save(invoice);
        return "redirect:/invoice/";

    }
}
