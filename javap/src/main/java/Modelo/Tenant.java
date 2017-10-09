package Modelo;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Tenant
 *
 */
@Entity

public class Tenant implements Serializable {

	
	private String nameunit;   
	@Id
	private int id;
	private String username;
	private String url;
	private boolean usado;
	private static final long serialVersionUID = 1L;

	public Tenant() {
		super();
	}   
	public String getNameunit() {
		return this.nameunit;
	}

	public void setNameunit(String nameunit) {
		this.nameunit = nameunit;
	}   
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}   
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}   
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}   
	public boolean getUsado() {
		return this.usado;
	}

	public void setUsado(boolean usado) {
		this.usado = usado;
	}
   
}
