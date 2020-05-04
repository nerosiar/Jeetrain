package services;


import entity.Reparation;
import entity.User;
import entity.Vehicule;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface GarageServiceRemote {
	public void addVehicle(Vehicule v);
	public void removeVehicle(Vehicule v);
	public void updateVehicle(Vehicule v);
	public Vehicule getVehicle(int id);
	public List<Vehicule> getAllVehicles();
	public List<Vehicule> getAvailableVehicles();
	public List<Vehicule> getVehiclesByPanne(boolean panne);
	public void addRepartition(Reparation r);
	public void removeReparation(Reparation r);
	public void updateReparation(Reparation r);
	public Reparation getReparation(int id);
	public List<Reparation> getAllReparations();
	public List<Reparation> getRepartitionsByUser(User u);
	
	
}
