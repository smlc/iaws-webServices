package services;

import api.ApiArcGIS;
import api.ApiJCDecaux;
import api.ApiOpenStreetMap;
import api.ApiOpenWeatherMap;
import domain.Coordonne;
import domain.Station;

import javax.xml.datatype.XMLGregorianCalendar;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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
    private static Double vitessePietonEnKm = 4.799;
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


    public String getTempsTrajet (String addressDepart, String addressArriver,String ville){

        Coordonne coordonneAddressDepart = openStreetMap.getLatLong(addressDepart);
        Coordonne coordonneAddressArrive = openStreetMap.getLatLong(addressArriver);
        Double distanceEntreDepartArriver = arcGIS.getDistance(coordonneAddressDepart,coordonneAddressArrive);

        //t=D/V*
        //v = 3,6 km/h
        //vitesse pieton google = 4.799
        String tempsTrajet = calculTempsTrajet(distanceEntreDepartArriver,ville);
        return tempsTrajet;
    }

    private String calculTempsTrajet(Double distanceAParcourir,String ville){

        System.out.println("Distance :"+distanceAParcourir);
        Double tempsEnHeure = distanceAParcourir / vitessePietonEnKm;

        Double quantitePluie = openWeatherMap.getMeteo(ville);
        Double tempEnPlus;
        if(quantitePluie > 1.0 && quantitePluie <2.0){
            //+5 minutes
            tempEnPlus = 10.0/60.0;
        }else{
            //+15 minutes
            tempEnPlus = 15.0/60.0;
        }

        tempsEnHeure = tempsEnHeure + tempEnPlus;
        int heure = (int) TimeUnit.HOURS.toHours(Double.doubleToRawLongBits(tempsEnHeure));

        int minute = (tempsEnHeure.intValue() % 1)*60;

        int seconde = (minute%1)*60;

        return String.format(" %1$d: %2$d: %1$d",heure,minute,seconde);
    }

}
