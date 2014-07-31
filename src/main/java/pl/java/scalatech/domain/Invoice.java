package pl.java.scalatech.domain;

import static com.google.common.base.Preconditions.checkNotNull;
import groovy.transform.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import lombok.Data;
import lombok.ToString;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

/**
 * @author Sławomir Borowiec 
 * Module name : spring4WithoutXml
 * Creating time :  31 lip 2014 17:55:18
 
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "simple_invoice")
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper=false)
@NamedQueries({ @NamedQuery(name = "Invoice.findByUser", query = "FROM Invoice inv WHERE inv.user = :user ") })
public class Invoice extends AbstactId {

    private static final long serialVersionUID = -7305875286472112192L;

    @XmlElement(name = "invoice_name", required = true)
    @Column(name = "invoice_name", nullable = false)
    private String name;

    @XmlAttribute(name = "paid")
    private boolean paid;

    @Past
    @XmlElement(name = "create_date", required = true)
    @XmlJavaTypeAdapter(DateAdapter.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date creataDate;

    @Future
    @XmlElement(name = "pay_date", required = true)
    @XmlJavaTypeAdapter(DateAdapter.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date payDate;

    @Size(min = 3, max = 20)
    private String user;

    @Transient
    private String description;

    @Min(10)
    @Max(1000)
    @XmlElement(name = "amount", required = true)
    @NumberFormat(style = Style.CURRENCY)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @XmlAttribute(name = "type")
    @Column(name = "invoice_type")
    private InvoiceType type;

    public Invoice(InvoiceBuilder invoiceBuilder) {
        this.name = checkNotNull(invoiceBuilder.name, "invoice name can't be null");
        this.user = checkNotNull(invoiceBuilder.user, "invoice user can't be null");
        this.payDate = checkNotNull(invoiceBuilder.payDate, "invoice payDate can't be null");
        this.amount = checkNotNull(invoiceBuilder.amount, "invoice amount can't be null");
        this.type = checkNotNull(invoiceBuilder.type, "invoice type can't be null");
    }

    public static class InvoiceBuilder {
        private final String name;
        private boolean paid;
        private Date createDate;
        private final Date payDate;
        private final String user;
        private String description;
        private final BigDecimal amount;
        private final InvoiceType type;

        public InvoiceBuilder(String name, String user, BigDecimal amount, InvoiceType type, Date payDate) {
            this.name = name;
            this.user = user;
            this.amount = amount;
            this.type = type;
            this.payDate = payDate;
            this.createDate = new Date();
        }

        public InvoiceBuilder description(String description) {
            this.description = description;
            return this;
        }

        public InvoiceBuilder createDate(Date createDate) {
            this.createDate = createDate;
            return this;
        }

        public Invoice build() {
            return new Invoice(this);
        }
    }

}
