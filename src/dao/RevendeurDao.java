package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import configuration.SqlConnection;
import model.Revendeur;

public class RevendeurDao {
	
	private static RevendeurDao instance;
	private SqlConnection sqlcon;
	
	private RevendeurDao(){
		this.sqlcon = SqlConnection.getInstance();
	}
	
	public static RevendeurDao getInstance(){
		synchronized (RevendeurDao.class) {
			if(instance == null){
				instance = new RevendeurDao();
			}
		}
		return instance;
	}
	
	public Revendeur getRevendeurById (int id_revendeur){
		Revendeur rev = null;
		ResultSet rs = null;
		
		String query = "SELECT * FROM Revendeur WHERE id = ? ;";

		try (Connection con = sqlcon.getconnection();
			PreparedStatement prstm = con.prepareStatement(query)){
			
			prstm.setInt(1, id_revendeur);
			rs = prstm.executeQuery();
			
			if(rs.next()){
				rev = new Revendeur();
				rev.setId(rs.getInt("id"));
				rev.setName(rs.getString("name"));
				rev.setDescription(rs.getString("description"));
				rev.setLattitude(rs.getInt("lattitude"));
				rev.setLongitude(rs.getInt("longitude"));	
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			sqlcon.releaseDBResource(rs);
		}
		return rev;	
	}
	
	public List<Revendeur> getListRevendeur(){
		List<Revendeur> myList = new ArrayList<Revendeur>();
		ResultSet rs = null;
				
		String query = "SELECT * FROM Revendeur;";
		
		try (Connection con = sqlcon.getconnection();
				PreparedStatement prstm = con.prepareStatement(query)){
			
			rs = prstm.executeQuery();
			
			while (rs.next()){
				Revendeur rev = new Revendeur();
				
				rev.setId(rs.getInt("id"));
				rev.setName(rs.getString("name"));
				rev.setDescription(rs.getString("description"));
				
				myList.add(rev);	
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			sqlcon.releaseDBResource(rs);
		}
		return myList;
	}
	
	public boolean isExist(String name, int x, int y){
		boolean exist = false;
		ResultSet rs = null;
		
		String query = "SELECT * FROM Revendeur WHERE name = ? AND lattitude = ? AND longitude = ?;";
		
		try (Connection con = sqlcon.getconnection();
			PreparedStatement prstm = con.prepareStatement(query)){
			
			prstm.setString(1, name);
			prstm.setInt(2, x);
			prstm.setInt(3, y);
			rs = prstm.executeQuery();
			
			if(rs != null){
				exist = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			sqlcon.releaseDBResource(rs);
		}
		return exist;
	}
	
	public int insertRevendeur(Revendeur rev){
		String query ="INSERT INTO Revendeur (name, description, lattitude, longitude) "
				+ "VALUES (?,?,?,?);";
		int rowCount = 0;
		
		try (Connection con = sqlcon.getconnection();
			PreparedStatement prstm = con.prepareStatement(query)){
			
			prstm.setString(1, rev.getName());
			prstm.setString(2, rev.getDescription());
			prstm.setInt(3, rev.getLattitude());
			prstm.setInt(4, rev.getLongitude());
			rowCount = prstm.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rowCount;
	}
	
	public int updateRevendeur(Revendeur rev){
		String query ="UPDATE Revendeur SET name= ?, description= ?, lattitude= ?," +
						"longitude= ? WHERE id= ?;";
		
		int rowCount = 0;
		
		try (Connection con = sqlcon.getconnection();
			PreparedStatement prstm = con.prepareStatement(query)){
			
			prstm.setString(1, rev.getName());
			prstm.setString(2, rev.getDescription());
			prstm.setInt(3, rev.getLattitude());
			prstm.setInt(4, rev.getLongitude());
			prstm.setInt(5, rev.getId());
			rowCount = prstm.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rowCount;	
	}

	public int deleteRevendeur(Revendeur rev) {
		// TODO Auto-generated method stub
		String query ="DELETE FROM Revendeur WHERE id= ?;";

		int rowCount = 0;
		
		try (Connection con = sqlcon.getconnection();
			PreparedStatement prstm = con.prepareStatement(query)){
			
			prstm.setInt(1, rev.getId());
			rowCount = prstm.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rowCount;
	}
}
