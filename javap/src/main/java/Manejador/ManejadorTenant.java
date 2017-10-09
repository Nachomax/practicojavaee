package Manejador;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import Modelo.Tenant;

public class ManejadorTenant {

	
	private static EntityManager manager;
	private static EntityManagerFactory emf;
	public boolean Alta(String url, String admin) 
	{
			emf = Persistence.createEntityManagerFactory("javap");
			manager = emf.createEntityManager();
			try {
			Tenant t = new Tenant();
			t = TenantDisponible();
			if(t==null) {
				return false;
			}
			else {
				t.setUrl(url);
				t.setUsername(admin);
				t.setUsado(true);
				manager.getTransaction().begin();
	        	manager.merge(t);
	        	manager.getTransaction().commit();
	        	manager.close();
	        	return true;
			}
			}catch(Exception e) {
				System.out.println(e.getMessage());
				return false;
			}
	}
	
	
		public Tenant TenantDisponible() {
			emf = Persistence.createEntityManagerFactory("javap");
			manager = emf.createEntityManager();
				Tenant ten = (Tenant) manager .createQuery( "SELECT t from Tenant t where t.usado = false") 
						//.setParameter("idArt", idArt) 
						.getSingleResult(); 
				return ten; 
		}
		
		public String TenantAdmin(String name) {
			emf = Persistence.createEntityManagerFactory("javap");
			manager = emf.createEntityManager();
			System.out.println("aca llego man");
			Tenant ten = (Tenant) manager .createQuery( "SELECT t from Tenant t where t.username = :name") 
					.setParameter("name", name) 
					.getSingleResult(); 
			System.out.print("aca ya no");
			String nameunit = ten.getNameunit(); 
			return nameunit;
		}
		
		

}