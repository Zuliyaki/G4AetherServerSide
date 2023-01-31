/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mentalDiseaseService;

import entities.MentalDisease;
import exceptions.CreateException;
import exceptions.DeleteException;
import exceptions.UpdateException;
import exceptions.MentalDiseaseException;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Leire
 */
@Stateless
public class EJBMentalDiseaseManager implements MentalDiseaseInterface {

    @PersistenceContext(unitName = "G4AetherPU")
    private EntityManager entityManager;

    /**
     * Create a new mental disease
     *
     * @param mentalDisease Mental disease to save
     * @throws CreateException
     */
    @Override
    public void createMentalDisease(MentalDisease mentalDisease) throws CreateException {
        try {
            entityManager.persist(mentalDisease);
        } catch (Exception e) {
            throw new CreateException(e.getMessage());
        }
    }

    /**
     * Update a mental disease
     *
     * @@param mentalDisease Mental disease to update
     * @throws UpdateException
     */
    @Override
    public void updateMentalDisease(MentalDisease mentalDisease) throws UpdateException {
        try {
            if (!entityManager.contains(mentalDisease)) {
                entityManager.merge(mentalDisease);
            }
            entityManager.flush();
        } catch (Exception e) {
            throw new UpdateException(e.getMessage());
        }
    }

    /**
     * Delete a mental disease by id
     *
     * @param idMentalDisease Id of the mental disease to delete
     * @throws DeleteException
     */
    @Override
    public void deleteMentalDisease(Long idMentalDisease) throws DeleteException {
        try {
            entityManager.remove(entityManager.merge(getMentalDiseasesById(idMentalDisease)));
        } catch (MentalDiseaseException e) {
            throw new DeleteException(e.getMessage());
        }
    }

    /**
     * Get all mental disease
     *
     * @return List of all mental disease
     * @throws MentalDiseaseException
     */
    @Override
    public List<MentalDisease> getAllMentalDiseases() throws MentalDiseaseException {
        List<MentalDisease> mentalDiseases;
        try {
            mentalDiseases = entityManager.createNamedQuery("getAllMentalDiseases").getResultList();
        } catch (Exception e) {
            throw new MentalDiseaseException(e.getMessage());
        }
        return mentalDiseases;
    }

    /**
     * Get all mental disease order by name
     *
     * @return List of all mental disease order by name
     * @throws MentalDiseaseException
     */
    @Override
    public List<MentalDisease> getAllMentalDiseasesOrderByName() throws MentalDiseaseException {
        List<MentalDisease> mentalDiseases;
        try {
            mentalDiseases = entityManager.createNamedQuery("getAllMentalDiseasesOrderByName").getResultList();
        } catch (Exception e) {
            throw new MentalDiseaseException(e.getMessage());
        }
        return mentalDiseases;
    }

    /**
     * Get a mental disease by name
     *
     * @param mdName Id of the mental disease to search for
     * @return Selected mental disease
     * @throws MentalDiseaseException
     */
    @Override
    public List<MentalDisease> getMentalDiseasesByName(String mdName) throws MentalDiseaseException {
        List<MentalDisease> mentalDisease;
        try {
            mentalDisease =entityManager.createNamedQuery("getMentalDiseasesByName").setParameter("mdName", mdName).getResultList();
        } catch (Exception e) {
            throw new MentalDiseaseException(e.getMessage());
        }
        return mentalDisease;
    }

    /**
     * Get a mental disease by id
     *
     * @param idMentalDisease Id of the mental disease to search for
     * @return Selected mental disease
     * @throws MentalDiseaseException
     */
    @Override
    public MentalDisease getMentalDiseasesById(Long idMentalDisease) throws MentalDiseaseException {
        MentalDisease mentalDisease;
        try {
            mentalDisease = entityManager.find(MentalDisease.class, idMentalDisease);
        } catch (Exception e) {
            throw new MentalDiseaseException(e.getMessage());
        }
        return mentalDisease;
    }

}
