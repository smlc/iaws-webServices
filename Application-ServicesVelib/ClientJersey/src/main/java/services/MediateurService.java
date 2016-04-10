package services;

import domain.Station;


import java.util.List;


/**
 * Created by mars on 04/04/16.
 */
public interface MediateurService {

    public List<Station> getStationsNonVides (String contract, String address);

    public List<Station> getStationsNonCompletes (String contract, String address);

    public String getInfoChausser(String ville);


    public String getTempsTrajet (String addressDepart,String addressArriver,String ville);


}
