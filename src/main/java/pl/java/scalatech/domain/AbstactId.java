package pl.java.scalatech.domain;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Data;

/**
 * @author Sławomir Borowiec 
 * Module name : spring4WithoutXml
 * Creating time :  31 lip 2014 17:55:08
 
 */
@MappedSuperclass
@Data
public abstract class AbstactId implements Serializable{

    private static final long serialVersionUID = -2027966547795504944L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    
}
