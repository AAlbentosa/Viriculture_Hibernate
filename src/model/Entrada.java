package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity  //Specifies that this corresponts to a Database table.
@Table(name= "entrada") //Specifies the tributes of the table or de schema if needed
public class Entrada {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO) //with this tag we make the id auto_increment
	@Column(name = "id", unique = true, nullable = true) //with this tag we specify the atributes
	private int id;
	
	@Column
	private String instruccion;
	
	public Entrada() {
		super();
	}

	public Entrada(int id, String instruccion) {
		this.id = id;
		this.instruccion = instruccion;
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getInstruccion() {
		return instruccion;
	}

	public void setInstruccion(String instruccion) {
		this.instruccion = instruccion;
	}

	@Override
	public String toString() {
		return instruccion;
	}
	
	
}
