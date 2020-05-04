package admin;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.swing.text.AttributeSet.CharacterAttribute;

import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONObject;

import entity.Notif;
import entity.Reparation;
import entity.RoleUser;
import entity.Tache;
import entity.Trajet;
import entity.User;
import entity.Vehicule;
import services.GarageServiceRemote;
import services.ServiceNotifRemote;
import services.UserServiceRemote;
import services.VoyageServiceRemote;

@ManagedBean
@SessionScoped
public class AdminBean {

	
	@EJB
	UserServiceRemote serviceUser;

	@EJB
	GarageServiceRemote garageService;
	
	@EJB
	VoyageServiceRemote voyagegService ;
	
	@EJB
	ServiceNotifRemote serviceNotif;
	
	private String tacheAddError;
	private String reparationAddError;

	private Trajet addedTrajet = new Trajet();
	private User addedUser= new User();
	private Vehicule addedVehicle = new Vehicule();
	private Reparation addedReparation = new Reparation();
	private Tache addedTache = new Tache();
	private int selectedUserId;
	private int selectedVehicleId;
	private int selectedTrajetId;
	private int selectedMecanicienId;
	private User selectedChauffeur;
	private Vehicule selectedVehicule;
	
	public List<Vehicule> getAllVehicles() {
		 return garageService.getAllVehicles();		 
	}
	
	public List<Vehicule> getAvailableVehicles() {
		 return garageService.getAvailableVehicles();		 
	}
	
	public List<Vehicule> getVehiclesByPanne(boolean panne) {
		 return garageService.getVehiclesByPanne(panne);		 
	}
	
	public List<Trajet> getAlLTrajets() {
		 return voyagegService.getAllTrajets();
	}
	
	public List<Tache> getAllTaches() {
		 return voyagegService.getAllTaches();
	}
	
	public List<User> getAllUsersByRole(RoleUser role) {
		 return serviceUser.getUsersByRole(role);
	}
	
	public List<User> getAllChauffeur() {
		 return serviceUser.getUsersByRole(RoleUser.chauffeur);
	}
	
	public List<User> getAllMechanicien() {
		 return serviceUser.getUsersByRole(RoleUser.mecanicien);
	}
	
	public List<User> getAvailableMechanicien() {
		 return serviceUser.getUsersAvalaible(RoleUser.mecanicien);
	}
	
	public List<User> getAllUsers() {
		 return serviceUser.getAllUsers();
	}
	
	public List<User> getAvailableUsers(RoleUser role) {
		 return serviceUser.getUsersAvalaible(role);
	}
	
	public String addVehicle() {
		addedVehicle.setPanne(false);
		garageService.addVehicle(addedVehicle);
		addedVehicle = new Vehicule();
		return "list.jsf?faces-redirect=true";
	}
	
	public String addTrajet() {
		voyagegService.addTrajet(addedTrajet);
		addedTrajet = new Trajet();
		return "list.jsf?faces-redirect=true";
	}
	
	public String addTache() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		try {
			Date d = sdf.parse(addedTache.getDateDebut());
		}catch (Exception e) {
			tacheAddError = "date invalide";
			return "add.jsf?faces-redirect=true";
		}
		addedTache.setUser(serviceUser.findUser(selectedUserId));
		addedTache.setTrajet(voyagegService.getTrajet(selectedTrajetId));
		voyagegService.addTache(addedTache);
		addedTache = new Tache();
		tacheAddError ="";
		return "list.jsf?faces-redirect=true";
	}
	
	
	public String doAssignDriver(User chauffeur) {
		selectedChauffeur = chauffeur;
		return "assign.jsf?faces-redirect=true";
	}
	
	public String doAssign() {		
		Vehicule oldVehicle = selectedChauffeur.getVehicule();
		if(oldVehicle != null) {
			oldVehicle.setUser(null);
			garageService.updateVehicle(oldVehicle);
		}
		Vehicule vehicule = garageService.getVehicle(selectedVehicleId);
		if(vehicule != null) {
			vehicule.setUser(selectedChauffeur);
			garageService.updateVehicle(vehicule);
		}
			
		return "chauffeurs.jsf?faces-redirect=true";
	}
	
	public String doUnassign() {		
		Vehicule oldVehicle = selectedChauffeur.getVehicule();
		if(oldVehicle != null) {
			oldVehicle.setUser(null);
			garageService.updateVehicle(oldVehicle);
		}
		return "chauffeurs.jsf?faces-redirect=true";
	}
	
	public Trajet getAddedTrajet() {
		return addedTrajet;
	}

	public String goReparation(Vehicule v) {
		selectedVehicule = v;
		return "panne.jsf?faces-redirect=true";
	}
	
	public String doReparation() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		try {
			Date d = sdf.parse(addedReparation.getDateDebut());
		}catch (Exception e) {
			reparationAddError = "date invalide";
			return "panne.jsf?faces-redirect=true";
		}	
		
		User mecanicien = serviceUser.findUser(selectedMecanicienId);
		mecanicien.setDisponible(true);
		addedReparation.setUser(mecanicien);
		addedReparation.setVehicule(selectedVehicule);
		if(selectedVehicule == null || mecanicien == null) {
			reparationAddError +=" vehicule et/ou mecanicien invalide";
			return "panne.jsf?faces-redirect=true";
		}
		
		serviceUser.updateUser(mecanicien);
		garageService.addRepartition(addedReparation);
		reparationAddError = "";
		addedReparation = new Reparation();
		return "list.jsf?faces-redirect=true";
	}
	

	public String getCalendarData() {
		JSONArray jArray = new JSONArray();
	    for (Tache t : voyagegService.getAllTaches())
	    {
	    	 String color = "green";
	    	 if(t.isCompleted())
	    		 color = "red";
	         JSONObject studentJSON = new JSONObject();
	         studentJSON.put("title", "tache "+t.getId());
	         studentJSON.put("start", t.getDateDebut());
	         studentJSON.put("color", color);      
	         studentJSON.put("id", t.getId());
	         studentJSON.put("url", "https://www.google.com/maps/dir/"+t.getTrajet().getDepart()+"/"+t.getTrajet().getDestination());

	         jArray.put(studentJSON);
	    }
		System.out.println(jArray.toString());
		return jArray.toString();
	}
	
	public boolean isReparing(int idv) {
		
		for(Reparation r : garageService.getAllReparations()) {
			if(r.getVehicule().getId() == idv && !r.isCompleted())
				return true;
		}
		return false;
	}
	
	
	public List<Notif> getAllNotifs(){
		return serviceNotif.getNotifs();
	}
	
	public String setSeen(Notif notif) {
		serviceNotif.seenNotif(notif.getId());
		String type = notif.getType().toLowerCase();
		if( notif.getType().toLowerCase().equals("tache"))
			return "tache/list.jsf?faces-redirect=true";
		else
			return "reparation/list.jsf?faces-redirect=true";
				
	}
	
	public void setAddedTrajet(Trajet addedTrajet) {
		this.addedTrajet = addedTrajet;
	}

	public User getAddedUser() {
		return addedUser;
	}

	public void setAddedUser(User addedUser) {
		this.addedUser = addedUser;
	}

	public Vehicule getAddedVehicle() {
		return addedVehicle;
	}

	public void setAddedVehicle(Vehicule addedVehicle) {
		this.addedVehicle = addedVehicle;
	}

	public Reparation getAddedReparation() {
		return addedReparation;
	}

	public void setAddedReparation(Reparation addedReparation) {
		this.addedReparation = addedReparation;
	}

	public Tache getAddedTache() {
		return addedTache;
	}

	public void setAddedTache(Tache addedTache) {
		this.addedTache = addedTache;
	}

	public String getTacheAddError() {
		return tacheAddError;
	}

	public int getSelectedUserId() {
		return selectedUserId;
	}

	public void setSelectedUserId(int selectedUserId) {
		this.selectedUserId = selectedUserId;
	}

	public int getSelectedTrajetId() {
		return selectedTrajetId;
	}

	public void setSelectedTrajetId(int selectedTrajetId) {
		this.selectedTrajetId = selectedTrajetId;
	}

	public void setTacheAddError(String tacheAddError) {
		this.tacheAddError = tacheAddError;
	}

	public User getSelectedChauffeur() {
		return selectedChauffeur;
	}

	public void setSelectedChauffeur(User selectedChauffeur) {
		this.selectedChauffeur = selectedChauffeur;
	}

	public int getSelectedVehicleId() {
		return selectedVehicleId;
	}

	public void setSelectedVehicleId(int selectedVehicleId) {
		this.selectedVehicleId = selectedVehicleId;
	}

	public int getSelectedMecanicienId() {
		return selectedMecanicienId;
	}

	public void setSelectedMecanicienId(int selectedMecanicienId) {
		this.selectedMecanicienId = selectedMecanicienId;
	}

	public Vehicule getSelectedVehicule() {
		return selectedVehicule;
	}

	public void setSelectedVehicule(Vehicule selectedVehicule) {
		this.selectedVehicule = selectedVehicule;
	}

	public String getReparationAddError() {
		return reparationAddError;
	}

	public void setReparationAddError(String reparationAddError) {
		this.reparationAddError = reparationAddError;
	}
	
	
}
