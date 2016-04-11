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
        String tempsTrajet = serviceApi.getTempsTrajet("31 Rue valade,Toulouse, 31000","16 Allées du Président Franklin Roosevelt,Toulouse , 31000","Toulouse");

        //Then
        assertEquals(" 0: 11: 20",tempsTrajet);
    }
}