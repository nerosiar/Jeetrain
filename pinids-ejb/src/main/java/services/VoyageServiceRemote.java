package services;


import entity.Tache;
import entity.User;
import entity.Trajet;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface VoyageServiceRemote {
	public void addTrajet(Trajet t);
	public void RemoveTrajet(Trajet t);
	public void updateTrajet(Trajet t);
	public Trajet getTrajet(int id);
	public List<Trajet> getAllTrajets();
	
	public void addTache(Tache t);
	public void RemoveTache(Tache t);
	public void updateTache(Tache t);
	public Tache getTache(int id);
	public List<Tache> getAllTaches();
	public List<Tache> getTachesByUser(User u);
	
	
}
