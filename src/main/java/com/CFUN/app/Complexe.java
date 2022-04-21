package com.CFUN.app;

import java.util.ArrayList;
import java.util.List;

public class Complexe {
	private static int numeroActuel = 0;

	public static int getNumeroActuel() {
		return numeroActuel;
	}

	public static void setNumeroActuel() {
		Complexe.numeroActuel = Complexe.getNumeroActuel() + 1;
	}

	private String nomComplexe;

	public String getNomComplexe() {
		return nomComplexe;
	}

	private static int nbTotalPlacesFit;
	private static int nbTotalPlacesMuscu;

	private static int nbPlacesOccupeesFit;
	private static int nbPlacesOccupeesMuscu;

	List<Arrivee> lesArrivees = new ArrayList<Arrivee>();

	public boolean entreeUsager(final Arrivee uneArrivee) {
		boolean ok;
		char choix;

		ok = false;
		choix = uneArrivee.getChoixSport();
		if (choix == 'F') {
			if (Complexe.etatFit() != 1) {
				Complexe.setNumeroActuel();
				uneArrivee.setNumeroArrivee(Complexe.getNumeroActuel());
				lesArrivees.add(uneArrivee);
				this.nouvelUsagerFitness();
				ok = true;
			}
		} else {
			if (Complexe.etatMuscu() != 1.0) {
				Complexe.setNumeroActuel();
				uneArrivee.setNumeroArrivee(Complexe.getNumeroActuel());
				lesArrivees.add(uneArrivee);
				this.nouvelUsagerMusculation();
				ok = true;
			}
		}
		return ok;
	}

	public Arrivee sortieUsager(final int entree) {
		if (Arrivee.GetArrivantByTicket(String.valueOf(entree)).getChoixSport() == 'F') {
			this.oterUsagerFitness();
		} else {
			this.oterUsagerMusculation();
		}
		
		Arrivee.GetArrivantByTicket(String.valueOf(entree));
		Arrivee
				.RemoveArrivant(Arrivee.GetArrivantByTicket(String.valueOf(entree)));

		return Arrivee.GetArrivantByTicket(String.valueOf(entree));
	}

	public Complexe(final int nbTotalPlacesMuscu, final int nbTotalPlacesFit, final String nomComplexe) {
		Complexe.nbTotalPlacesFit = nbTotalPlacesFit;
		Complexe.nbTotalPlacesMuscu = nbTotalPlacesMuscu;
		this.nomComplexe = nomComplexe;
		Complexe.nbPlacesOccupeesFit = 0;
		Complexe.nbPlacesOccupeesMuscu = 0;
	}

	@SuppressWarnings("unused")
	private static String couleurMuscu() {
		ChoixCouleur choixCouleur = new ChoixCouleur(etatMuscu());
		return choixCouleur.getCouleur().toString();
	}

	public static double etatFit() {
		return (getNbPlacesOccupeesFit()) * 1.0 / nbTotalPlacesFit;
	}
	
	public static String[] lesInfos() {
		String[] elDoc = new String[10];
		elDoc[0] = String.valueOf(getNbPlacesRestantesMuscu());
		elDoc[1] = String.valueOf(nbPlacesOccupeesMuscu);
		elDoc[2] = String.valueOf(getNbPlacesRestantesFit());
		elDoc[3] = String.valueOf(nbPlacesOccupeesFit);

		return elDoc;
	}

	public static int getNbPlacesRestantesFit() {
		return nbTotalPlacesFit - (nbPlacesOccupeesFit);
	}

	public static int getNbPlacesOccupeesFit() {
		return Complexe.nbPlacesOccupeesFit;
	}

	public void nouvelUsagerFitness() {
		nbPlacesOccupeesFit++;
	}

	public void oterUsagerFitness() {
		nbPlacesOccupeesFit--;
	}

	public static int getNbPlacesRestantesMuscu() {
		return nbTotalPlacesMuscu - (nbPlacesOccupeesMuscu);
	}

	public static int getNbPlacesOccupeesMuscu() {
		return Complexe.nbPlacesOccupeesMuscu;
	}

	public void nouvelUsagerMusculation() {
		nbPlacesOccupeesMuscu++;
	}

	public void oterUsagerMusculation() {
		nbPlacesOccupeesMuscu--;
	}

	@SuppressWarnings("unused")
	private static String couleurFit() {
		ChoixCouleur choixCouleur = new ChoixCouleur(etatFit());
		return choixCouleur.getCouleur().toString();
	}

	public static double etatMuscu() {
		return (getNbPlacesOccupeesMuscu()) * 1.0 / nbTotalPlacesMuscu;
	}

	@SuppressWarnings("unused")
	private Arrivee recherche(int num) {
		int i = 0;
		Arrivee courant = lesArrivees.get(i);
		while (courant.getNumeroArrivee() != num)
			courant = lesArrivees.get(++i);
		return courant;
	}
}
