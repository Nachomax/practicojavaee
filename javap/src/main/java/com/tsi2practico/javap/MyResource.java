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
import Modelo.User;
import java.io.IOException;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;


/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource {

	Manejador.ManejadorUsuario muser = new Manejador.ManejadorUsuario();
	UsuarioPOCO up = new UsuarioPOCO();

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Got itsss!";
    }
    
    
    //POST PARA PROBAR
    @POST
	@Path("/alta/{password}/{username}/{name}/{lastname}/{email}/{creationdate}/{roleid}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response postAlta(@PathParam("password") String password,@PathParam("username") String username, @PathParam("name") String name,@PathParam("lastname") String lastname,@PathParam("email") String email, @PathParam("creationdate") Date creationdate,@PathParam("roleid") int roleid) 
	{
		try {
			String output = "Usuario"+username+" dado de ALTA CORRECTAMENTE" ;
			 User usuarionuevo = new User();
			 usuarionuevo.setName(name);
			 usuarionuevo.setLastname(lastname);
			 usuarionuevo.setUsername(username);
			 usuarionuevo.setPassword(password);
			 usuarionuevo.setEmail(email);
			 usuarionuevo.setRole(roleid);
			 
			 boolean respuesta = true;
			 
			 if(respuesta) {
				 muser.AltaUser(usuarionuevo);
				 String outputelse = "Usuario"+usuarionuevo.getName()+" pa ver que onda" ;
			 	return Response.status(200).entity(outputelse).build();
			 	
			 }
			 else {
				 String outputelse = "Usuario"+username+" ya existe, NO SE PUDO DAR DE ALTA" ;
				 return Response.status(200).entity(outputelse).build();
			 }
				 
		}catch(Exception e) {
			e.printStackTrace();
			String output = "Usuario"+username+" NO SE PUDO DAR DE ALTA" ;
			return Response.status(500).entity(output).build();	
		}
		
	}
    
    @GET
	@Path("/usuario/{password}/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUsuario(@PathParam("password") String password,@PathParam("username") String username){
		try{
			 User u = new User();
			 u = muser.getUser(username, password);
			 boolean respuesta = true;
			 if (respuesta) {
				 ObjectMapper mapper = new ObjectMapper();
				 String json = mapper.writeValueAsString(u);
				 return Response.status(200).entity(json).build();
			 }else {
				 String outputelse = "Datos inválidos";
				 return Response.status(200).entity(outputelse).build();
			 }
			 			
		}catch(Exception e) {
			return null;
		
		}
	}
    
    
    
    @GET
	@Path("/getUsuarios")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUsuarios(){
		try{
			
			List<User> lista = new ArrayList<User>();
			 lista = muser.getUsuarios();
			
			 ObjectMapper mapper = new ObjectMapper();
			 String json = mapper.writeValueAsString(lista);
			 return Response.status(200).entity(json).build();
			
		}catch(Exception e) {
			return null;
		
		}
	}
    
    @PUT
	@Path("/update/{password}/{username}/{name}/{lastname}/{email}/{creationdate}/{roleid}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response update(@PathParam("password") String password,@PathParam("username") String username, @PathParam("name") String name,@PathParam("lastname") String lastname,@PathParam("email") String email, @PathParam("creationdate") Date creationdate,@PathParam("roleid") int roleid) {
		try {
			
			
			User u = new User();
			u.setCreationdate(creationdate);
			u.setEmail(email);
			u.setLastname(lastname);
			u.setName(name);
			u.setPassword(password);
			u.setRole(roleid);
			u.setUsername(username);
			
			String output = "Usuario "+username+" MODIFICADO CORRECTAMENTE" ;
			
			muser.Modificacion(u);
			 
			return Response.status(200).entity(output).build();
			
			
		}catch(Exception e) {
			e.printStackTrace();
			String outputelse = "Usuario "+username+"  NO SE PUDO MODIFICAR CORRECTAMENTE" ;
			return Response.status(500).entity(outputelse).build();
			
		}
	}

    
    @DELETE
    @Path("/delete/{usename}")
	//@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response delete(@PathParam("usename") String username) {
		
        
        try 
        {
        	String output = "Usuario "+username+" dado de BAJA CORRECTAMENTE" ;
        	boolean respuesta = muser.Baja(username);
        	if(respuesta)
        		return Response.status(200).entity(output).build();
        	else {
        		 String outputelse = "Usuario "+username+"  NO SE PUDO DAR DE BAJA" ;
				 return Response.status(200).entity(outputelse).build();
        	}
        	//return "Usuario Dado de baja correctamente";
        	
        }catch(Exception e) 
        {
        	e.printStackTrace(); 
        	String output = "Usuario "+username+"  NO SE PUDO DAR DE BAJA" ;
			return Response.status(500).entity(output).build();
        	//return "Error al dar de baja  Usuario";
        }
        
	}
	



    
    @GET
	@Path("/prueba")
	public String probar() {
		return "ESTO FUNCA CHE";
	}
    
    
	@XmlRootElement
	public class UsuarioPOCO{
		
		private String username;
		private Date creationdate;
		private String email;
		private String lastname;
		private String name;
		private String password;
		private int roleid;
		public UsuarioPOCO(String string, String string2, String string3, String string4, String string5, Date e,
				int i) {
			// TODO Auto-generated constructor stub
		}
		public UsuarioPOCO() {
			// TODO Auto-generated constructor stub
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public Date getCreationdate() {
			return creationdate;
		}
		public void setCreationdate(Date creationdate) {
			this.creationdate = creationdate;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getLastname() {
			return lastname;
		}
		public void setLastname(String lastname) {
			this.lastname = lastname;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public int getRoleid() {
			return roleid;
		}
		public void setRoleid(int roleid) {
			this.roleid = roleid;
		}	
	}
}
