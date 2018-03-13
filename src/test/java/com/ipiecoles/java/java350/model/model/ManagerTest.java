package com.ipiecoles.java.java350.model.model;

import com.ipiecoles.java.java350.model.Commercial;
import com.ipiecoles.java.java350.model.Manager;
import com.ipiecoles.java.java350.model.Technicien;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by HCHARBONNEYR on 13/03/2018.
 */
@RunWith(JUnitParamsRunner.class)
public class ManagerTest {

    @Test
    @Parameters({
            "0 , 0 , 0",
            "1500 , 0 , 1950",
            "1500, 1 , 2100",
            "1500 , 100 , 16950",
    })
    public void testSetSalaire(Double salaire, Double taille, Double salaireExpected){
        //Given initialisation des données d'entrée
        Manager manager = new Manager();
        Set<Technicien> equipe = new HashSet<>();
        int i=0;
        while(i<taille){
            Technicien t = new Technicien();
            t.setGrade(i);
            equipe.add(t);
            i++;
        }
        manager.setEquipe(equipe);

        //when
        manager.setSalaire(salaire);

        //Then vérification par rapport à la sortie de la méthode
        //Assertions.assertThat(equipe.size()).isGreaterThan(1);
        Assertions.assertThat(manager.getSalaire()).isNotNegative();
        Assertions.assertThat(manager.getSalaire()).isEqualTo(salaireExpected);
        Assertions.assertThat(manager.getSalaire()).isGreaterThanOrEqualTo(salaire);
    }


}
