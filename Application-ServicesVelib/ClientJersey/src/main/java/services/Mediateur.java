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

    public Map<Station, Double> getStationsNonVides (String contract, String address) {

        Map<Station, Double> threeStationsNotEmpty =  new HashMap<>();
        List<Station> stations = new ArrayList<>();
        stations.addAll(jcDecaux.getStation(contract));
        Coordonne positionAddressClient = openStreetMap.getLatLong(address);

        //Récupération des 3 stations non vides
        threeStationsNotEmpty.putAll(arcGIS.getLengths(positionAddressClient, stations, true));

        return threeStationsNotEmpty;
    }

    public Map<Station, Double> getStationsNonCompletes (String contract, String address) {

        Map<Station, Double> threeStationsNotFull =  new HashMap<>();
        List<Station> stations = new ArrayList<>();
        stations.addAll(jcDecaux.getStation(contract));
        Coordonne positionAddressClient = openStreetMap.getLatLong(address);

        //Récupération des 3 stations non complètes
        threeStationsNotFull.putAll(arcGIS.getLengths(positionAddressClient, stations, false));

        return threeStationsNotFull;
    }
}
