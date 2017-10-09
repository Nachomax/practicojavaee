package com.tsi2practico.javap;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlRootElement;
import Manejador.*;
import Modelo.*;
import java.io.IOException;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;


/**
 * Root resource (exposed at "myresource" path)
 */
@Path("restTenant")
public class restTenant {
	
	Manejador.ManejadorTenant mten = new Manejador.ManejadorTenant();
	
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Got itsss!";
    }
    
    
    @POST
	@Path("/alta/{url}/{admin}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response AltaTenant(@PathParam("url") String url, @PathParam("admin") String admin) 
	{
		try {			 
			boolean resp = mten.Alta(url, admin);
			if(resp==true) {
				 String outputelse = "Tenant dado de alta correctamente";
				 return Response.status(200).entity(outputelse).build();
			}else {
				String outputelse="Error dar de alta Tenant";
				 return Response.status(200).entity(outputelse).build();
			}
				 
		}catch(Exception e) {
			e.printStackTrace();
			String output = " TENANT NO SE PUDO DAR DE ALTA" ;
			return Response.status(500).entity(output).build();	
		}
		
	}
    
    @GET
	@Path("/tenantadmin/{name}")
	@Produces(MediaType.TEXT_PLAIN)
	public String getTenant(@PathParam("name") String name){
			 String t = mten.TenantAdmin(name);
			 return t;
	}
}
