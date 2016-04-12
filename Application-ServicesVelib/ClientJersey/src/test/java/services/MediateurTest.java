package services;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by mars on 10/04/16.
 */
public class MediateurTest {

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