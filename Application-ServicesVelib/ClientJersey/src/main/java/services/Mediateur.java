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

        //t=D/V
        // v = 3,93 km/h
        //vitesse pieton google environ 4.799
        Temps tempsTrajet = calculTempsTrajet(distanceEntreDepartArriver,ville,vitessePietonEnKm );
        return formatTemps(tempsTrajet.getHeure(),tempsTrajet.getMinute(),tempsTrajet.getSeconde());
    }

    private Temps calculTempsTrajet(Double distanceAParcourir, String ville, double vitesseDeplacement){


        Double tempsEnHeure = distanceAParcourir / vitesseDeplacement;

        Double quantitePluie = openWeatherMap.getMeteo(ville);
        Double tempEnPlus;
        if(quantitePluie > 1.0 && quantitePluie <2.0){
            //+- 1km
            tempsEnHeure = distanceAParcourir / (vitessePietonEnKm-1);
        }else if (quantitePluie>2.0){
            //vitesse pieton divisé par deux
            tempsEnHeure = distanceAParcourir / (vitessePietonEnKm/2);
        }


        int heure = tempsEnHeure.intValue();

        double minute = (tempsEnHeure % 1.0) * 60;

        double seconde = (minute%1)*60;

        return new Temps(heure, (int)minute,(int)seconde);

    }

    private String formatTemps(int heure,int minute,int seconde){

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

        return String.format("%s:%s:%s",heureString,minuteString , secondeString);
    }
    public String getTempsTrajetVelo(String addressDepart,String addressArriver,String ville){

        //Station plus proches addresse
        Station stationNonVidePlusProcheDepart =getStationsNonVides(ville,addressDepart).get(0);
        Station stationNonCompletePlusProcheArriver = getStationsNonCompletes(ville,addressArriver).get(0);


        //Calcule temps trajet entre addresse départ et station non vide le plus proche

       
        Coordonne coordonneAddressDepart = openStreetMap.getLatLong(addressDepart);
        Coordonne coordonneStationDepart = new Coordonne((double)stationNonVidePlusProcheDepart.getPosition().get("lat"),
                (double)stationNonVidePlusProcheDepart.getPosition().get("lng")  );
        Double distanceEntreAddresseDepartStationDepart = arcGIS.getDistance(coordonneAddressDepart,coordonneStationDepart);

        
        Temps tempsTrajetVersStationDepart = calculTempsTrajet(distanceEntreAddresseDepartStationDepart,ville,vitessePietonEnKm);

        //Calcule temps trajet entre station depart et station arrivé
        Coordonne coordonneStationArriver = new Coordonne((double)stationNonCompletePlusProcheArriver.getPosition().get("lat"),
                (double)stationNonCompletePlusProcheArriver.getPosition().get("lng")  );
       
        Double distanceEntreStationDepartStationArriver = arcGIS.getDistance(coordonneStationDepart,coordonneStationArriver);

        //Temps trajet vers la stion arriver avec incidence météo
        Temps tempsTrajetVersStationArriver = calculTempsTrajet(distanceEntreStationDepartStationArriver,ville,vitesseCyclisteEnKm);


        //Calcule temps trajet entre station arriver et addresse arriver
        Coordonne coordonneAdresseArriver = openStreetMap.getLatLong(addressArriver);
        Double distanceEntreStationArriverAddresseArriver = arcGIS.getDistance(coordonneStationArriver,coordonneAdresseArriver);

        //Temps trajet vers l'adresse arriver avec incidence météo
        Temps tempsTrajetVersAddresseArriver = calculTempsTrajet(distanceEntreStationArriverAddresseArriver,ville,vitessePietonEnKm);


        Temps tempsTrajetTotal = tempsTrajetVersStationDepart.sommeTemps(tempsTrajetVersStationArriver).sommeTemps(tempsTrajetVersAddresseArriver);

        return formatTemps(tempsTrajetTotal.getHeure(),tempsTrajetTotal.getMinute(),tempsTrajetTotal.getSeconde());
    }



}
