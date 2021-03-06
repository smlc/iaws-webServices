
package webservice.serviceuserstory2.beanRequeteUserStory2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RequestWS2Type complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RequestWS2Type">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="numeroRue" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="nomRue" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codePostal" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ville" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RequestWS2Type", namespace = "http://iaws/ws/contractfirst/station", propOrder = {
    "numeroRue",
    "nomRue",
    "codePostal",
    "ville"
})
public class RequestWS2Type {

    @XmlElement(namespace = "http://iaws/ws/contractfirst/station", required = true)
    protected String numeroRue;
    @XmlElement(namespace = "http://iaws/ws/contractfirst/station", required = true)
    protected String nomRue;
    @XmlElement(namespace = "http://iaws/ws/contractfirst/station", required = true)
    protected String codePostal;
    @XmlElement(namespace = "http://iaws/ws/contractfirst/station", required = true)
    protected String ville;

    /**
     * Gets the value of the numeroRue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroRue() {
        return numeroRue;
    }

    /**
     * Sets the value of the numeroRue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroRue(String value) {
        this.numeroRue = value;
    }

    /**
     * Gets the value of the nomRue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNomRue() {
        return nomRue;
    }

    /**
     * Sets the value of the nomRue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNomRue(String value) {
        this.nomRue = value;
    }

    /**
     * Gets the value of the codePostal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodePostal() {
        return codePostal;
    }

    /**
     * Sets the value of the codePostal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodePostal(String value) {
        this.codePostal = value;
    }

    /**
     * Gets the value of the ville property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVille() {
        return ville;
    }

    /**
     * Sets the value of the ville property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVille(String value) {
        this.ville = value;
    }

}
