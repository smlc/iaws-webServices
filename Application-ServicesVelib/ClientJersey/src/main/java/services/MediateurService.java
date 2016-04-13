package services;

import domain.Station;


import java.util.List;


/**
 * Created by mars on 04/04/16.
 */
public interface MediateurService {

    public List<Station> getStationsNonVides (String contract, String address);

    public List<Station> getStationsNonCompletes (String contract, String address);

    public String getInfoChaussee (String city);

    public String getTempsTrajet (String addressStart, String addressArrival, String city);

    public String getTempsTrajetVelo (String addressStart, String addressArrival, String city);


}
