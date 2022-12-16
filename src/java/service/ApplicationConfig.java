/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Set;
import javax.ws.rs.core.Application;

/*
 * @author JulenB
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /* Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(service.AdminFacadeREST.class);
        resources.add(service.AppointmentFacadeREST.class);
        resources.add(service.DailyNoteFacadeREST.class);
        resources.add(service.DiagnosisFacadeREST.class);
        resources.add(service.MedicationFacadeREST.class);
        resources.add(service.MentalDiseaseFacadeREST.class);
        resources.add(service.PatientFacadeREST.class);
        resources.add(service.PsychologistFacadeREST.class);
        resources.add(service.TreatmentFacadeREST.class);
    }

}