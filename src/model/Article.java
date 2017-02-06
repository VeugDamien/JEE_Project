package model;

import java.util.List;

public class Article {

	private int id;
	private String name;
	private String description;
	private List<Prix> prix;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<Prix> getPrix() {
		return prix;
	}
	public void setPrix(List<Prix> prix) {
		this.prix = prix;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.name + "\nDescription:" + this.description;
	}
	
	
}
