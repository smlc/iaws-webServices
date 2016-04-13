
package webservice.serviceuserstory1.beanRequete;

import domain.Station;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;


import javax.xml.bind.*;
import javax.xml.bind.annotation.*;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;



/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the beanRequeteUserStory package.
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

    private final static QName _StationReponse_QNAME = new QName("http://iaws/ws/contractfirst/station", "StationReponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: beanRequeteUserStory2
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
     * Create an instance of {@link StationsType }
     * 
     */
    public StationsType createStationsType() {
        return new StationsType();
    }

    /**
     * Create an instance of {@link StationType }
     * 
     */

    /**
     * Créer une StationType à partir d'une station
     * @param station
     * @return
     */
    public StationType createStationType(Station station) {
        StationType stationType = new StationType();
        stationType.setName(station.getName());
        stationType.setAdresse(station.getAddress());
        stationType.setAvailableBikeStands(station.getAvailable_bike_stands()+"");
        stationType.setAvailableBikes(station.getAvailable_bikes()+"");
        stationType.setDistance(station.getDistance());

        return stationType;
    }



    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StationReponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://iaws/ws/contractfirst/station", name = "StationReponse")
    public JAXBElement<StationReponseType> createStationReponse(StationReponseType value) {
        return new JAXBElement<>(_StationReponse_QNAME, StationReponseType.class, value);
    }
    @XmlElementDecl(namespace = "http://iaws/ws/contractfirst/station", name = "StationReponse")
    public Element createElementStation(StationReponseType value){

            JAXBContext jaxbContext = null;
            final StringWriter writer = new StringWriter();
            JAXBElement<StationReponseType> element = new JAXBElement<>(_StationReponse_QNAME, StationReponseType.class, null, value);
            try {
                jaxbContext = JAXBContext.newInstance(StationReponseType.class);
                final XMLStreamWriter xmlStreamWriter = XMLOutputFactory
                        .newInstance().createXMLStreamWriter(writer);
                Marshaller marshaller = jaxbContext.createMarshaller();

                marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, "http://iaws/ws/contractfirst/station StationReponseContact.xsd" );
                marshaller.marshal(element,xmlStreamWriter);
            } catch (JAXBException e) {
                e.printStackTrace();
            } catch (XMLStreamException e) {
                e.printStackTrace();
            }

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder;


        Document document = null;
            try {
                builder = factory.newDocumentBuilder();
                 document = builder.parse( new InputSource(  new StringReader(writer.toString()) ) );
            } catch (SAXException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            }


        return document.getDocumentElement();

    }

}
