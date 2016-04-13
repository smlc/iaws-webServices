
package webservice.serviceuserstory4.beanRequeteUserStory4;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the beanRequeteUserStory4 package.
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

    private final static QName _ResponseWs4_QNAME = new QName("http://iaws/ws/contractfirst/station", "ResponseWs4");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: beanRequeteUserStory4
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ResponseWs4Type }
     * 
     */
    public ResponseWs4Type createResponseWs4Type() {
        return new ResponseWs4Type();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResponseWs4Type }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://iaws/ws/contractfirst/station", name = "ResponseWs4")
    public JAXBElement<ResponseWs4Type> createResponseWs4(ResponseWs4Type value) {
        return new JAXBElement<ResponseWs4Type>(_ResponseWs4_QNAME, ResponseWs4Type.class, null, value);
    }

}
