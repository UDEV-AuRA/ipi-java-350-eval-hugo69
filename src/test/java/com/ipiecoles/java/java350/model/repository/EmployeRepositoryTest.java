package com.ipiecoles.java.java350.model.repository;

import com.ipiecoles.java.java350.MySpringApplication;
import com.ipiecoles.java.java350.model.Commercial;
import com.ipiecoles.java.java350.model.Employe;
import com.ipiecoles.java.java350.repository.EmployeRepository;
import org.assertj.core.api.Assertions;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by HCHARBONNEYR on 12/03/2018.
 */
@RunWith(SpringRunner.class)
//@DataJpaTest
@SpringBootTest(classes = MySpringApplication.class)
public class EmployeRepositoryTest {

    @Autowired
    private EmployeRepository employeRepository;

    Commercial pierreDurand,rachidDurand,manuelPierre;

    @Before
    public void setUpRepo(){
        employeRepository.deleteAll();
    }


    public void setUpClassic(){

        pierreDurand = new Commercial();
        pierreDurand.setNom("Durand");
        pierreDurand.setPrenom("Pierre");
        pierreDurand.setSalaire(1000d);
        pierreDurand = employeRepository.save(pierreDurand);

        rachidDurand = new Commercial();
        rachidDurand.setNom("Durand");
        rachidDurand.setPrenom("Rachid");
        rachidDurand.setSalaire(10000d);
        rachidDurand = employeRepository.save(rachidDurand);

        manuelPierre = new Commercial();
        manuelPierre.setNom("Pierre");
        manuelPierre.setPrenom("Manuel");
        manuelPierre.setSalaire(1800d);
        manuelPierre = employeRepository.save(manuelPierre);
    }

    public void setUpEqual(){

        pierreDurand = new Commercial();
        pierreDurand.setNom("Durand");
        pierreDurand.setPrenom("Pierre");
        pierreDurand.setSalaire(2000d);
        pierreDurand = employeRepository.save(pierreDurand);

        rachidDurand = new Commercial();
        rachidDurand.setNom("Durand");
        rachidDurand.setPrenom("Rachid");
        rachidDurand.setSalaire(2000d);
        rachidDurand = employeRepository.save(rachidDurand);

        manuelPierre = new Commercial();
        manuelPierre.setNom("Pierre");
        manuelPierre.setPrenom("Manuel");
        manuelPierre.setSalaire(2000d);
        manuelPierre = employeRepository.save(manuelPierre);
    }

    public void setUpBigTwo(){
        pierreDurand = new Commercial();
        pierreDurand.setNom("Durand");
        pierreDurand.setPrenom("Pierre");
        pierreDurand.setSalaire(12000d);
        pierreDurand = employeRepository.save(pierreDurand);

        rachidDurand = new Commercial();
        rachidDurand.setNom("Durand");
        rachidDurand.setPrenom("Rachid");
        rachidDurand.setSalaire(10000d);
        rachidDurand = employeRepository.save(rachidDurand);

        manuelPierre = new Commercial();
        manuelPierre.setNom("Pierre");
        manuelPierre.setPrenom("Manuel");
        manuelPierre.setSalaire(1800d);
        manuelPierre = employeRepository.save(manuelPierre);
    }

    @Test
    public void testfindByNomOrPrenomAllIgnoreCasePrenom(){
        //given
        setUpClassic();
        /*
        Commercial c = new Commercial();
        c.setPrenom("Pierre");
        c.setNom("Durand");
        c = employeRepository.save(c);
        */
        //when
        List<Employe> employeList = employeRepository.findByNomOrPrenomAllIgnoreCase("pierre");
        //then
        Assertions.assertThat(employeList).isNotEmpty();
        Assertions.assertThat(employeList).hasSize(2);
        Assertions.assertThat(employeList).contains(pierreDurand,manuelPierre);
    }

    @Test
    public void testFindByNomOrPrenomAllIgnoreCaseNom(){
        //given
        setUpClassic();
        /*
        Commercial c = new Commercial();
        c.setPrenom("Pierre");
        c.setNom("Durand");
        c = employeRepository.save(c);
        */
        //when
        List<Employe> employeList = employeRepository.findByNomOrPrenomAllIgnoreCase("durand");
        //then
        Assertions.assertThat(employeList).isNotEmpty();
        Assertions.assertThat(employeList).hasSize(2);
        Assertions.assertThat(employeList).contains(pierreDurand,rachidDurand);
    }

    @Test
    public void testFindByNomOrPrenomAllIgnoreCasePrenomMaj(){
        //given
        setUpClassic();
        //when
        List<Employe> employeList = employeRepository.findByNomOrPrenomAllIgnoreCase("RACHID");
        //then
        Assertions.assertThat(employeList).isNotEmpty();
        Assertions.assertThat(employeList).hasSize(1);
        Assertions.assertThat(employeList).contains(rachidDurand);
    }

    @Test
    public void testFindByNomOrPrenomAllIgnoreCaseNotFound(){
        //given
        setUpClassic();
        /*
        Commercial c = new Commercial();
        c.setPrenom("Pierre");
        c.setNom("Durand");
        c = employeRepository.save(c);
        */
        //when
        List<Employe> employeList = employeRepository.findByNomOrPrenomAllIgnoreCase("Valls");
        //then

        Assertions.assertThat(employeList).isEmpty();
    }

    @Test
    public  void testFindRichClassic(){
        //given
        setUpClassic();
        //when
        List<Employe> employeList = employeRepository.findEmployePlusRiches();
        //then
        Assertions.assertThat(employeList).isNotEmpty();
        Assertions.assertThat(employeList).contains(rachidDurand);
        Assertions.assertThat(employeList).doesNotContain(pierreDurand,manuelPierre);
    }

    @Test
    public void testFindRichEqual(){
        setUpEqual();
        //when
        List<Employe> employeList = employeRepository.findEmployePlusRiches();
        //then
        Assertions.assertThat(employeList).isEmpty();
    }

    @Test
    public void testFindRichEmpty(){
        List<Employe> employeList = employeRepository.findEmployePlusRiches();
        //then

        Assertions.assertThat(employeList).isEmpty();

    }@Test
    public void testFindRichTwoBig(){
        setUpBigTwo();

        List<Employe> employeList = employeRepository.findEmployePlusRiches();
        //then

        Assertions.assertThat(employeList).contains(pierreDurand,rachidDurand);
        Assertions.assertThat(employeList).doesNotContain(manuelPierre);


    }
}
