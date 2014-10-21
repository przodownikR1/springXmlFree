package pl.java.scalatech.domain;

import groovy.transform.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import com.google.common.collect.Lists;

/**
 * @author Sławomir Borowiec
 *         Module name : spring4WithoutXml
 *         Creating time : 31 lip 2014 17:55:18
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "simple_invoice")
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = false)
@NoArgsConstructor
@NamedQueries({ @NamedQuery(name = "Invoice.findByUser", query = "FROM Invoice inv WHERE inv.createdBy = :user ") })
public class Invoice extends AuditableEntity {

    private static final long serialVersionUID = -7305875286472112192L;

    @XmlElement(name = "invoice_name", required = true)
    @Column(name = "invoice_name", nullable = false)
    private String name;

    @XmlAttribute(name = "paid")
    private boolean paid;

    // @Past
    @XmlElement(name = "create_date", required = true)
    @XmlJavaTypeAdapter(DateAdapter.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date creataDate;

    // @Future
    @XmlElement(name = "pay_date", required = true)
    @XmlJavaTypeAdapter(DateAdapter.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date payDate;

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
    
    @OneToMany
    
    private List<Product> products = Lists.newArrayList();

}
