package Modelo;

import java.io.Serializable;
import java.util.Date;
//import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="\"Cliente\"")
@NamedQuery(name="Cliente.findAll", query="SELECT c FROM Cliente c")
public class Cliente extends User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue 
	private int idCliente;
	
	//private List<String> historicoCompras;
	
	private String direccion;
	
	private int codigoPostal;
	
	public Cliente(String username, Date creationdate, String email, String lastname, String name, String password, int idCliente, String direccion, int codigoPostal){
        super(username, creationdate, email, lastname, name, password);
        this.idCliente = idCliente;   
        //this.historicoCompras = historicoCompras;
        this.direccion = direccion;
        this.codigoPostal = codigoPostal;
    }
	
	public Cliente() {	
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
/*
	public List<String> getHistoricoCompras() {
		return historicoCompras;
	}

	public void setHistoricoCompras(List<String> historicoCompras) {
		this.historicoCompras = historicoCompras;
	}
*/
	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(int codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
	
	
}