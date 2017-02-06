package model;

public class Utilisateur {

	private int id;
	private String login;
	private String password;
	private int isAdmin;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	
	public boolean isAdmin() {
		switch (this.isAdmin) {
		case 1:
			return true;
		default:
			return false;
		}
	}
	
	public void setIsAdmin(int isAdmin){
		this.isAdmin = isAdmin;
	}
	
	public void setIsAdmin(Boolean isAdmin) {
		if(isAdmin){
			this.isAdmin = 1;
		} else {
			this.isAdmin = 0;
		}
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return Integer.toString(this.getId()) + '/' + this.getLogin() + '/' + this.getPassword();
	}
}
