package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import configuration.SqlConnection;
import model.Article;
import model.Prix;
import model.Revendeur;

public class PrixDao {
	
	private static PrixDao instance;
	private SqlConnection sqlcon;
	
	private PrixDao(){
		this.sqlcon = SqlConnection.getInstance();
	}
	
	public static PrixDao getInstance(){
		synchronized (PrixDao.class) {
			if(instance == null)
			instance = new PrixDao();
		}
		return instance;
	}
	
	public Prix getPrice(Article art, Revendeur rev){
		ResultSet rs = null;
		Prix p = new Prix();
		
		String query = "SELECT * FROM Prix WHERE idArticle= ? AND idRevendeur= ?;";
		
		try (Connection con = sqlcon.getconnection();
			PreparedStatement prstm = con.prepareStatement(query)){
			
			prstm.setInt(1, art.getId());
			prstm.setInt(2, rev.getId());
			rs = prstm.executeQuery();
			
			if(rs.next()){
				RevendeurDao rd =  RevendeurDao.getInstance();
				
				p.setArticle(art);
				p.setRevendeur(rev);
				p.setPrice(rs.getFloat("price"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			sqlcon.releaseDBResource(rs);
		}
		return p;
	}
	
	public ArrayList<Prix> getListPrixArticle(Article art ){
		
		ArrayList<Prix> myList = new ArrayList<Prix>();
		ResultSet rs = null;
		
		String query = "SELECT * FROM Prix WHERE idArticle= ?;";
		
		try (Connection con = sqlcon.getconnection();
			PreparedStatement prstm = con.prepareStatement(query)){
			
			prstm.setInt(1, art.getId());
			rs = prstm.executeQuery();
			
			while (rs.next()){
				Prix p = new Prix();
				RevendeurDao rd =  RevendeurDao.getInstance();
				
				p.setArticle(art);
				p.setRevendeur(rd.getRevendeurById(rs.getInt("idRevendeur")));
				p.setPrice(rs.getFloat("price"));
				
				myList.add(p);	
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			sqlcon.releaseDBResource(rs);
		}
		return myList;
	}
	
	public int insertPrice (Prix prix){
		String query ="INSERT INTO Prix (idArticle, idRevendeur, price) "
				+ "VALUES (?, ?, ?);";
		int rowCount = 0;
		
		try (Connection con = sqlcon.getconnection();
			PreparedStatement prstm = con.prepareStatement(query)){
			
			prstm.setInt(1, prix.getArticle().getId());
			prstm.setInt(2, prix.getRevendeur().getId());
			prstm.setDouble(3, prix.getPrice());
			rowCount = prstm.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rowCount;
	}
	
	public int updatePrice(Prix prix){
		String query ="UPDATE Prix SET price= ? WHERE idArticle= ? AND idRevendeur= ?;";
		
		int rowCount = 0;
		
		try (Connection con = sqlcon.getconnection();
			PreparedStatement prstm = con.prepareStatement(query)){
			
			prstm.setDouble(1, prix.getPrice());
			prstm.setInt(2, prix.getArticle().getId());
			prstm.setInt(3, prix.getRevendeur().getId());
			rowCount = prstm.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rowCount;
	}

	public int deletePrice(Prix prix) {
		// TODO Auto-generated method stub
		String query ="DELETE FROM Prix WHERE idArticle= ? AND idRevendeur= ?;";
		
		int rowCount = 0;
		
		try (Connection con = sqlcon.getconnection();
			PreparedStatement prstm = con.prepareStatement(query)){
			
			prstm.setInt(1, prix.getArticle().getId());
			prstm.setInt(2, prix.getRevendeur().getId());
			rowCount = prstm.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rowCount;
	}
}
