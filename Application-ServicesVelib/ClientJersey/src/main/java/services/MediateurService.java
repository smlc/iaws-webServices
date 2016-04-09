package services;

import domain.Station;

import javax.xml.datatype.XMLGregorianCalendar;
import java.util.List;
import java.util.Map;

/**
 * Created by mars on 04/04/16.
 */
public interface MediateurService {

    public Map<Station, Double> getStationsNonVides (String contract, String address);

    public Map<Station, Double> getStationsNonCompletes (String contract, String address);

    public XMLGregorianCalendar getTempsTrajet (AdresseArriveeType address)
}
