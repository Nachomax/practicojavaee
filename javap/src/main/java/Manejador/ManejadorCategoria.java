package Manejador;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import Modelo.Categoria;


public class ManejadorCategoria {

	
	private static EntityManager manager;
	private static EntityManagerFactory emf;
	public boolean Alta(int idCategoria, String nombre, String descripcion) 
	{
			emf = Persistence.createEntityManagerFactory("javap");
			manager = emf.createEntityManager();
			try {
			
			Categoria c = new Categoria();
			c.setIdCategoria(idCategoria);
			c.setNombre(nombre);
			c.setDescripcion(descripcion);
			
			manager.getTransaction().begin();
			manager.persist(c);
			manager.getTransaction().commit();
			return true;
			}catch(Exception e) {
				System.out.println(e.getMessage());
				return false;
			}
	}
	
		public boolean AltaCategoria(Categoria c) {
			emf = Persistence.createEntityManagerFactory("javap");
			manager = emf.createEntityManager();
			try {
			manager.getTransaction().begin();
			manager.persist(c);
			manager.getTransaction().commit();
			return true;
			}catch(Exception e) {
				System.out.println(e.getMessage());
				return false;
			}
		}
		
		public Categoria getCategoria(int idCategoria) { 
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("javap");
	        EntityManager em = emf.createEntityManager();
				Categoria c = (Categoria) em .createQuery( "SELECT c from Categoria c where c.idCategoria = :idCategoria") 
						.setParameter("idCategoria", idCategoria) 
						.getSingleResult(); 
				return c; 
		}
	
	
	@SuppressWarnings("unchecked")
	public List<Categoria> getCategorias(){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("javap");
        EntityManager em = emf.createEntityManager();
        
		try{
			Query query = em.createQuery("SELECT c FROM Categoria c");
		    return (List<Categoria>) query.getResultList();
			
		}catch(Exception e) {
			return null;
		
		}
	}
	

	
	public boolean Baja(int idCategoria) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("javap");
        EntityManager em = emf.createEntityManager();
        
        try 
        {
        	Categoria c = em.find(Categoria.class, idCategoria);
        	em.getTransaction().begin();
        	em.remove(c);
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
