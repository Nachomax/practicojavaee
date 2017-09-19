package Manejador;

import java.util.Calendar;
//import java.util.List;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import Modelo.User;
import javax.persistence.Query;




public class ManejadorUsuario {

	
	private static EntityManager manager;
	private static EntityManagerFactory emf;
	public boolean Alta(String username, String password, String name, String lastname,String email, int roleid) 
	{
			emf = Persistence.createEntityManagerFactory("javap");
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
			u.setRole(roleid);
			
			manager.getTransaction().begin();
			manager.persist(u);
			manager.getTransaction().commit();
			return true;
			}catch(Exception e) {
				System.out.println(e.getMessage());
				return false;
			}
	}
	
		public boolean AltaUser(User us) {
			emf = Persistence.createEntityManagerFactory("javap");
			manager = emf.createEntityManager();
			try {
			manager.getTransaction().begin();
			manager.persist(us);
			manager.getTransaction().commit();
			return true;
			}catch(Exception e) {
				System.out.println(e.getMessage());
				return false;
			}
		}
		
		public User getUser(String username, String password) { 
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("javap");
	        EntityManager em = emf.createEntityManager();
				User user = (User) em .createQuery( "SELECT u from User u where u.username = :username and u.password = :password") 
						.setParameter("username", username) 
						.setParameter("password", password)
						.getSingleResult(); 
				return user; 
		}
		
		@SuppressWarnings("unchecked")
		public List<User> getUsuarios(){
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("javap");
	        EntityManager em = emf.createEntityManager();
	        
			try{
				Query query = em.createQuery("SELECT u FROM User u");
			    return (List<User>) query.getResultList();
				
			}catch(Exception e) {
				return null;
			
			}
		}
		
		public boolean Modificacion(User u) {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("javap");
	        EntityManager em = emf.createEntityManager();
	        
	        try 
	        {
	        				
				
	        	User up = em.find(User.class, u.getUsername());
	        	
	        	up.setEmail(u.getEmail());
	        	up.setPassword(u.getPassword());
	        	up.setName(u.getName());
	        	up.setEmail(u.getEmail());
	        	up.setLastname(u.getLastname());
	        	
	   
	        	em.getTransaction().begin();

	        	em.merge(up);
	        	em.getTransaction().commit();
	        	em.close();
	        	
	        	return true;
	        	
	        }catch(Exception e) 
	        {
	        	em.close();
	        	return false;
	        }
			
			
		}

		
		public boolean Baja(String username) {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("javap");
	        EntityManager em = emf.createEntityManager();
	        
	        try 
	        {
	        	User u = em.find(User.class, username);
	        	em.getTransaction().begin();
	        	em.remove(u);
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