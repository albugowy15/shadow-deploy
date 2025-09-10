package com.albugowy15;

import java.util.ArrayList;
import java.util.List;

import org.jboss.resteasy.reactive.RestPath;
import org.jboss.resteasy.reactive.RestQuery;

import jakarta.ws.rs.BeanParam;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

record UserData(
        String username,
        Integer age,
        Boolean isVip) {
}

@Path("/user")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {
    private List<UserData> users = new ArrayList<>();

    public UserResource() {
        users.add(new UserData("Bughowi", 20, true));
        users.add(new UserData("Kholid", 20, false));
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<UserData> hello() {
        return users;
    }

    @GET
    @Path("{username}")
    public String getUser(@BeanParam GetUserParameters parameters) {
        return parameters.username + parameters.age + parameters.isVip;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void createUser(UserData userData) {
        users.add(userData);
    }

    private record GetUserParameters(
            @RestPath String username,
            @RestQuery Integer age,
            @RestQuery Boolean isVip) {
    }

}
