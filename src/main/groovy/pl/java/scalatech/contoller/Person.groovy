package pl.java.scalatech.contoller

import groovy.transform.Canonical;

import javax.persistence.GeneratedValue
import javax.persistence.Id
/**
 * @author Sławomir Borowiec 
 * Module name : spring4WithoutXml
 * Creating time :  31 lip 2014 17:56:07
 
 */
@Canonical
class Person {
    @Id
    @GeneratedValue
    Integer id

    String firstName

    String lastName
}
