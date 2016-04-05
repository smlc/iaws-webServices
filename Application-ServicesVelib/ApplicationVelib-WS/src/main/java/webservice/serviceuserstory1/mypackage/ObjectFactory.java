
package webservice.serviceuserstory1.mypackage;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the webservice.serviceuserstory1.mypackage package.
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

    private final static QName _StationReponse_QNAME = new QName("http://iaws/ws/contractfirst/stationReponse", "StationReponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: webservice.serviceuserstory1.mypackage
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link StationReponseType }
     * 
     */
    public StationReponseType createStationReponseType() {
        return new StationReponseType();
    }

    /**
     * Create an instance of {@link StationType }
     * 
     */
    public StationType createStationType() {
        return new StationType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StationReponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://iaws/ws/contractfirst/stationReponse", name = "StationReponse")
    public JAXBElement<StationReponseType> createStationReponse(StationReponseType value) {
        return new JAXBElement<StationReponseType>(_StationReponse_QNAME, StationReponseType.class, null, value);
    }

}
