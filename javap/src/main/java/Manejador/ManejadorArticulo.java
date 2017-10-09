package Manejador;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import Modelo.Articulo;

public class ManejadorArticulo {

	
	private static EntityManager manager;
	private static EntityManagerFactory emf;
	public boolean Alta(/*int idArt,*/ Date fechaPublicado, float precio, String nombre,String descripcion, int idCat) 
	{
			emf = Persistence.createEntityManagerFactory("javap");
			manager = emf.createEntityManager();
			try {
			java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
			Articulo a = new Articulo();
			//a.setIdArt(idArt);
			a.setFechaPublicado(date);
			a.setPrecio(precio);
			a.setNombre(nombre);
			a.setDescripcion(descripcion);
			//a.setIdCat(idCat);
			
			manager.getTransaction().begin();
			manager.persist(a);
			manager.getTransaction().commit();
			return true;
			}catch(Exception e) {
				System.out.println(e.getMessage());
				return false;
			}
	}
	
		public boolean AltaArticulo(Articulo a, String nameAdmin) {
			Manejador.ManejadorTenant mten = new Manejador.ManejadorTenant();
			String unit = mten.TenantAdmin(nameAdmin);
			if (unit!=null) {
				System.out.println("LA UNIDAD SE LLAMA:" + unit);
				emf = Persistence.createEntityManagerFactory(unit);
				manager = emf.createEntityManager();
				manager.getTransaction().begin();
				manager.persist(a);
				manager.getTransaction().commit();
				return true;
			}else{
				return false;
			}
		}
		
		public Articulo getArticulo(int idArt) { 
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("javap");
	        EntityManager em = emf.createEntityManager();
				Articulo art = (Articulo) em .createQuery( "SELECT a from Articulo a where a.idArt = :idArt") 
						.setParameter("idArt", idArt) 
						.getSingleResult(); 
				return art; 
		}
	
	
	@SuppressWarnings("unchecked")
	public List<Articulo> getArticulos(){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("javap");
        EntityManager em = emf.createEntityManager();
        
		try{
			Query query = em.createQuery("SELECT a FROM Articulo a");
		    return (List<Articulo>) query.getResultList();
			
		}catch(Exception e) {
			return null;
		
		}
	}
	
	
	
	public boolean Modificacion(Articulo a) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("javap");
        EntityManager em = emf.createEntityManager();
        
        try 
        {
        				
			
        	Articulo ar = em.find(Articulo.class, a.getIdArt());
        	
        	ar.setNombre(a.getNombre());
        	ar.setDescripcion(a.getDescripcion());
        	ar.setFechaPublicado(a.getFechaPublicado());
        	ar.setPrecio(a.getPrecio());
        	
   
        	em.getTransaction().begin();

        	em.merge(ar);
        	em.getTransaction().commit();
        	em.close();
        	
        	return true;
        	
        }catch(Exception e) 
        {
        	em.close();
        	return false;
        }
		
		
	}

	
	public boolean Baja(int idArt) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("javap");
        EntityManager em = emf.createEntityManager();
        
        try 
        {
        	Articulo a = em.find(Articulo.class, idArt);
        	em.getTransaction().begin();
        	em.remove(a);
        	em.getTransaction().commit();
        	em.close();
        	return true;
        	
        }catch(Exception e) 
        {
        	em.close();
        	return false;
        }
        
		
	}
}
