package services;


import java.util.ArrayList;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entity.RoleUser;
import entity.User;

@Stateless
@LocalBean
public class UserService implements UserServiceRemote {

	@PersistenceContext(name="nidspi-ejb")
	EntityManager em;

	@Override
	public String ajouterUser(User user) {
		em.persist(user);
		return user.getId().toString();
	}

	@Override
	public void deleteUserById(Integer userId) {
		em.remove(em.find(User.class, userId));

	}

	@Override
	public List<User> findEventByTitle(String title) {
		
		return em.createQuery("select e from User e where e.nom like ?1",User.class).setParameter(1,"%"+title+"%").getResultList();
	}
	@Override
	public List<User> getAllUsers() {
			try{
				TypedQuery<User> query = em.createQuery("Select u from User u ", User.class);		
				return query.getResultList();
			}catch (Exception e) {
				return new ArrayList<>();
			}
			
		}

	@Override
	
	public boolean updateUser(User user) {
		if (em.merge(user)!=null)
			return true;
		else 
			return false;
		
	}
	
	@Override
	public User getUserByEmail(String login) {
		
		TypedQuery<User> query=em.createQuery("select u from User u "+"where u.login=:login ",User.class);
		query.setParameter("login",login);
		User user=null;
		try{
			user=query.getSingleResult();
		}
		catch(NoResultException e ){
			//Logger.getAnonymousLogger("Aucun utilisateur trouve avec cet email:"+login);
		}
		return user;
	}


	@Override
	public List<User> getAllUsersInvalid() {
		TypedQuery<User> query = em.createQuery("Select "
				+ "DISTINCT u from User u "
				+"where u.isValid= 0", User.class);
		
		return query.getResultList();
	}

	@Override
	public Integer accepter(Integer id) {
		Integer q;
		q = em.createQuery("update User u set u.isValid= 1 "
				+ "where u.id='" + id + "'").executeUpdate();

		return q;
	}



	public List<User> findUserBymanycriteres(String  motcle) {
		TypedQuery<User> query = em.createQuery("Select "
				+ "DISTINCT u from User u "
				+"where u.nom LIKE'"+'%'+motcle+'%'+"'OR u.prenom LIKE'"+'%'+motcle+'%'+"'", User.class);
		
		return query.getResultList();
		
	}

	@Override
	public List<User> getUsersByRole(RoleUser role) {
		TypedQuery<User> query = em.createQuery("Select u from User u where u.role=:role", User.class);
		query.setParameter("role",role);
		return query.getResultList();
	}

	@Override
	public List<User> getUsersAvalaible(RoleUser role) {
		TypedQuery<User> query = em.createQuery("Select u from User u where u.role=:role and disponible=1", User.class);
		query.setParameter("role",role);
		return query.getResultList();
	}

	@Override
	public User findUser(int id) {
		return em.find(User.class, id);	
	}

	
}
	
	

