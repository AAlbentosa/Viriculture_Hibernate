package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity  //Specifies that this corresponts to a Database table.
@Table(name= "Vid") //Specifies the tributes of the table or de schema if needed
public class Vid {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO) //with this tag we make the id auto_increment
	@Column(name = "id", unique = true, nullable = true) //with this tag we specify the atributes
	private int id;
	
	@Column
	private TipoVid tipo;
	
	@Column
	private int cantidad;
	
	
	public Vid() {
		super();
	}


	public Vid(TipoVid tipo, int cantidad) {
		this.tipo = tipo;
		this.cantidad = cantidad;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public TipoVid getTipo() {
		return tipo;
	}


	public void setTipo(TipoVid tipo) {
		this.tipo = tipo;
	}


	public int getCantidad() {
		return cantidad;
	}


	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}


	@Override
	public String toString() {
		return "Vid [id=" + id + ", tipo=" + tipo + ", cantidad=" + cantidad + "]";
	}
	
	
	
	
}
