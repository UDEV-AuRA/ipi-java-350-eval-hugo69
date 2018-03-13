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

    @Test //certain jeu de données ne passent pas a cause des double pour 1500 pourcentage de 0,1 on trouve pas 1650 mais 1650,00000002
    @Parameters({
            "1000 , 0.50 , 1500",
            "1500 , 0.50 , 2250",
    })
    public void testAugmenterSalaire(Double salaire, Double pourcentage, Double salaireExpected){
        //given
        HashSet<Technicien> test;
        Manager manager = new Manager("nom","prenom","M11223",null, salaire , test = new HashSet<Technicien>());
        Technicien t1 = new Technicien("test1","test1","M12",null,1000d,2);
        Technicien t2 = new Technicien("test2","test2","M122",null,1200d,3);
        test.add(t1);
        test.add(t2);
        manager.setEquipe(test);
        //when
        manager.augmenterSalaire(pourcentage);

        //then
        Assertions.assertThat(manager.getSalaire()).isEqualTo(salaireExpected);
        Assertions.assertThat(t1.getSalaire()).isEqualTo(1500d);
        Assertions.assertThat(t2.getSalaire()).isEqualTo(1800d);

    }
}
