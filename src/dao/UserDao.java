package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import configuration.SqlConnection;
import model.Utilisateur;

public class UserDao {

	private static UserDao instance;
	private SqlConnection sqlcon;
	
	private UserDao(){
		this.sqlcon = SqlConnection.getInstance(); 
	}
	
	public static UserDao getInstance(){
		synchronized (UserDao.class) {
			if(instance == null){
				instance = new UserDao();
			}
		}
		return instance;
	}
	
	public Utilisateur getUserByLogin(String login){
		Utilisateur user = null;
		ResultSet rs = null;
		
		String query = "SELECT * FROM User WHERE login = ? ;";
		
		try (Connection con = sqlcon.getconnection();
			PreparedStatement prstm = con.prepareStatement(query)){
			
			prstm.setString(1, login);
			rs = prstm.executeQuery();
			
			if(rs.next()){
				user = new Utilisateur();
				user.setId(rs.getInt("id"));
				user.setLogin(rs.getString("login"));
				user.setPassword(rs.getString("Password"));
				user.setIsAdmin(rs.getInt("isAdmin"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			sqlcon.releaseDBResource(rs);
		}
		return user;
	}
}
