
package entity;


import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.ws.rs.ext.ParamConverter.Lazy;

/**
 * Entity implementation class for Entity: user
 */
@Entity
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nom;
    private String prenom;
    private String email;
    private String login;
    private String password;
	private String phone;
	private String adresse;
	@Enumerated(EnumType.STRING)
	private RoleUser role;
	private Boolean enabled;
	private boolean disponible;
	@OneToOne(mappedBy="user")
	private Vehicule vehicule;
	
	@OneToMany(mappedBy="user")
	private List<Reparation> reparations;	
	
	@OneToMany(mappedBy="user")
	private List<Tache> taches;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public RoleUser getRole() {
		return role;
	}
	public void setRole(RoleUser role) {
		this.role = role;
	}
	public Boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	public boolean isDisponible() {
		return disponible;
	}
	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}
	public Vehicule getVehicule() {
		return vehicule;
	}
	public void setVehicule(Vehicule vehicule) {
		this.vehicule = vehicule;
	}
	public List<Reparation> getReparations() {
		return reparations;
	}
	public void setReparations(List<Reparation> reparations) {
		this.reparations = reparations;
	}
	public List<Tache> getTaches() {
		return taches;
	}
	public void setTaches(List<Tache> taches) {
		this.taches = taches;
	}
	    
}
