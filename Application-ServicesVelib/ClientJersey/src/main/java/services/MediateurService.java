package services;

import domain.Station;

import javax.xml.datatype.XMLGregorianCalendar;
import java.util.List;
import java.util.Map;

/**
 * Created by mars on 04/04/16.
 */
public interface MediateurService {

    public List<Station> getStationsNonVides (String contract, String address);

    public List<Station> getStationsNonCompletes (String contract, String address);

    public String getInfoChausser(String ville);

    public XMLGregorianCalendar getTempsTrajet (String addressStart, String addressArrival);

}
