package client;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import controller.SessionUtils;
import entity.*;
import services.GarageServiceRemote;
import services.ServiceNotifRemote;
import services.UserServiceRemote;
import services.VoyageServiceRemote;

@ManagedBean
@SessionScoped
public class MecanicienBean {

	@EJB
	UserServiceRemote serviceUser;

	@EJB
	GarageServiceRemote garageService;
	
	@EJB
	VoyageServiceRemote voyagegService ;
	
	@EJB
	ServiceNotifRemote notifRemote;
	
	private Reparation selecedReparation;
	
	
	public List<Reparation> getMyReparation(){
		return garageService.getRepartitionsByUser(SessionUtils.getCurrentUser());
	}

	
	public String goDetailTask(Reparation reparation) {
		selecedReparation = reparation;
		return "detail.jsf?faces-redirect=true";
	}
	
	public String doCompleteTask() {
		selecedReparation.setCompleted(true);
		
		garageService.updateReparation(selecedReparation);
		Vehicule v = selecedReparation.getVehicule();
		v.setPanne(false);
		garageService.updateVehicle(v);
		Notif notif = new Notif();
		notif.setIdUser(SessionUtils.getCurrentUser().getId());
		notif.setIsChecked(0);
		notif.setDate(new Date());
		notif.setType("Reparation");
		notif.setContent("la réparation num "+selecedReparation.getId()+"est terminée");	
		notifRemote.add(notif);
		return "detail.jsf?faces-redirect=true";
	}


	public Reparation getSelecedReparation() {
		return selecedReparation;
	}


	public void setSelecedReparation(Reparation selecedReparation) {
		this.selecedReparation = selecedReparation;
	}

	
}
