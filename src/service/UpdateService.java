package service;

import dao.ArticleDao;
import dao.PrixDao;
import dao.RevendeurDao;
import model.Article;
import model.Prix;
import model.Revendeur;
import model.Utilisateur;

public class UpdateService {

	private static UpdateService instance;
	private RevendeurDao revDao;
	private ArticleDao artDao;
	private PrixDao prixDao;
	
	private UpdateService(){
		revDao = RevendeurDao.getInstance();
		artDao = ArticleDao.getInstance();
		prixDao = PrixDao.getInstance();
	}
	
	public static UpdateService getInstance(){
		synchronized (UpdateService.class) {
			if(instance == null){
				instance = new UpdateService();
			}
		}
		return instance;
	}
	
	public int insertArticle(Article art){
		return artDao.insertArticle(art);
	}
	
	public int updateArticle(Article art){
		return artDao.updateArticle(art);		
	}
	
	public int deleteArticle(Article art){
		return artDao.deleteArticle(art);
	}
	
	public int insertRevendeur(Revendeur rev){
		return revDao.insertRevendeur(rev);
	}
	
	public int updateRevendeur(Revendeur rev){
		return revDao.updateRevendeur(rev);
	}
	
	public int deleteRevendeur(Revendeur rev){
		return revDao.deleteRevendeur(rev);
	}
	
	public int insertPrice(Prix prix){
		return prixDao.insertPrice(prix);
	}
	
	public int updatePrice(Prix prix){
		return prixDao.updatePrice(prix);
	}
	
	public int deletePrice(Prix prix){
		return prixDao.deletePrice(prix);
	}
	
	public int insertFavori(Article art, Utilisateur user){
		return artDao.insertFavori(art, user);
	}
	
	public int deleteFavori(Article art, Utilisateur user){
		return artDao.deleteFavoris(art, user);
	}
}
