
package webservice.serviceuserstory4.beanRequeteUserStory4;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RequestWs4Type complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RequestWs4Type">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="adresse" type="{http://iaws/ws/contractfirst/station}adresseType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RequestWs4Type", namespace = "http://iaws/ws/contractfirst/station", propOrder = {
    "adresse"
})
public class RequestWs4Type {

    @XmlElement(namespace = "http://iaws/ws/contractfirst/station")
    protected List<AdresseType> adresse;

    /**
     * Gets the value of the adresse property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the adresse property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAdresse().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AdresseType }
     * 
     * 
     */
    public List<AdresseType> getAdresse() {
        if (adresse == null) {
            adresse = new ArrayList<AdresseType>();
        }
        return this.adresse;
    }

}
