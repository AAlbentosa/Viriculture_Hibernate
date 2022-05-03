package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity  //Specifies that this corresponts to a Database table.
@Table(name= "Bodega") //Specifies the tributes of the table or de schema if needed
public class Bodega {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO) //with this tag we make the id auto_increment
	@Column(name = "id", unique = true, nullable = true) //with this tag we specify the atributes
	private int id_bodega;
	
	@Column
	private String nombre;
	
	@OneToMany //This especifies that for every instance os this class we hav emany of the other
    @JoinColumn(name = "id_bodega")
	private List<Vid> vids;
	
	//Empty constructor is needed
	public Bodega(){}

	public Bodega(String nombre) {
		this.nombre = nombre;
		this.vids = new ArrayList<>();
	}

	public int getId_bodega() {
		return id_bodega;
	}

	public void setId_bodega(int id_bodega) {
		this.id_bodega = id_bodega;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Vid> getVids() {
		return vids;
	}

	public void setVids(List<Vid> vids) {
		this.vids = vids;
	}

	@Override
	public String toString() {
		return "Bodega [id_bodega=" + id_bodega + ", nombre=" + nombre + ", vids=" + vids + "]";
	} 
}
