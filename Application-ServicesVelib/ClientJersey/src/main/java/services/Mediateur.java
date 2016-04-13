package services;

import api.ApiArcGIS;
import api.ApiJCDecaux;
import api.ApiOpenStreetMap;
import api.ApiOpenWeatherMap;
import domain.Coordonne;
import domain.Station;
import domain.Temps;

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
    private  Double vitessePietonEnKm = 3.9393;
    private Double vitesseCyclisteEnKm = 16.25;


    public Mediateur () {
        this.jcDecaux = new ApiJCDecaux();
        this.openStreetMap = new ApiOpenStreetMap();
        this.arcGIS = new ApiArcGIS();
        this.openWeatherMap = new ApiOpenWeatherMap();

        this.stationsNonVides = new ArrayList<>();
        this.stationsNonCompletes = new ArrayList<>();
    }

    public List<Station> getStationsNonVides (String contract, String address) {

        // Initialisation des variables
        List<Station> threeStationsNotEmpty =  new ArrayList<>();
        List<Station> stations = new ArrayList<>();

        // Récupération de toutes les stations de Toulouse
        stations.addAll(jcDecaux.getStation(contract));

        // Récupération des coordonnées (latitude et longitude) de l'adresse client
        Coordonne positionAddressClient = openStreetMap.getLatLong(address);

        //Récupération des 3 stations non vides
        threeStationsNotEmpty.addAll(arcGIS.getLengths(positionAddressClient, stations, true));

        return threeStationsNotEmpty;
    }

    public List<Station> getStationsNonCompletes (String contract, String address) {

        // Initialisation des variables
        List<Station> threeStationsNotFull =  new ArrayList<>();
        List<Station> stations = new ArrayList<>();

        // Récupération de toutes les stations de Toulouse
        stations.addAll(jcDecaux.getStation(contract));

        // Récupération des coordonnées (latitude et longitude) de l'adresse client
        Coordonne positionAddressClient = openStreetMap.getLatLong(address);

        //Récupération des 3 stations non complètes
        threeStationsNotFull.addAll(arcGIS.getLengths(positionAddressClient, stations, false));

        return threeStationsNotFull;
    }

    @Override
    public String getInfoChaussee (String city) {

        Double quantitePluie = openWeatherMap.getMeteo(city);
        String risqueChausseeMouillee = "";

        if (quantitePluie== .0) {
            risqueChausseeMouillee = "FAIBLE";
        } else if (quantitePluie > 1.0 && quantitePluie <2.0) {
            risqueChausseeMouillee = "MOYEN";
        } else {
            risqueChausseeMouillee = "FORT";
        }

        return risqueChausseeMouillee;
    }


    public String getTempsTrajet (String addressStart, String addressArrival, String city) {

        Coordonne coordonneAddressDepart = openStreetMap.getLatLong(addressStart);
        Coordonne coordonneAddressArrivee = openStreetMap.getLatLong(addressArrival);
        Double distanceEntreDepartArrivee = arcGIS.getDistance(coordonneAddressDepart, coordonneAddressArrivee);

        //t=D/V
        // v = 3,93 km/h
        //vitesse pieton google environ 4.799
        Temps tempsTrajet = calculTempsTrajet(distanceEntreDepartArrivee, city,vitessePietonEnKm );

        return formatTemps(tempsTrajet.getHeure(),tempsTrajet.getMinute(),tempsTrajet.getSeconde());
    }

    private Temps calculTempsTrajet (Double distanceToBrowse, String city, double speedTravel) {

        Double tempsEnHeure = distanceToBrowse / speedTravel;

        Double quantitePluie = openWeatherMap.getMeteo(city);
        Double tempEnPlus;

        if(quantitePluie > 1.0 && quantitePluie <2.0) {
            //+- 1km
            tempsEnHeure = distanceToBrowse / (vitessePietonEnKm-1);
        } else if (quantitePluie>2.0) {
            //vitesse du piéton divisée par deux
            tempsEnHeure = distanceToBrowse / (vitessePietonEnKm/2);
        }

        int heure = tempsEnHeure.intValue();
        double minute = (tempsEnHeure % 1.0) * 60;
        double seconde = (minute%1)*60;

        return new Temps(heure, (int)minute,(int)seconde);

    }

    private String formatTemps (int heure, int minute, int seconde) {

        String heureString = heure+"";
        String minuteString = minute+"";
        String secondeString = seconde +"";

        if(heure < 10 ){
            heureString = "0"+heureString;
        }
        if (minute < 10){
            minuteString = "0"+minute;
        }
        if(seconde < 10){
            secondeString = "0"+seconde;
        }

        return String.format("%s:%s:%s", heureString, minuteString , secondeString);
    }

    public String getTempsTrajetVelo (String addressStart, String addressArrival, String city) {

        //Station plus proches addresse
        Station stationNonVidePlusProcheDepart =getStationsNonVides(city, addressStart).get(0);
        Station stationNonCompletePlusProcheArrivee = getStationsNonCompletes(city, addressArrival).get(0);

        //Calcul du temps de trajet entre addresse départ et station non vide la plus proche
        Coordonne coordonneAddressDepart = openStreetMap.getLatLong(addressStart);
        Coordonne coordonneStationDepart = new Coordonne((double)stationNonVidePlusProcheDepart.getPosition().get("lat"),
                (double)stationNonVidePlusProcheDepart.getPosition().get("lng")  );
        Double distanceEntreAddresseDepartStationDepart = arcGIS.getDistance(coordonneAddressDepart,coordonneStationDepart);

        //Temps de trajet vers la station départ avec incidence météo
        Temps tempsTrajetVersStationDepart = calculTempsTrajet(distanceEntreAddresseDepartStationDepart, city,vitessePietonEnKm);

        //Calcul du temps de trajet entre station depart et station arrivée
        Coordonne coordonneStationArrivee = new Coordonne((double) stationNonCompletePlusProcheArrivee.getPosition().get("lat"),
                (double) stationNonCompletePlusProcheArrivee.getPosition().get("lng")  );
        Double distanceEntreStationDepartStationArrivee = arcGIS.getDistance(coordonneStationDepart,coordonneStationArrivee);

        //Temps de trajet vers la station arrivée avec incidence météo
        Temps tempsTrajetVersStationArrivee = calculTempsTrajet(distanceEntreStationDepartStationArrivee, city,vitesseCyclisteEnKm);

        //Calcul du temps de trajet entre station arrivée et addresse arrivée
        Coordonne coordonneAdresseArrivee = openStreetMap.getLatLong(addressArrival);
        Double distanceEntreStationArriverAddresseArrivee = arcGIS.getDistance(coordonneStationArrivee,coordonneAdresseArrivee);

        //Temps de trajet vers l'adresse arrivée avec incidence météo
        Temps tempsTrajetVersAddresseArrivee = calculTempsTrajet(distanceEntreStationArriverAddresseArrivee, city,vitessePietonEnKm);

        //Temps total
        Temps tempsTrajetTotal = tempsTrajetVersStationDepart.sommeTemps(tempsTrajetVersStationArrivee).sommeTemps(tempsTrajetVersAddresseArrivee);

        return formatTemps(tempsTrajetTotal.getHeure(),tempsTrajetTotal.getMinute(),tempsTrajetTotal.getSeconde());
    }



}
