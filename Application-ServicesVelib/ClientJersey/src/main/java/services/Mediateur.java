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
    private  Double vitessePietonEnKm = 3.9393;


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
            //+- 1km
            tempsEnHeure = distanceAParcourir / (vitessePietonEnKm-1);
        }else if (quantitePluie>2.0){
            //vitesse pieton divisé par deux
            tempsEnHeure = distanceAParcourir / (vitessePietonEnKm/2);
        }

        System.out.println("temps en minute :" +(tempsEnHeure*60));
        int heure = tempsEnHeure.intValue();

        double minute = (tempsEnHeure % 1.0) * 60;

        double seconde = (minute%1)*60;

        System.out.println("minuteDouble "+minute+" secondeDouble "+ seconde);
        return formatTemps(heure, (int)minute,(int)seconde);

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
        return "";
    }
}
