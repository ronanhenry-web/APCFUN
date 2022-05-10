package com.CFUN.CFUNGIT;

public class Equipement {
	String nom, type, qte;
	
	public Equipement(String nom, String type, String qte) {
		super();
		this.nom = nom;
		this.type = type;
		this.qte = qte;
	}
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getQte() {
		return qte;
	}

	public void setQte(String qte) {
		this.qte = qte;
	}
}
