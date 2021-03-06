
package webservice.serviceuserstory1.beanRequete;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for StationRequestType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="StationRequestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="numeroRue" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="nomRue" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codePostal" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ville" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *       &lt;attribute name="requeteStationNonVide" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StationRequestType", namespace = "http://iaws/ws/contractfirst/station", propOrder = {
    "numeroRue",
    "nomRue",
    "codePostal",
    "ville"
})
public class StationRequestType {

    @XmlElement(namespace = "http://iaws/ws/contractfirst/station", required = true)
    protected String numeroRue;
    @XmlElement(namespace = "http://iaws/ws/contractfirst/station", required = true)
    protected String nomRue;
    @XmlElement(namespace = "http://iaws/ws/contractfirst/station", required = true)
    protected String codePostal;
    @XmlElement(namespace = "http://iaws/ws/contractfirst/station", required = true)
    protected String ville;
    @XmlAttribute(name = "requeteStationNonVide")
    protected Boolean requeteStationNonVide;

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

    /**
     * Gets the value of the requeteStationNonVide property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isRequeteStationNonVide() {
        return requeteStationNonVide;
    }

    /**
     * Sets the value of the requeteStationNonVide property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setRequeteStationNonVide(Boolean value) {
        this.requeteStationNonVide = value;
    }

}
