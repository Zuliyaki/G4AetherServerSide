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
import exceptions.UserNotFoundException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.persistence.EntityManager;
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

    @GET
    @Path("logInUser/{dniUser}/{passwordUser}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public User logInUser(@PathParam("dniUser") String dniUser, @PathParam("passwordUser") String passwordUser) {
        User user = null;
        try {
            user = userEJB.logInUser(dniUser, passwordUser);
        } catch (UserNotFoundException ex) {
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
        } catch (UserNotFoundException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
        return users;
    }

    @GET
    @Path("findUsersByDni/{dniUser}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public User findUserByDni(@PathParam("dniUser") String dniUser) {
        User user = null;
        try {
            user = userEJB.findUserByDni(dniUser);
        } catch (UserNotFoundException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
        return user;
    }

}
