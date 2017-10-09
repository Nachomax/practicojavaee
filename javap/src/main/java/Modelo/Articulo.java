package Modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="\"Articulo\"")
@NamedQuery(name="Articulo.findAll", query="SELECT a FROM Articulo a")
public class Articulo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int idArt;
	
	private Date fechaPublicado;
	
	private float precio;
	
	private String nombre;
	
	private String descripcion;
	
	//@ManyToOne
    //@JoinColumn(name="idCategoria") 
	private int idCat;
    
	public Articulo() {	
	}
	
	
	public int getIdArt() {
		return idArt;
	}
	

	public void setIdArt(int idArt) {
		this.idArt = idArt;
	}

	public Date getFechaPublicado() {
		return fechaPublicado;
	}

	public void setFechaPublicado(Date fechaPublicado) {
		this.fechaPublicado = fechaPublicado;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public int getIdCat() {
		return idCat;
	}


	public void setIdCat(int idCat) {
		this.idCat = idCat;
	}
	
	
	
}