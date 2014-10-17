package pl.java.scalatech.domain;



import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Builder;

@Entity
@Data
@EqualsAndHashCode(callSuper=true)
@Builder
public class Role extends PKEntity {

    /**
     * 
     */
    private static final long serialVersionUID = -804077594557972107L;
    private String name;
}
