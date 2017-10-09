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
import Modelo.*;
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
    
    
    @POST
	@Path("/alta/{username}/{creationdate}/{email}/{lastname}/{name}/{password}/{idCliente}/{direccion}/{codigoPostal}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response Alta(@PathParam("username") String username,@PathParam("creationdate") Date creationdate, @PathParam("email") String email,@PathParam("lastname") String lastname,@PathParam("name") String name, @PathParam("password") String password, @PathParam("idCliente") int idCliente, @PathParam("direccion") String direccion, @PathParam("codigoPostal") int codigoPostal) 
	{
		try {
			 Cliente c = new Cliente();
			 c.setName(name);
			 c.setLastname(lastname);
			 c.setUsername(username);
			 c.setPassword(password);
			 c.setEmail(email);
			 c.setCreationdate(creationdate);
			 c.setIdCliente(idCliente);
			 c.setCodigoPostal(codigoPostal);
			 c.setDireccion(direccion);
			 
			 
			 boolean respuesta = true;
			 
			 if(respuesta) {
				 muser.AltaCliente(c);
				 String outputelse = "Cliente"+c.getName()+"dado de alta correctamente";
			 	return Response.status(200).entity(outputelse).build();
			 	
			 }
			 else {
				 String outputelse = "Cliente"+c.getName()+"ya existe" ;
				 return Response.status(200).entity(outputelse).build();
			 }
				 
		}catch(Exception e) {
			e.printStackTrace();
			String output = "Usuario"+username+"NO SE PUDO DAR DE ALTA" ;
			return Response.status(500).entity(output).build();	
		}
		
	}
    
    @GET
	@Path("/usuario/{idCliente}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCliente(@PathParam("idCliente") int idCliente){
		try{
			 Cliente c = new Cliente();
			 c = muser.getCliente(idCliente);
			 boolean respuesta = true;
			 if (respuesta) {
				 ObjectMapper mapper = new ObjectMapper();
				 String json = mapper.writeValueAsString(c);
				 return Response.status(200).entity(json).build();
			 }else {
				 String outputelse = "Datos invÃ¡lidos";
				 return Response.status(200).entity(outputelse).build();
			 }
			 			
		}catch(Exception e) {
			return null;
		
		}
	}
    
    
    
    @GET
	@Path("/getClientes")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getClientes(){
		try{
			
			List<Cliente> lista = new ArrayList<Cliente>();
			 lista = muser.getClientes();
			
			 ObjectMapper mapper = new ObjectMapper();
			 String json = mapper.writeValueAsString(lista);
			 return Response.status(200).entity(json).build();
			
		}catch(Exception e) {
			return null;
		
		}
	}
    
    //andando
    @GET
  	@Path("/getAdmins")
  	@Produces(MediaType.APPLICATION_JSON)
  	public Response getAdmins(){
  		try{
  			
  			List<Admin> lista = new ArrayList<Admin>();
  			 lista = muser.getAdmins();
  			
  			 ObjectMapper mapper = new ObjectMapper();
  			 String json = mapper.writeValueAsString(lista);
  			 return Response.status(200).entity(json).build();
  			
  		}catch(Exception e) {
  			return null;
  		
  		}
  	}
    
    
    
    @PUT
	@Path("/update/{password}/{username}/{name}/{lastname}/{codigoPostal}/{direccion}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response update(@PathParam("password") String password,@PathParam("username") String username, @PathParam("name") String name,@PathParam("lastname") String lastname,@PathParam("codigoPostal") int codigoPostal, @PathParam("direccion") String direccion) {
		try {
			
			
			Cliente c = new Cliente();
			c.setPassword(password);
			c.setUsername(username);
			c.setLastname(lastname);
			c.setName(name);
			c.setCodigoPostal(codigoPostal);
			c.setDireccion(direccion);
			
			
			String output = "Cliente "+name+" MODIFICADO CORRECTAMENTE" ;
			
			muser.Modificacion(c);
			 
			return Response.status(200).entity(output).build();
			
			
		}catch(Exception e) {
			e.printStackTrace();
			String outputelse = "Cliente "+name+"  NO SE PUDO MODIFICAR CORRECTAMENTE" ;
			return Response.status(500).entity(outputelse).build();
			
		}
	}

    
    @DELETE
    @Path("/delete/{idCliente}")
	//@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response delete(@PathParam("idCliente") int idCliente) {
		
        
        try 
        {
        	String output = "Cliente "+idCliente+" dado de BAJA CORRECTAMENTE" ;
        	boolean respuesta = muser.Baja(idCliente);
        	if(respuesta)
        		return Response.status(200).entity(output).build();
        	else {
        		 String outputelse = "Usuario "+idCliente+"  NO SE PUDO DAR DE BAJA" ;
				 return Response.status(200).entity(outputelse).build();
        	}
        	//return "Usuario Dado de baja correctamente";
        	
        }catch(Exception e) 
        {
        	e.printStackTrace(); 
        	String output = "Cliente "+idCliente+"  NO SE PUDO DAR DE BAJA" ;
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
