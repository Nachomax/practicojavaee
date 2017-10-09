package Modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="\"Admin\"")
@NamedQuery(name="Admin.findAll", query="SELECT a FROM Admin a")
public class Admin extends User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue 
	private int idAdmin;
	//private int id;
	
	public Admin(String username, Date creationdate, String email, String lastname, String name, String password, int idAdmin){
        super(username, creationdate, email, lastname, name, password);
        this.idAdmin = idAdmin;   
    }
	
	public Admin() {	
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setId(int idAdmin) {
		this.idAdmin = idAdmin;
	}
	
	public int getId() {
		return this.idAdmin;
	}
	
}
