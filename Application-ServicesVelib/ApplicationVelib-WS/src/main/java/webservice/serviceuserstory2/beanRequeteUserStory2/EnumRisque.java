
package webservice.serviceuserstory2.beanRequeteUserStory2;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for enumRisque.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="enumRisque">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="FAIBLE"/>
 *     &lt;enumeration value="MOYEN"/>
 *     &lt;enumeration value="FORT"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "enumRisque", namespace = "http://iaws/ws/contractfirst/station")
@XmlEnum
public enum EnumRisque {

    FAIBLE,
    MOYEN,
    FORT;

    public String value() {
        return name();
    }

    public static EnumRisque fromValue(String v) {
        return valueOf(v);
    }

}
