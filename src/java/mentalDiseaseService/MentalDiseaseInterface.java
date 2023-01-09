/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mentalDiseaseService;

import entities.MentalDisease;
import exceptions.CreateException;
import exceptions.DeleteException;
import exceptions.MentalDiseaseException;
import exceptions.UpdateException;
import java.util.List;

/**
 *
 * @author Leire
 */
public interface MentalDiseaseInterface {

    public void createMentalDisease(MentalDisease mentalDisease) throws CreateException;

    public void updateMentalDisease(MentalDisease dailyNote) throws UpdateException;

    public void deleteMentalDisease(Long idMentalDisease) throws DeleteException;
    
    public List<MentalDisease> getAllMentalDiseases() throws MentalDiseaseException;
    
    public List<MentalDisease> getAllMentalDiseasesOrderByName(String mdname) throws MentalDiseaseException;
    
    public MentalDisease getMentalDiseasesByName(String mdname) throws MentalDiseaseException;

    public MentalDisease getMentalDiseasesById(Long idMentalDisease) throws MentalDiseaseException;

   

}