package entity;

import java.io.Serializable;
import java.lang.String;
import java.util.List;

import javax.persistence.*;


/**
 * Entity implementation class for Entity: Notification
 *
 */
@Entity
public class Vehicule implements Serializable {

	   
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String type;
	private String modele;
	private String matricule;
	private int poids;
	private boolean panne=false;

	@OneToOne()
	private User user;
	@OneToMany(mappedBy="vehicule")
	private List<Reparation> reparations;	

	public Vehicule() {
		super();
	}   
	public int getId() {
		return this.id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getModele() {
		return modele;
	}
	public void setModele(String modele) {
		this.modele = modele;
	}
	public String getMatricule() {
		return matricule;
	}
	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}
	public int getPoids() {
		return poids;
	}
	public void setPoids(int poids) {
		this.poids = poids;
	}	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public boolean isPanne() {
		return panne;
	}
	public void setPanne(boolean panne) {
		this.panne = panne;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	
}
