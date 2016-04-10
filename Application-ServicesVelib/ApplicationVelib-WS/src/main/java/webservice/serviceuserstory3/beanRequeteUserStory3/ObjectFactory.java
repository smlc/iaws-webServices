
package webservice.serviceuserstory3.beanRequeteUserStory3;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the beanRequeteUserStory3 package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ResponseWs3_QNAME = new QName("http://iaws/ws/contractfirst/station", "ResponseWs3");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: beanRequeteUserStory3
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ResponseWs3Type }
     * 
     */
    public ResponseWs3Type createResponseWs3Type() {
        return new ResponseWs3Type();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResponseWs3Type }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://iaws/ws/contractfirst/station", name = "ResponseWs3")
    public JAXBElement<ResponseWs3Type> createResponseWs3(ResponseWs3Type value) {
        return new JAXBElement<ResponseWs3Type>(_ResponseWs3_QNAME, ResponseWs3Type.class, null, value);
    }

}
