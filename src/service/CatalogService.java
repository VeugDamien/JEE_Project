package service;

import java.awt.List;
import java.util.ArrayList;

import dao.ArticleDao;
import dao.PrixDao;
import dao.RevendeurDao;
import model.Article;
import model.Prix;
import model.Revendeur;
import model.Utilisateur;

public class CatalogService {
	private static CatalogService instance;
	private ArticleDao articleDao;
	private PrixDao prixDao;
	private RevendeurDao revDao;
	
	private CatalogService(){
		articleDao = ArticleDao.getInstance();
		prixDao = PrixDao.getInstance();
		revDao = RevendeurDao.getInstance();
	}
	
	public static CatalogService getInstance(){
		synchronized (CatalogService.class) {
			if(instance == null){
				instance = new CatalogService();
			}
		}
		return instance;
	}
	
	public Article getArticleById(int id){
		Article art = null;
		art = articleDao.getArticleById(id);
		return art;
	}
	
	
	public ArrayList<Article> getListArticleById(int id){
		return (ArrayList<Article>) articleDao.getListArticleById(id);
	}
	
	public ArrayList<Article> getListArticle(){
		return (ArrayList<Article>) articleDao.getListArticle();
	}
	
	public ArrayList<Article> getListFavoris(Utilisateur user){
		return (ArrayList<Article>) articleDao.getListFavoris(user);
	}
	
	public ArrayList<Revendeur> getListRevendeur(){
		return (ArrayList<Revendeur>) revDao.getListRevendeur();
	}
	
	public ArrayList<Prix> getListPriceArticle(Article art){
		return prixDao.getListPrixArticle(art);
	}
	
	public Prix getPrice(Article art, Revendeur rev){
		return prixDao.getPrice(art, rev);
	}
	
	public Revendeur getRevendeurById(int id){
		return revDao.getRevendeurById(id);
	}
}
