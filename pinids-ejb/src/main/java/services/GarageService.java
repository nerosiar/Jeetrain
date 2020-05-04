package services;


import entity.Reparation;
import entity.User;
import entity.Vehicule;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@LocalBean
public class GarageService implements GarageServiceRemote {

	@PersistenceContext(name="nidspi-ejb")
	EntityManager em;
	
	@Override
	public void addVehicle(Vehicule v) {
		em.persist(v);		
	}

	@Override
	public void removeVehicle(Vehicule v) {
		em.remove(v);
	}

	@Override
	public void updateVehicle(Vehicule v) {
		em.merge(v);
	}

	@Override
	public Vehicule getVehicle(int id) {
		return em.find(Vehicule.class,id);
	}

	@Override
	public List<Vehicule> getAllVehicles() {
		try {
			return em.createQuery("select v from Vehicule v",Vehicule.class).getResultList();
		}catch (Exception e) {
			return new ArrayList<>();
		}
	}

	@Override
	public List<Vehicule> getVehiclesByPanne(boolean panne) {
		try {
			return em.createQuery("select v from Vehicule v where v.panne=:panne",Vehicule.class)
					.setParameter("pane", panne)
					.getResultList();
		}catch (Exception e) {
			return new ArrayList<>();
		}
	}

	@Override
	public void addRepartition(Reparation r) {
		em.persist(r);		
	}

	@Override
	public void removeReparation(Reparation r) {
		em.remove(r);
	}

	@Override
	public void updateReparation(Reparation r) {
		em.merge(r);
		
	}

	@Override
	public Reparation getReparation(int id) {
		
		return em.find(Reparation.class, id);
	}

	@Override
	public List<Reparation> getAllReparations() {
		try {
			return em.createQuery("select r from Reparation r",Reparation.class).getResultList();
		}catch (Exception e) {
			return new ArrayList<>();
		}
	}

	@Override
	public List<Reparation> getRepartitionsByUser(User user) {
		try {
			return em.createQuery("select r from Reparation r where r.user=:user",Reparation.class)
					.setParameter("user",user)
					.getResultList();
		}catch (Exception e) {
			return new ArrayList<>();
		}
	}

	@Override
	public List<Vehicule> getAvailableVehicles() {
		try {
			return em.createQuery("select v from Vehicule v where v.user=NULL",Vehicule.class)
					.getResultList();
		}catch (Exception e) {
			return new ArrayList<>();
		}
	}
	
	
	
}
