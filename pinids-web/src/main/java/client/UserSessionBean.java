package client;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import controller.SessionUtils;
import entity.RoleUser;
import entity.User;
import services.UserServiceRemote;

@ManagedBean
@SessionScoped
public class UserSessionBean {

	@EJB
	UserServiceRemote serviceUser;
	
	private User addedUser=new User();
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	private String login;
	private String password;
	public User currentUser;
	public String errorLogin = "";

	public User getCurrentUser() {
		return currentUser;
	}

	public String getErrorLogin() {
		return errorLogin;
	}

	public void setErrorLogin(String errorLogin) {
		this.errorLogin = errorLogin;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
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

	public String verifLogin() {

		User u = null;
		try {
			u = serviceUser.getUserByEmail(login);
		} catch (Exception e) {
			// TODO: handle exception
		}
		if (u != null) {
			currentUser = u;
			errorLogin = "";
			if (u.getRole().equals(RoleUser.admin))
				return "admin/index.jsf?faces-redirect=true";
			else
				return "admin/index.jsf?faces-redirect=true";
				
		}
		
		errorLogin = "Email et /ou mdp est incorrect";
		return "login.jsf";
	}
	
	public String addUser(){
		
		addedUser.setEnabled(true);
		addedUser.setDisponible(true);
		addedUser.setPassword(passwordEncoder.encode(addedUser.getPassword()));
		serviceUser.ajouterUser(addedUser);
		addedUser = new User();
		return "list.jsf?faces-redirect=true";
	}
	
	
	public RoleUser[] getRoles(){
		RoleUser [] roles = new RoleUser[2];
		roles[0] = RoleUser.chauffeur;
		roles[1] = RoleUser.mecanicien;
		return roles;
	}

    public String doLogin(){
        User user = serviceUser.getUserByEmail(login);
        if(user !=null){
            if(passwordEncoder.matches(password,user.getPassword())){
                currentUser=user;
                SessionUtils.setCurrentUser(user);
    			errorLogin = "";

                if(user.getRole().equals(RoleUser.admin)){
                    return "/admin/index.jsf?faces-redirect=true";
                }else if(user.getRole().equals(RoleUser.chauffeur)){
                    return "/chauffeur/tache/list.jsf?faces-redirect=true";
                }else {
                    return "/mecanicien/reparation/list.jsf?faces-redirect=true";
                }
            }
        }
        errorLogin = "Mot de passe et/ou email est incorrect";
        return "/login.jsf";
    }
    
    public String doLogOut() {
    	SessionUtils.setCurrentUser(null);
        return "/login.jsf?faces-redirect=true";

    }

	public User getAddedUser() {
		return addedUser;
	}

	public void setAddedUser(User addedUser) {
		this.addedUser = addedUser;
	}

    
}
