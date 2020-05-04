package entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * Entity implementation class for Entity: Notification
 *
 */
@Entity
public class Tache implements Serializable {

	   
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String dateDebut;
	private boolean completed=false;
	@ManyToOne()
	private User user;
	@ManyToOne()
	private Trajet trajet;
	
	public Tache() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(String dateDebut) {
		this.dateDebut = dateDebut;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Trajet getTrajet() {
		return trajet;
	}

	public void setTrajet(Trajet trajet) {
		this.trajet = trajet;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}   
	
}
