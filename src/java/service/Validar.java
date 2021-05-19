/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author esnip
 */
@Path("darToken")
public class Validar {
  @POST
  @Produces (MediaType.APPLICATION_JSON)
  @Consumes (MediaType.APPLICATION_JSON)
   public Response validar(){
       boolean status=true;
       if(status){
           long tiempo= System.currentTimeMillis();
           String KEY="clavesini";
       String jwt=Jwts.builder()
                .signWith(SignatureAlgorithm.HS256,KEY)
               .setSubject("noloserickparecefalso")
               .setIssuedAt(new Date(tiempo))
               .setExpiration(new Date(tiempo+900000))
               .claim("email", "pescadofeliz@gmail.com")
               .compact();
       JsonObject json=Json.createObjectBuilder()
               .add("JWT",jwt).build();
                return Response.status(Response.Status.CREATED).entity(json).build();

       }
       return Response.status(Response.Status.UNAUTHORIZED).build();
   }
}
