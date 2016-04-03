
package webservice.serviceuserstory1.mypackage;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for stationType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="stationType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="adresse" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="available_bike_stands" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="available_bikes" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "stationType", namespace = "http://iaws/ws/contractfirst/station", propOrder = {
    "name",
    "adresse",
    "availableBikeStands",
    "availableBikes"
})
public class StationType {

    @XmlElement(namespace = "http://iaws/ws/contractfirst/station", required = true)
    protected String name;
    @XmlElement(namespace = "http://iaws/ws/contractfirst/station", required = true)
    protected String adresse;
    @XmlElement(name = "available_bike_stands", namespace = "http://iaws/ws/contractfirst/station", required = true)
    protected String availableBikeStands;
    @XmlElement(name = "available_bikes", namespace = "http://iaws/ws/contractfirst/station", required = true)
    protected String availableBikes;

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the adresse property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdresse() {
        return adresse;
    }

    /**
     * Sets the value of the adresse property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdresse(String value) {
        this.adresse = value;
    }

    /**
     * Gets the value of the availableBikeStands property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAvailableBikeStands() {
        return availableBikeStands;
    }

    /**
     * Sets the value of the availableBikeStands property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAvailableBikeStands(String value) {
        this.availableBikeStands = value;
    }

    /**
     * Gets the value of the availableBikes property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAvailableBikes() {
        return availableBikes;
    }

    /**
     * Sets the value of the availableBikes property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAvailableBikes(String value) {
        this.availableBikes = value;
    }

}
