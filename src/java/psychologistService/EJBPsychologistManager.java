/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psychologistService;

import entities.Psychologist;
import exceptions.CreateException;
import exceptions.DeleteException;
import exceptions.UpdateException;
import exceptions.PsychologistException;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author unaibAndLeire
 */
@Stateless
public class EJBPsychologistManager implements PsychologistInterface {

    @PersistenceContext(unitName = "G4AetherPU")
    private EntityManager entityManager;

    @Override
    public void createPsychologist(Psychologist psychologist) throws CreateException {
        try {
            entityManager.persist(psychologist);
        } catch (Exception e) {
            throw new CreateException(e.getMessage());
        }
    }

    @Override
    public void updatePsychologist(Psychologist psychologist, String dni) throws UpdateException {
        try {
            if (!entityManager.contains(psychologist)) {
                entityManager.merge(psychologist);
            }
            entityManager.flush();
        } catch (Exception e) {
            throw new UpdateException(e.getMessage());
        }
    }

    @Override
    public void deletePsychologist(String dni) throws DeleteException {
        try {
            entityManager.remove(entityManager.merge(findPsychologistById(dni)));
        } catch (Exception e) {
            throw new DeleteException(e.getMessage());
        }
    }

    @Override
    public Psychologist findPsychologistById(String dni) throws PsychologistException {
        Psychologist psychologist;
        try {
            psychologist = (Psychologist) entityManager.find(Psychologist.class, dni);
        } catch (Exception e) {
            throw new PsychologistException(e.getMessage());
        }
        return psychologist;
    }

    @Override
    public List<Psychologist> findAllPsychologists() throws PsychologistException {
        List<Psychologist> psychologists = null;
        try {
            psychologists = entityManager.createNamedQuery("findAllPsychologists").getResultList();
        } catch (Exception e) {
            throw new PsychologistException(e.getMessage());
        }
        return psychologists;
    }

}
