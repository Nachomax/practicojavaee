package Modelo;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="\"Stock\"")
@NamedQuery(name="Stock.findAll", query="SELECT s FROM Stock s")
public class Stock implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int cantidad;
	
	@Id
	@OneToOne
    @JoinColumn(name="idArt") 
	private Articulo idArticulo;
	
	public Stock() {
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Articulo getIdArticulo() {
		return idArticulo;
	}

	public void setIdArticulo(Articulo idArticulo) {
		this.idArticulo = idArticulo;
	}

	
}