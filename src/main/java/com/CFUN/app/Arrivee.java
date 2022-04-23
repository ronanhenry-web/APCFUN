package com.CFUN.app;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Arrivee {
	private static int numeroSortie = 0;
	private int numeroArrivee;
	String[] BilletInfo;
	static List<Arrivee> ListArrivant = new ArrayList<>();

	private static int nbMuscu = 4;
	private static int nbFit = 5;
	private static final String nomComplexe = "CFUN";

	static Complexe leComplexe = new Complexe(nbMuscu, nbFit, nomComplexe);

	public static Complexe GetComplexe() {
		return leComplexe;
	}

	public int getNumeroArrivee() {
		return numeroArrivee;
	}

	private char choixSport;

	public char getChoixSport() {
		return choixSport;
	}

	private long horaireArrivee;
	private Calendar hDep;

	private Complexe complexe;

	// Constructeur Arrivee
	public Arrivee(final Complexe complexe, final char choixSport) {
		this.horaireArrivee = Calendar.getInstance().getTimeInMillis();
		this.choixSport = choixSport;
		this.complexe = complexe;
		this.hDep = null;
	}
	
	public String[] GetBilletInfo() {
		// Gestion de la date et de l'heure
		Calendar leCal = Calendar.getInstance();
		leCal.setTimeInMillis(this.horaireArrivee);

		Date laDate = leCal.getTime();

		SimpleDateFormat leJour = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat lHeure = new SimpleDateFormat("HH:mm");

		String[] BilletInfo = { this.getComplexe().getNomComplexe(), String.valueOf(this.numeroArrivee),
				leJour.format(laDate), lHeure.format(laDate) };
		return BilletInfo;
	}

	public String[] GetBilletInfoSortie() {
		// Gestion de la date et de l'heure
		Calendar leCal = Calendar.getInstance();
		leCal.setTimeInMillis(this.horaireArrivee);

		Date laDate = leCal.getTime();

		SimpleDateFormat leJour = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat lHeure = new SimpleDateFormat("HH:mm");

		String[] BilletInfo = { this.getComplexe().getNomComplexe(), String.valueOf(this.numeroArrivee),
				leJour.format(laDate), lHeure.format(laDate),String.valueOf(this.getMontant()) };
		return BilletInfo;
	}

	// Test unitaire Monétaire
	public String afficheTicket() {
		final String MSGNOM = "Complexe ";
		final String MSGNUM = "Ticket de sortie n° : ";
		final String MSGDATE = "Date : ";
		final String MSGHEURE = "Heure : ";
		final String MSGCOUT = "Montant : ";

		String leTicket;

		leTicket = MSGNOM + this.getComplexe().getNomComplexe() + "\t";
		leTicket += MSGNUM + ++Arrivee.numeroSortie + "\n";

		this.hDep = Calendar.getInstance();

		Date laDate = hDep.getTime();
		SimpleDateFormat leJour = new SimpleDateFormat("dd/MM/yyyy");
		leTicket += MSGDATE + leJour.format(laDate) + "\n";
		SimpleDateFormat lHeure = new SimpleDateFormat("HH:mm");
		leTicket += MSGHEURE + lHeure.format(laDate) + "\n";
		leTicket += MSGCOUT + this.getMontant() + " €\n";

		return leTicket;
	}

	// Test unitaire Monétaire
	public void addTime(int additionalTime) {
		afficheTicket();
		hDep.add(Calendar.MINUTE, +additionalTime);
	}

	// Test unitaire Monétaire
	public void clearTime() {
		hDep = Calendar.getInstance();
	}

	// Gestion des arrivants
	public static Arrivee GetArrivantByTicket(String NumeroTicket) {
		for (int i = 0; i < ListArrivant.size(); i++) {
			if (ListArrivant.get(i).GetBilletInfo()[1].equals(NumeroTicket)) {
				return ListArrivant.get(i);
			}
		}
		return null;
	}

	// ADD un arrivant grâce à une entrée
	public static void AjoutArrivant(Arrivee Arrivant) {
		ListArrivant.add(Arrivant);
	}
	
	// REMOVE une ligne (qu'on sélectionne) grâce à une sortie
	public static void RemoveArrivant(Arrivee Arrivant) {
		ListArrivant.remove(Arrivant);		
	}
	
	// Test unitaire Monétaire
	public double getMontant() {
		double cout = 0;

		if (hDep != null) {
			long dep = hDep.getTimeInMillis() / (1000 * 60);
			long arr = this.horaireArrivee / (1000 * 60);
			long duree = dep - arr;

			if (duree <= 30 && duree > 15) {
				cout = 0.5;

			} else if (duree <= 15) {
				cout = 0;
			} else if (duree < 60) {
					cout = 1;
			} else {
				// cout fixe d'une heure
				cout = 1;
				duree -= 60;
				// + tous les 1/4 h commencés
				long nbquarts, reste;
				nbquarts = duree / 15;
				reste = duree % 15;
				if (reste != 0)
					nbquarts++;
				cout += nbquarts * 0.5;
			}

		}
		return cout;
	}

	// Récupération des infos de Complexe
	public Complexe getComplexe() {
		return this.complexe;
	}
	
	// On initie le numéro d'une entrée
	public void setNumeroArrivee(int numero) {
		numeroArrivee = numero;
	}
}
