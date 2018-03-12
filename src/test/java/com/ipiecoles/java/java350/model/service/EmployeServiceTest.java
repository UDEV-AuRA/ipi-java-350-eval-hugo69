package com.ipiecoles.java.java350.model.service;

import com.ipiecoles.java.java350.model.Commercial;
import com.ipiecoles.java.java350.model.Employe;
import com.ipiecoles.java.java350.repository.EmployeRepository;
import com.ipiecoles.java.java350.service.EmployeService;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import javax.persistence.EntityNotFoundException;

/**
 * Created by HCHARBONNEYR on 12/03/2018.
 */
@RunWith(MockitoJUnitRunner.class)
public class EmployeServiceTest {

    @InjectMocks
    private EmployeService employeService;

    @Mock
    private EmployeRepository employeRepository;

    @Test
    public void testFindByMatriculeFound(){
        //given
        Commercial commercial = new Commercial();
        Mockito.when(employeRepository.findByMatricule("C12345")).thenReturn(commercial);

        //when
        Employe employe = employeService.findByMatricule("C12345");

        //then
        Assertions.assertThat(employe).isEqualTo(commercial);

    }

    @Test(expected = EntityNotFoundException.class)
    public void testFindByMatriculeNotFound(){
        //given
        Commercial commercial = new Commercial();
        Mockito.when(employeRepository.findByMatricule("C12345")).thenReturn(null);

        //when
        Employe employe = employeService.findByMatricule("C12345");

        //then

    }

    @Test
    public void testFindByMatriculeNotFound2(){
        //given
        Mockito.when(employeRepository.findByMatricule("C12345")).thenReturn(null);

        //when
        try {
            Employe employe = employeService.findByMatricule("C12345");
            Assertions.fail("Cela aurait dû planter !");
        }
        catch (Exception e){
            //then
            Assertions.assertThat(e).isInstanceOf(EntityNotFoundException.class);
            Assertions.assertThat(e).hasMessage("Impossible de trouver l'employé de matricule C12345");
        }

    }
}
