package com.ipiecoles.java.java350.model.service;

import com.ipiecoles.java.java350.model.Manager;
import com.ipiecoles.java.java350.model.Technicien;
import com.ipiecoles.java.java350.repository.ManagerRepository;
import com.ipiecoles.java.java350.repository.TechnicienRepository;
import com.ipiecoles.java.java350.service.ManagerService;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by HCHARBONNEYR on 13/03/2018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ManagerIntegrationServiceTest   {
    @Autowired
    public ManagerService managerService;

    @Autowired
    public ManagerRepository managerRepository;

    @Autowired
    public TechnicienRepository technicienRepository;

    @Before
    public void setUp(){
        technicienRepository.deleteAll();
        managerRepository.deleteAll();
    }

    private HashSet<Technicien> equipe = new HashSet<>();

    @Test
    public void testAddTechnicien(){
        //Given avec de vraies données d'entrées

        Manager m = new Manager("RAMBO","John","M12345", null, 2000d, equipe);
        m = managerRepository.save(m);
        Technicien t = new Technicien("TEXAS","Walker","M12",null,1200d,2);
        t = technicienRepository.save(t);

        //When avec appel des vraies méthodes de repository...
        managerService.addTechniciens(m.getId(), t.getMatricule());

        //Then avec de vraies vérifications...
        Manager finalManager = managerRepository.findOneWithEquipeById(m.getId());
        Technicien finalTechnicien = technicienRepository.findByMatricule(t.getMatricule());

        Assertions.assertThat(finalManager.getEquipe()).contains(finalTechnicien);
        Assertions.assertThat(finalTechnicien.getManager()).isNotNull();
        Assertions.assertThat(finalTechnicien.getManager()).isEqualTo(finalManager);
    }

    @Test
    public void testAddTechnicienWithNoManager(){

        Technicien t = new Technicien("LEBRICOLEUR","Bob","M212",null,1200d,2);
        t = technicienRepository.save(t);
        try {
            managerService.addTechniciens(6L, t.getMatricule());
            //Assertions.fail("Impossible de trouver le manager d'identifiant " + null);
            //Assertions.fail("Impossible de trouver le manager d'identifiant M134");
            Assertions.fail("cela doit fail!!");
        }catch (Exception e){
            Assertions.assertThat(e).isInstanceOf(EntityNotFoundException.class);
            Assertions.assertThat(e).hasMessage("Impossible de trouver le manager d'identifiant " + 6L);
        }
    }

    @Test
    public void testAddTechnicienWithNoTechnicien(){

        Manager m = new Manager("RAMBO","John","M12345", null, 2000d, equipe);
        m = managerRepository.save(m);
        try {
            managerService.addTechniciens(m.getId(), "M1234");
            //Assertions.fail("Impossible de trouver le manager d'identifiant " + null);
            //Assertions.fail("Impossible de trouver le manager d'identifiant M134");
            Assertions.fail("cela doit fail!!");
        }catch (Exception e){
            Assertions.assertThat(e).isInstanceOf(EntityNotFoundException.class);
            Assertions.assertThat(e).hasMessage("Impossible de trouver le technicien de matricule M1234");
        }
    }

    @Test
    public void testAddTechnicienWithManagerIdNull(){

        Technicien t = new Technicien("LEBRICOLEUR","Bob","M212",null,1200d,2);
        t = technicienRepository.save(t);
        try {
            managerService.addTechniciens(null, t.getMatricule());
            //Assertions.fail("Impossible de trouver le manager d'identifiant " + null);
            //Assertions.fail("Impossible de trouver le manager d'identifiant M134");
            Assertions.fail("cela doit fail!!");
        }catch (Exception e){
            Assertions.assertThat(e).isInstanceOf(EntityNotFoundException.class);
            Assertions.assertThat(e).hasMessage("Impossible de trouver le manager d'identifiant " + null);
        }
    }

    @Test
    public void testAddTechnicienWithTechnicienMatriculeNull(){

        Manager m = new Manager("RAMBO","John","M12345", null, 2000d, equipe);
        m = managerRepository.save(m);
        try {
            managerService.addTechniciens(m.getId(), null);
            //Assertions.fail("Impossible de trouver le manager d'identifiant " + null);
            //Assertions.fail("Impossible de trouver le manager d'identifiant M134");
            Assertions.fail("cela doit fail!!");
        }catch (Exception e){
            Assertions.assertThat(e).isInstanceOf(EntityNotFoundException.class);
            Assertions.assertThat(e).hasMessage("Impossible de trouver le technicien de matricule "     + null);
        }
    }
}
