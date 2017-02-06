package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Util;

import configuration.SqlConnection;
import model.Article;
import model.Utilisateur;

public class ArticleDao {
	
	private static ArticleDao instance;
	private SqlConnection sqlcon;
	
	private ArticleDao(){
		this.sqlcon = SqlConnection.getInstance();
	}
	
	public static ArticleDao getInstance(){
		synchronized (ArticleDao.class) {
			if(instance == null){
				instance = new ArticleDao();
			}
		}
		return instance;
	}
	
	public Article getArticleById(int id){
		Article art = null;
		ResultSet rs = null;
		
		String query = "SELECT * FROM Article WHERE id = ?;";
		
		try(Connection con = sqlcon.getconnection();
			PreparedStatement prstm = con.prepareStatement(query)){
			
			prstm.setInt(1, id);
			rs = prstm.executeQuery();
			
			if(rs.next()){
				art = new Article();
				art.setId(rs.getInt("id"));
				art.setName(rs.getString("name"));
				art.setDescription(rs.getString("Description"));				
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			sqlcon.releaseDBResource(rs);
		}
		return art;
	}
	
	public List<Article> getListArticleById(int id){
		
		List<Article> myList = new ArrayList<Article>();
		ResultSet rs = null;
				
		String query = "SELECT * FROM Article WHERE id LIKE ?;";
		
		try (Connection con = sqlcon.getconnection();
				PreparedStatement prstm = con.prepareStatement(query)){
			
			prstm.setString(1, "%" + id + "%");
			rs = prstm.executeQuery();
			
			while (rs.next()){
				Article art = new Article();
				
				art.setId(rs.getInt("id"));
				art.setName(rs.getString("name"));
				art.setDescription(rs.getString("description"));
				
				myList.add(art);	
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			sqlcon.releaseDBResource(rs);
		}
		return myList;
	}
	
	public List<Article> getListArticle(){
		List<Article> myList = new ArrayList<Article>();
		ResultSet rs = null;
				
		String query = "SELECT * FROM Article;";
		
		try (Connection con = sqlcon.getconnection();
				PreparedStatement prstm = con.prepareStatement(query)){
			
			rs = prstm.executeQuery();
			
			while (rs.next()){
				Article art = new Article();
				
				art.setId(rs.getInt("id"));
				art.setName(rs.getString("name"));
				art.setDescription(rs.getString("description"));
				
				myList.add(art);	
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			sqlcon.releaseDBResource(rs);
		}
		return myList;
	}
	
	public List<Article> getListFavoris(Utilisateur user) {
		// TODO Auto-generated method stub
		List<Article> myList = new ArrayList<Article>();
		ResultSet rs = null;
				
		String query = "SELECT * FROM Favoris WHERE idUser =?;";
		
		try (Connection con = sqlcon.getconnection();
				PreparedStatement prstm = con.prepareStatement(query)){
			
			prstm.setInt(1, user.getId());
			rs = prstm.executeQuery();
			
			while (rs.next()){
				Article art = new Article();
				art = getArticleById(rs.getInt("idArticle"));
				myList.add(art);	
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			sqlcon.releaseDBResource(rs);
		}
		return myList;
	}
	
	public int insertArticle (Article art){
		String query ="INSERT INTO Article (name, description) "
				+ "VALUES (?,?);";
		int rowCount = 0;
		
		try (Connection con = sqlcon.getconnection();
			PreparedStatement prstm = con.prepareStatement(query)){
			
			prstm.setString(1, art.getName());
			prstm.setString(2, art.getDescription());
			rowCount = prstm.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rowCount;
	}
	
	public int updateArticle(Article art){
		String query ="UPDATE Article SET name= ?, description= ? WHERE id= ?;";
		
		int rowCount = 0;
		
		try (Connection con = sqlcon.getconnection();
			PreparedStatement prstm = con.prepareStatement(query)){
			
			prstm.setString(1, art.getName());
			prstm.setString(2, art.getDescription());
			prstm.setInt(3, art.getId());
			rowCount = prstm.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rowCount;
	}

	public int deleteArticle(Article art) {
		// TODO Auto-generated method stub
		String query ="DELETE FROM Article WHERE id= ?;";
		
		int rowCount = 0;
		
		try (Connection con = sqlcon.getconnection();
			PreparedStatement prstm = con.prepareStatement(query)){
			
			prstm.setInt(1, art.getId());
			rowCount = prstm.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rowCount;
	}
	
	public int insertFavori(Article art, Utilisateur user){
		String query ="INSERT INTO Favoris (idArticle, idUser) "
				+ "VALUES (?,?);";
		int rowCount = 0;
		
		try (Connection con = sqlcon.getconnection();
			PreparedStatement prstm = con.prepareStatement(query)){
			
			prstm.setInt(1, art.getId());
			prstm.setInt(2, user.getId());
			rowCount = prstm.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rowCount;
	}
	
	public int deleteFavoris (Article art, Utilisateur user){
		String query ="DELETE FROM Favoris WHERE idArticle= ? AND idUser= ?;";
		
		int rowCount = 0;
		
		try (Connection con = sqlcon.getconnection();
			PreparedStatement prstm = con.prepareStatement(query)){
			
			prstm.setInt(1, art.getId());
			prstm.setInt(2, user.getId());
			rowCount = prstm.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rowCount;
	}
}