
package webservice.serviceuserstory2.beanRequeteUserStory2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="risque" type="{http://iaws/ws/contractfirst/station}enumRisque"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "risque"
})
@XmlRootElement(name = "ReponseWS2", namespace = "http://iaws/ws/contractfirst/station")
public class ReponseWS2 {

    @XmlElement(namespace = "http://iaws/ws/contractfirst/station", required = true)
    protected EnumRisque risque;

    /**
     * Gets the value of the risque property.
     * 
     * @return
     *     possible object is
     *     {@link EnumRisque }
     *     
     */
    public EnumRisque getRisque() {
        return risque;
    }

    /**
     * Sets the value of the risque property.
     * 
     * @param value
     *     allowed object is
     *     {@link EnumRisque }
     *     
     */
    public void setRisque(EnumRisque value) {
        this.risque = value;
    }

}
