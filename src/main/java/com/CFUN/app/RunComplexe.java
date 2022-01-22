package com.CFUN.app;

import com.CFUN.barcodeGeneration.generatingBarCode;

public class RunComplexe {
	private static int nbMuscu = 4;
	private static int nbFit = 5;
	private static final String nomComplexe = "C Fun";
	private static final String TYPE = "Type opération (E)ntrée ou (S)ortie : ";
	private static final String SORTIE = "N° d'entrée à sortir : ";
	private static final String CHOIX = "(M)usculation, (F)itness : ";
	private static final String AUTRE = "Autre opération (O/N) : ";

	public static void main(String[] args) {

		Complexe leComplexe = new Complexe(nbMuscu, nbFit, nomComplexe);

		char repAutre = 'O';
		char repType;
		int repSortie;
		char repChoix;

		generatingBarCode codeBar = new generatingBarCode();

		codeBar.GetInfo();
		codeBar.GenerateCode();
		
		while (repAutre == 'O') {
			repType = Character.toUpperCase(javax.swing.JOptionPane.showInputDialog(TYPE).charAt(0));
			if (repType == 'E') {
				repChoix = Character.toUpperCase(javax.swing.JOptionPane.showInputDialog(CHOIX).charAt(0));
				Arrivee jArrive = new Arrivee(leComplexe, repChoix);
				if (leComplexe.entreeUsager(jArrive)) {
					System.out.println(jArrive.afficheBillet());
				}
			} else {
				repSortie = Integer.parseInt(javax.swing.JOptionPane.showInputDialog(SORTIE));
				System.out.println(leComplexe.sortieUsager(repSortie).afficheTicket());
			}
			System.out.print(leComplexe.lesInfos());
			repAutre = Character.toUpperCase(javax.swing.JOptionPane.showInputDialog(AUTRE).charAt(0));
		}
		System.exit(0);
	}

}
