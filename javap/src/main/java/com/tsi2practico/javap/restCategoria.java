package com.tsi2practico.javap;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import Modelo.Categoria;
import org.codehaus.jackson.map.ObjectMapper;


@Path("restCategorias")
public class restCategoria {

	
	Manejador.ManejadorCategoria maca = new Manejador.ManejadorCategoria();

	
	
	 	@POST
		@Path("/altaCategoria/{idCategoria}/{nombre}/{descripcion}")
		@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
		public Response altaCategoria(@PathParam("idCategoria") int idCategoria, @PathParam("nombre") String nombre,@PathParam("descripcion") String descripcion) 
		{
			try {
				 Categoria c = new Categoria();
				 c.setIdCategoria(idCategoria);
				 c.setNombre(nombre);
				 c.setDescripcion(descripcion);
 
				 boolean respuesta = true;
				 
				 if(respuesta) {
					 maca.AltaCategoria(c);
					 String outputelse = "Categoria"+nombre+"dada de alta correctamente";
				 	return Response.status(200).entity(outputelse).build();
				 	
				 }
				 else {
					 String outputelse = "Categoria"+nombre+"ya existe" ;
					 return Response.status(200).entity(outputelse).build();
				 }
					 
			}catch(Exception e) {
				e.printStackTrace();
				String output = "Categoria"+nombre+"NO SE PUDO DAR DE ALTA" ;
				return Response.status(500).entity(output).build();	
			}
			
		}
	    
	    @GET
		@Path("/categoria/{idCategoria}")
		@Produces(MediaType.APPLICATION_JSON)
		public Response getCategoria(@PathParam("idCategoria") int idCategoria){
			try{
				 Categoria c = new Categoria();
				 c = maca.getCategoria(idCategoria);
				 boolean respuesta = true;
				 if (respuesta) {
					 ObjectMapper mapper = new ObjectMapper();
					 String json = mapper.writeValueAsString(c);
					 return Response.status(200).entity(json).build();
				 }else {
					 String outputelse = "Identificador inválido";
					 return Response.status(200).entity(outputelse).build();
				 }
				 			
			}catch(Exception e) {
				return null;
			
			}
		}
	    
	
	
	//andando
    @GET
	@Path("/getCategorias")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCategorias(){
		try{
			
			List<Categoria> lista = new ArrayList<Categoria>();
			 lista = maca.getCategorias();
			
			 ObjectMapper mapper = new ObjectMapper();
			 String json = mapper.writeValueAsString(lista);
			 return Response.status(200).entity(json).build();
			
		}catch(Exception e) {
			return null;
		
		}
	}
	

       @DELETE
       @Path("/delete/{idCategoria}")
   	//@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
   	public Response delete(@PathParam("idCategoria") int idCategoria) {
   		
           
           try 
           {
           	String output = "Categoria "+idCategoria+" dada de BAJA CORRECTAMENTE" ;
           	boolean respuesta = maca.Baja(idCategoria);
           	if(respuesta)
           		return Response.status(200).entity(output).build();
           	else {
           		 String outputelse = "Categoria "+idCategoria+"  NO SE PUDO DAR DE BAJA" ;
   				 return Response.status(200).entity(outputelse).build();
           }
           }catch(Exception e) 
           {
           	e.printStackTrace(); 
           	String output = "Categoria "+idCategoria+"  NO SE PUDO DAR DE BAJA" ;
   			return Response.status(500).entity(output).build();
           	
           }
           
   	}
       

}
