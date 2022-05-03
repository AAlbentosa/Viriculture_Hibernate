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
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity  //Specifies that this corresponts to a Database table.
@Table(name= "Campo") //Specifies the tributes of the table or de schema if needed
public class Campo {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO) //with this tag we make the id auto_increment
	@Column(name = "id", unique = true, nullable = true) //with this tag we specify the atributes
	private int id_campo;
	
	@OneToOne
	@JoinColumn(name = "id_bodega")
	private Bodega id_bodega;
	
	@OneToMany //This especifies that for every instance os this class we hav emany of the other
    @JoinColumn(name = "id_campo")
	private List<Vid> vids;

	public Campo() {}

	public Campo(Bodega id_bodega) {
		this.id_bodega = id_bodega;
		this.vids = new ArrayList<>();
	}

	public int getId_campo() {
		return id_campo;
	}

	public void setId_campo(int id_campo) {
		this.id_campo = id_campo;
	}

	public Bodega getId_bodega() {
		return id_bodega;
	}

	public void setId_bodega(Bodega id_bodega) {
		this.id_bodega = id_bodega;
	}

	public List<Vid> getVids() {
		return vids;
	}

	public void setVids(List<Vid> vids) {
		this.vids = vids;
	}

	@Override
	public String toString() {
		return "Campo [id_campo=" + id_campo + ", id_bodega=" + id_bodega + ", vids=" + vids + "]";
	}
}
