package services;

import domain.Station;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by mars on 10/04/16.
 */
public class MediateurTest {


    @Test
    public void getStationsNonVides() throws Exception{
        //Given
        MediateurService serviceApi = new Mediateur();

        //When
        List<Station> stationNonVide = serviceApi.getStationsNonVides("Toulouse","4, Avenue des Herbettes,Toulouse, 31400");

        //Then
        assertNotSame(0,stationNonVide.size());
    }

    @Test
    public void getStationsNonCompletes() throws Exception{
        //Given
        MediateurService serviceApi = new Mediateur();

        //When
        List<Station> stationNonVide = serviceApi.getStationsNonCompletes("Toulouse","4, Avenue des Herbettes,Toulouse, 31400");

        //Then
        assertNotSame(0,stationNonVide.size());
    }


    @Test
    public void getInfoChausser() throws Exception{
        //Given
        MediateurService serviceApi = new Mediateur();

        //When
        String risque = serviceApi.getInfoChausser("Toulouse");

        //Then
        assertTrue(risque.equals("FAIBLE") ||
                risque.equals("MOYEN") ||
                risque.equals("FORT") );
    }
    @Test
    public void getTempsTrajet() throws Exception {
        //Given
        MediateurService serviceApi = new Mediateur();

        //When
        String tempsTrajet = serviceApi.getTempsTrajet("31, Rue Valade,Toulouse, 31000","38, Rue Valade,Toulouse, 31000","Toulouse");

        //Then
        assertEquals("00:01:31",tempsTrajet);
    }

    @Test
    public void getTempsTrajetVelo() throws Exception {
        //Given
        MediateurService serviceApi = new Mediateur();

        //When
        String tempsTrajet = serviceApi.getTempsTrajetVelo("31, Rue Valade,Toulouse, 31000","118, Route de Narbonne,Toulouse, 31400","Toulouse");


        //Then
        assertEquals("00:24:18",tempsTrajet);

    }


}