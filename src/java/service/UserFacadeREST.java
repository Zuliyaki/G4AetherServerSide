/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.User;
import exceptions.CreateException;
import exceptions.DeleteException;
import exceptions.UpdateException;
import exceptions.UserException;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import userService.UserInterface;

/**
 *
 * @author unaibAndLeire
 */
@Path("entities.user")
public class UserFacadeREST {

    @EJB
    private UserInterface userEJB;

    private static final Logger LOGGER = Logger.getLogger(UserFacadeREST.class.getName());

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(User entity) {
        try {
            userEJB.createUser(entity);
        } catch (CreateException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("dni") String dniUser, User entity) {
        try {
            userEJB.updateUser(entity, dniUser);
        } catch (UpdateException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    @DELETE
    @Path("{dni}")
    public void remove(@PathParam("dni") String dniUser) {
        try {
            userEJB.deleteUser(dniUser);
        } catch (DeleteException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    @GET
    @Path("logInUser/{dniUser}/{passwordUser}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public User logInUser(@PathParam("dniUser") String dniUser, @PathParam("passwordUser") String passwordUser) {
        User user = null;
        try {
            user = userEJB.logInUser(dniUser, passwordUser);
        } catch (UserException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
        return user;
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<User> findAllUsers() {
        List<User> users = null;
        try {
            users = userEJB.findAllUsers();
        } catch (UserException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
        return users;
    }

    @GET
    @Path("{dni}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public User findUserByDni(@PathParam("dni") String dniUser) {
        User user = null;
        try {
            user = userEJB.findUserByDni(dniUser);
        } catch (UserException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
        return user;
    }

}
