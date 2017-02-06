package service;

import dao.UserDao;
import model.Utilisateur;

public class AuthenticationService {
	private static AuthenticationService instance;
	private UserDao userDao;

	private AuthenticationService() {
	  userDao = UserDao.getInstance();
	}
	
	public static AuthenticationService getInstance() {
	  synchronized (AuthenticationService.class) {
	    if (instance == null) {
	      instance = new AuthenticationService();
	    }
	  }
	  return instance;
	}
	
	public Utilisateur checkUserPassword(String login, String password) {
	  Utilisateur user = userDao.getUserByLogin(login);
	  if (user == null || !password.equals(user.getPassword())) {
	    user = null;
	  }
	  return user;
	}
	
	public boolean checkUserAdmin(String login) {
	  Utilisateur user = userDao.getUserByLogin(login);
	  return user.isAdmin();
	}
}
