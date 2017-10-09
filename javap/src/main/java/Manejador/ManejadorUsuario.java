package Manejador;

import java.util.Calendar;
//import java.util.List;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.PersistenceUnit;

import Modelo.Admin;
import Modelo.Cliente;
import Modelo.User;
import javax.persistence.Query;




public class ManejadorUsuario {

	private static EntityManager manager;
	private static EntityManagerFactory emf;
	
	
	public boolean altaCliente(String username, String password, String name, String lastname,String email, int roleid) 
	{
		
			emf = Persistence.createEntityManagerFactory("\"javaDB1\"");
			manager = emf.createEntityManager();
			try {
			java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
			User u = new User();
			u.setUsername(username);
			u.setPassword(password);
			u.setName(name);
			u.setLastname(lastname);
			u.setEmail(email);
			u.setCreationdate(date);
			
			
			manager.getTransaction().begin();
			manager.persist(u);
			manager.getTransaction().commit();
			return true;
			}catch(Exception e) {
				System.out.println(e.getMessage());
				return false;
			}
	}
	
		public boolean AltaCliente(Cliente c) {
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
		
	
	
		public Cliente getCliente(int idCliente) { 
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("javap");
	        EntityManager em = emf.createEntityManager();
				Cliente c = (Cliente) em.createQuery( "SELECT c from Cliente c where c.idCliente = :idCliente") 
						.setParameter("idCliente", idCliente) 
						.getSingleResult(); 
				return c; 
		}
		
		@SuppressWarnings("unchecked")
		public List<Cliente> getClientes(){
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("javap");
	        EntityManager em = emf.createEntityManager();
	        
			try{
				Query query = em.createQuery("SELECT c FROM Cliente c");
			    return (List<Cliente>) query.getResultList();
				
			}catch(Exception e) {
				return null;
			
			}
		}
		
		
		public boolean Modificacion(Cliente c) {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("javap");
	        EntityManager em = emf.createEntityManager();
	        
	        try 
	        {
	        				
				
	        	Cliente cl = em.find(Cliente.class, c.getIdCliente());
	        
	        	cl.setPassword(c.getPassword());
	        	cl.setName(c.getName());
	        	cl.setLastname(c.getLastname());
	        	cl.setCodigoPostal(c.getCodigoPostal());
	        	cl.setDireccion(c.getDireccion());
	        	cl.setUsername(c.getUsername());
	        	
	   
	        	em.getTransaction().begin();

	        	em.merge(cl);
	        	em.getTransaction().commit();
	        	em.close();
	        	
	        	return true;
	        	
	        }catch(Exception e) 
	        {
	        	em.close();
	        	return false;
	        }
			
			
		}

		
		public boolean Baja(int idCliente) {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("javap");
	        EntityManager em = emf.createEntityManager();
	        
	        try 
	        {
	        	Cliente c = em.find(Cliente.class, idCliente);
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

		
		
		@SuppressWarnings("unchecked")
		public List<Admin> getAdmins(){
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("javap");
	        EntityManager em = emf.createEntityManager();
	        
			try{
				Query query = em.createQuery("SELECT a FROM Admin a");
			    return (List<Admin>) query.getResultList();
				
			}catch(Exception e) {
				return null;
			
			}
		}
		

}