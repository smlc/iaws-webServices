package services;

import api.ApiArcGIS;
import api.ApiJCDecaux;
import api.ApiOpenStreetMap;
import api.ApiOpenWeatherMap;
import domain.Coordonne;
import domain.Station;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mars on 03/04/16.
 */
public class Mediateur implements MediateurService {

    private ApiJCDecaux jcDecaux;
    private ApiOpenStreetMap openStreetMap;
    private ApiArcGIS arcGIS;
    private ApiOpenWeatherMap openWeatherMap;
    private List<Station> stationsNonVides;
    private List<Station> stationsNonCompletes;

    public Mediateur () {
        this.jcDecaux = new ApiJCDecaux();
        this.openStreetMap = new ApiOpenStreetMap();
        this.arcGIS = new ApiArcGIS();
        this.openWeatherMap = new ApiOpenWeatherMap();

        this.stationsNonVides = new ArrayList<>();
        this.stationsNonCompletes = new ArrayList<>();
    }

    public List<Station> getStationsNonVides (String contract, String address) {

        List<Station> threeStationsNotEmpty =  new ArrayList<>();
        List<Station> stations = new ArrayList<>();
        stations.addAll(jcDecaux.getStation(contract));
        Coordonne positionAddressClient = openStreetMap.getLatLong(address);

        //Récupération des 3 stations non vides
        threeStationsNotEmpty.addAll(arcGIS.getLengths(positionAddressClient, stations, true));

        return threeStationsNotEmpty;
    }

    public List<Station> getStationsNonCompletes (String contract, String address) {

        List<Station> threeStationsNotFull =  new ArrayList<>();
        List<Station> stations = new ArrayList<>();
        stations.addAll(jcDecaux.getStation(contract));
        Coordonne positionAddressClient = openStreetMap.getLatLong(address);

        //Récupération des 3 stations non complètes
        threeStationsNotFull.addAll(arcGIS.getLengths(positionAddressClient, stations, false));

        return threeStationsNotFull;
    }

    @Override
    public String getInfoChausser(String ville) {
        Double quantitePluie = openWeatherMap.getMeteo(ville);
        String risqueChausserMouiller = "";
        if(quantitePluie== .0){
            risqueChausserMouiller = "FAIBLE";
        }else if (quantitePluie > 1.0 && quantitePluie <2.0){
            risqueChausserMouiller = "MOYEN";
        }else{
            risqueChausserMouiller = "FORT";
        }
        return risqueChausserMouiller;
    }
}
