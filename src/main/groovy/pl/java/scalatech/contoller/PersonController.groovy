package pl.java.scalatech.contoller

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

//import groovy.json.JsonBuilder
//import groovy.xml.MarkupBuilder;

/**
 * @author Sławomir Borowiec 
 * Module name : spring4WithoutXml
 * Creating time :  31 lip 2014 17:56:14
 
 */
@Controller
@RequestMapping("/people")
class PersonController {
    private List<Person> persons = new ArrayList<>();
    public PersonController(){
        Person p = new Person();
        p.firstName="slawek";
        p.lastName = "borowiec";
        
        persons.add(p);
        p = new Person();
        p.firstName ="mike";
        p.lastName ="tyson";
        persons.add(p);
    }
    
    @RequestMapping(value = "/hello", method = RequestMethod.GET, produces="application/json")
    @ResponseBody
    public String getJson() {
        return "test..."
        
    }
  /*  @RequestMapping(value = "/status/json", method = RequestMethod.GET, produces="application/json")
    @ResponseBody
    public String getJson() {
        def json = new JsonBuilder()
        json.status {
            date new Date()
            peopleCount persons.size()
        }
        json.toPrettyString()
    }
*/
   /* @RequestMapping(value = "/xml", method = RequestMethod.GET, produces="application/xml")
    @ResponseBody
    public String getXml() {
        StringWriter writer = new StringWriter()
        def xml = new MarkupBuilder(writer)

        xml.people {
            persons.each { Person p ->
                person {
                    id p.id
                    firstName p.firstName
                    lastName p.lastName
                }
            }
        }
        writer.toString()
    }*/

  /*  @RequestMapping(value = "/firstNames", method = RequestMethod.GET)
    @ResponseBody
    public String getFirstNamesHtml() {
        StringWriter writer = new StringWriter()
        def out = new MarkupBuilder(writer)

        out.html {
            head {
                title "List of First Names"
            }
            body {
                h1 "List of First Names"

                ul {
                    persons.each { Person p ->
                        li {
                            h3 p.firstName
                        }
                    }

                }
            }
        }
        writer.toString()
    }*/

}