/*package com.tsi2practico.javap;

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
import Modelo.Articulo;
import Modelo.Categoria;
import org.codehaus.jackson.map.ObjectMapper;


@Path("restArticulos")
public class restArticulo {

	
	Manejador.ManejadorArticulo mart = new Manejador.ManejadorArticulo();
	Manejador.ManejadorCategoria maca = new Manejador.ManejadorCategoria();
	
	
	 	@POST
		@Path("/altaArticulo/{idArt}/{fechaPublicado}/{precio}/{nombre}/{descripcion}/{idCat}")
		@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
		public Response altaArticulo(@PathParam("idArt") int idArt,@PathParam("fechaPublicado") Date fechaPublicado, @PathParam("nombre") String nombre,@PathParam("descripcion") String descripcion, @PathParam("precio") float precio, @PathParam("idCat") int idCat) 
		{
			try {
				 Articulo a = new Articulo();
				 Categoria c = new Categoria();
				 c = maca.getCategoria(idCat);
				 a.setIdArt(idArt);
				 a.setFechaPublicado(fechaPublicado);
				 a.setNombre(nombre);
				 a.setDescripcion(descripcion);
				 a.setPrecio(precio);
				 a.setIdCat(c);
 
				 
				 boolean respuesta = true;
				 
				 if(respuesta) {
					 mart.AltaArticulo(a);
					 String outputelse = "Articulo"+nombre+"dado de alta correctamente";
				 	return Response.status(200).entity(outputelse).build();
				 	
				 }
				 else {
					 String outputelse = "Articulo"+nombre+"ya existe" ;
					 return Response.status(200).entity(outputelse).build();
				 }
					 
			}catch(Exception e) {
				e.printStackTrace();
				String output = "Usuario"+nombre+"NO SE PUDO DAR DE ALTA" ;
				return Response.status(500).entity(output).build();	
			}
			
		}
	    
	    @GET
		@Path("/articulo/{idArt}")
	
	
	
	//andando
    @GET
	@Path("/getArticulos")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getArticulos(){
		try{
			
			List<Articulo> lista = new ArrayList<Articulo>();
			 lista = mart.getArticulos();
			
			 ObjectMapper mapper = new ObjectMapper();
			 String json = mapper.writeValueAsString(lista);
			 return Response.status(200).entity(json).build();
			
		}catch(Exception e) {
			return null;
		
		}
	}
	
}*/