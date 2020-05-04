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
public class ChauffeurBean {

	@EJB
	UserServiceRemote serviceUser;

	@EJB
	GarageServiceRemote garageService;
	
	@EJB
	VoyageServiceRemote voyagegService ;
	
	@EJB
	ServiceNotifRemote notifRemote;
	
	private Tache selectedTask;
	
	public List<Tache> getMyTasks(){
		return voyagegService.getTachesByUser(SessionUtils.getCurrentUser());
	}
	
	public String goDetailTask(Tache tache) {
		selectedTask = tache;
		return "detail.jsf?faces-redirect=true";
	}
	
	public String doCompleteTask() {
		selectedTask.setCompleted(true);
		
		voyagegService.updateTache(selectedTask);
		
		Notif notif = new Notif();
		notif.setIdUser(SessionUtils.getCurrentUser().getId());
		notif.setIsChecked(0);
		notif.setDate(new Date());
		notif.setType("Tache");
		notif.setContent("la tache num "+selectedTask.getId()+"est terminée");	
		notifRemote.add(notif);
		return "detail.jsf?faces-redirect=true";
	}

	public Tache getSelectedTask() {
		return selectedTask;
	}

	public void setSelectedTask(Tache selectedTask) {
		this.selectedTask = selectedTask;
	}

}
