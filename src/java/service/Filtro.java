/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.io.IOException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import javax.json.Json;
import javax.json.JsonObject;

/**
 *
 * @author esnip
 */
@Provider
@PreMatching
public class Filtro implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext request) throws IOException {
        String url=request.getUriInfo().getAbsolutePath().toString();
        if(url.contains("APIREGISTROESCOLAR/darToken"))
            return;
        String token=request.getHeaderString("Authorization");
         if(token==null){
             JsonObject json=Json.createObjectBuilder().add("Mensaje","Credenciales nulas")
                     .build();
             request.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity(json).type(MediaType.APPLICATION_JSON).build());
             return;
        }
        if(!token.equals("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJub2xvc2VyaWNrcGFyZWNlZmFsc28iLCJpYXQiOjE2MjE0NjUyNDIsImV4cCI6MTYyMTQ2NjE0MiwiZW1haWwiOiJwZXNjYWRvZmVsaXpAZ21haWwuY29tIn0.D1ehQSajW0IYXx6h8k_yDOswRsUvOLvq0eE-qPpEVGM")){
             JsonObject json=Json.createObjectBuilder().add("Mensaje","Credenciales Incorrectas")
                     .build();
             request.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity(json).entity(json).type(MediaType.APPLICATION_JSON).build());
             return;
        }
    }
    
}
