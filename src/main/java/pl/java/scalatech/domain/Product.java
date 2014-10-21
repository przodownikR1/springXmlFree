package pl.java.scalatech.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Builder;

@Data
@EqualsAndHashCode(callSuper=true)
@Entity
@Builder
public class Product extends AbstactId{

    private static final long serialVersionUID = 1L;
    private String name;
    private BigDecimal price;
    private String description;
}
