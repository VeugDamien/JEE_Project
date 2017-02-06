package model;

public class Prix {

	private Revendeur revendeur;
	private Article article;
	private float price;
	
	public Revendeur getRevendeur() {
		return revendeur;
	}
	public void setRevendeur(Revendeur revendeur) {
		this.revendeur = revendeur;
	}
	public Article getArticle() {
		return article;
	}
	public void setArticle(Article article) {
		this.article = article;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Revendeur: " + this.revendeur.getName() + " -> " + this.price + "€";
	}

}
