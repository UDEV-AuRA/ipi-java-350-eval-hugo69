package com.ipiecoles.java.java350.model;


import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 * Created by HCHARBONNEYR on 12/03/2018.
 */
public class CommercialTest {

    @Test
    public void testPrimeAnnuelleWithCANull(){
        //Given initialisation des données d'entrée
        Commercial commercial = new Commercial();
        commercial.setCaAnnuel(null);
        //When Exécution/test de la méthode
        Double prime = commercial.getPrimeAnnuelle();
        //Then vérification par rapport à la sortie de la méthode
        Assertions.assertThat(prime).isEqualTo(500d);

    }

    @Test
    public void testPrimeAnnuelleIsHigher(){
        //given
        Commercial commercial = new Commercial();
        commercial.setCaAnnuel(100000d);

        //when
        Double prime = commercial.getPrimeAnnuelle();

        //then
        Assertions.assertThat(prime).isEqualTo(5000d);
    }

    @Test
    public void testPrimeAnnuelleIsZero(){
        //Given initialisation des données d'entrée
        Commercial commercial = new Commercial();
        commercial.setCaAnnuel(0d);
        //When Exécution/test de la méthode
        Double prime = commercial.getPrimeAnnuelle();
        //Then vérification par rapport à la sortie de la méthode
        Assertions.assertThat(prime).isEqualTo(500d);
    }

}
