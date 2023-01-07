/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.Treatment;
import entities.TreatmentId;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.PathSegment;

/**
 *
 * @author unaiz
 */
@Stateless
@Path("entities.treatment")
public class TreatmentFacadeREST extends AbstractFacade<Treatment> {

    @PersistenceContext(unitName = "G4AetherPU")
    private EntityManager em;

    private TreatmentId getPrimaryKey(PathSegment pathSegment) {
        /*
         * pathSemgent represents a URI path segment and any associated matrix parameters.
         * URI path part is supposed to be in form of 'somePath;diagnosisId=diagnosisIdValue;medicationId=medicationIdValue;day=dayValue;dayTime=dayTimeValue'.
         * Here 'somePath' is a result of getPath() method invocation and
         * it is ignored in the following code.
         * Matrix parameters are used as field names to build a primary key instance.
         */
        entities.TreatmentId key = new entities.TreatmentId();
        javax.ws.rs.core.MultivaluedMap<String, String> map = pathSegment.getMatrixParameters();
        java.util.List<String> diagnosisId = map.get("diagnosisId");
        if (diagnosisId != null && !diagnosisId.isEmpty()) {
            key.setDiagnosisId(new java.lang.Long(diagnosisId.get(0)));
        }
        java.util.List<String> medicationId = map.get("medicationId");
        if (medicationId != null && !medicationId.isEmpty()) {
            key.setMedicationId(new java.lang.Long(medicationId.get(0)));
        }
        java.util.List<String> day = map.get("day");
        if (day != null && !day.isEmpty()) {
            key.setDay(entities.EnumDay.valueOf(day.get(0)));
        }
        java.util.List<String> dayTime = map.get("dayTime");
        if (dayTime != null && !dayTime.isEmpty()) {
            key.setDayTime(entities.EnumDayTime.valueOf(dayTime.get(0)));
        }
        return key;
    }

    public TreatmentFacadeREST() {
        super(Treatment.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Treatment entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") PathSegment id, Treatment entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") PathSegment id) {
        entities.TreatmentId key = getPrimaryKey(id);
        super.remove(super.find(key));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Treatment find(@PathParam("id") PathSegment id) {
        entities.TreatmentId key = getPrimaryKey(id);
        return super.find(key);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Treatment> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Treatment> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
