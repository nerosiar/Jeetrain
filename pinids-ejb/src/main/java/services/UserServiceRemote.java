package services;


import entity.RoleUser;
import entity.User;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface UserServiceRemote {
	String ajouterUser(User user);
	void deleteUserById(Integer userId);
	public User findUser(int id);
	public List<User> getAllUsers();
	public boolean updateUser(User user);
	public User getUserByEmail(String email);
	List<User> findEventByTitle(String title);
	public List<User> getAllUsersInvalid();
	public Integer accepter(Integer id);
	public List<User>  findUserBymanycriteres(String  motcle);
	public List<User>  getUsersByRole(RoleUser role);
	public List<User>  getUsersAvalaible(RoleUser role);

}
