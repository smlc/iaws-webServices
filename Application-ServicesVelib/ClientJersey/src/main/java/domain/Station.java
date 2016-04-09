package domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.json.JsonObject;
import java.util.List;
import java.util.Map;

/**
 * Created by mars on 20/03/16.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Station {

    private int number;
    private String contract_name;
    private String name;
    private String address;


    private Double distance;

    @JsonProperty("position")
    private Map<Object,Object> position;

    /* le nombre de points d'attache disponibles pour y ranger un vélo*/
    private int available_bike_stands;

    /* le nombre de vélos disponibles et opérationnels*/
    private int available_bikes;

    public String getContract_name() {
        return contract_name;
    }

    public void setContract_name(String contract_name) {
        this.contract_name = contract_name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Map<Object,Object> getPosition() {
        return position;
    }

    public void setPosition(Map<Object,Object> position) {
        this.position = position;
    }

    public int getAvailable_bike_stands() {
        return available_bike_stands;
    }

    public void setAvailable_bike_stands(int available_bike_stands) {
        this.available_bike_stands = available_bike_stands;
    }
    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public int getAvailable_bikes() {
        return available_bikes;
    }

    public void setAvailable_bikes(int available_bikes) {
        this.available_bikes = available_bikes;
    }

    @Override
    public String toString() {
        return "Station{" +
                "number=" + number +
                ", contract_name='" + contract_name + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", position=" + position +
                ", available_bike_stands=" + available_bike_stands +
                ", available_bikes=" + available_bikes +
                '}';
    }
}
