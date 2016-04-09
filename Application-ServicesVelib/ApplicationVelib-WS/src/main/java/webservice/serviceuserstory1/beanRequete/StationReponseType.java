
package webservice.serviceuserstory1.beanRequete;

import javax.annotation.Generated;
import javax.jws.WebResult;
import javax.xml.bind.annotation.*;
import javax.xml.ws.ResponseWrapper;

import org.springframework.ws.server.endpoint.annotation.*;

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
@XmlType(name = "StationReponseType", namespace = "http://iaws/ws/contractfirst/station", propOrder = {
    "stations"
})
public class StationReponseType {

    @XmlElement(name = "Stations", namespace = "http://iaws/ws/contractfirst/station", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2016-04-08T05:06:52+02:00", comments = "JAXB RI v2.2.4-2")

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
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2016-04-08T05:06:52+02:00", comments = "JAXB RI v2.2.4-2")
    public void setStations(StationsType value) {
        this.stations = value;
    }

}
