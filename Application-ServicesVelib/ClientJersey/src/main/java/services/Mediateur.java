package services;

import api.ApiArcGIS;
import api.ApiJCDecaux;
import api.ApiOpenStreetMap;
import api.ApiOpenWeatherMap;
import domain.Coordonne;
import domain.Station;

import java.util.ArrayList;
import java.util.List;

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

    public List<Station> getStationsNonVides (String contract) {

        List<Station> stationsVille = new ArrayList<>();
        stationsVille.addAll(jcDecaux.getStation(contract));

        Coordonne positionAdresseClient = openStreetMap.getLatLong(contract);

        //Récupération des distances

        return this.stationsNonVides;
    }

    public List<Station> getStationsNonCompletes (String contract) {
        return this.stationsNonCompletes;
    }
}
