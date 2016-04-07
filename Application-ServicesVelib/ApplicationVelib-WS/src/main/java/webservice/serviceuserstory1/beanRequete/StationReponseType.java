
package webservice.serviceuserstory1.beanRequete;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for StationReponseType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="StationReponseType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Stations" type="{http://iaws/ws/contractfirst/stationReponse}StationsType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StationReponseType", namespace = "http://iaws/ws/contractfirst/stationReponse", propOrder = {
    "stations"
})
public class StationReponseType {

    @XmlElement(name = "Stations", namespace = "http://iaws/ws/contractfirst/stationReponse", required = true)
    protected StationsType stations;

    /**
     * Gets the value of the stations property.
     * 
     * @return
     *     possible object is
     *     {@link StationsType }
     *     
     */
    public StationsType getStations() {
        return stations;
    }

    /**
     * Sets the value of the stations property.
     * 
     * @param value
     *     allowed object is
     *     {@link StationsType }
     *     
     */
    public void setStations(StationsType value) {
        this.stations = value;
    }

}
