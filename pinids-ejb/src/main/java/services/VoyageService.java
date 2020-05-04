package services;


import entity.Tache;
import entity.User;
import entity.Vehicule;
import entity.Trajet;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Stateless
@LocalBean
public class VoyageService implements VoyageServiceRemote{

	@PersistenceContext(name="nidspi-ejb")
	EntityManager em;
	
	@Override
	public void addTrajet(Trajet t) {
		em.persist(t);
	}

	@Override
	public void RemoveTrajet(Trajet t) {
		em.remove(t);		
	}

	@Override
	public void updateTrajet(Trajet t) {
		em.merge(t);
	}

	@Override
	public Trajet getTrajet(int id) {
		return em.find(Trajet.class, id);
	}

	@Override
	public List<Trajet> getAllTrajets() {
		try {
			return em.createQuery("select t from Trajet t",Trajet.class).getResultList();
		}catch (Exception e) {
			return new ArrayList<>();
		}
	}

	@Override
	public void addTache(Tache t) {
		em.persist(t);
	}

	@Override
	public void RemoveTache(Tache t) {
		em.remove(t);
	}

	@Override
	public void updateTache(Tache t) {
		em.merge(t);
	}

	@Override
	public Tache getTache(int id) {
		return em.find(Tache.class, id);
	}

	@Override
	public List<Tache> getAllTaches() {
		try {
			return em.createQuery("select t from Tache t",Tache.class).getResultList();
		}catch (Exception e) {
			return new ArrayList<>();
		}
	}

	@Override
	public List<Tache> getTachesByUser(User u) {
		try {
			return em.createQuery("select t from Tache t where t.user=:user",Tache.class)
					.setParameter("user", u)
					.getResultList();
		}catch (Exception e) {
			return new ArrayList<>();
		}
	}
	
	
	
}
