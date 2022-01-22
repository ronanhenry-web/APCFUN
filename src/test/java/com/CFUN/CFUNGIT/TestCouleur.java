package com.CFUN.CFUNGIT;

import static org.junit.Assert.*;

import org.junit.Test;

import com.CFUN.app.ChoixCouleur;
import com.CFUN.app.Couleur;

public class TestCouleur {

	@Test
	public void testGetCouleur() {
		//Test Couleur Vert
		ChoixCouleur getDispoVert = new ChoixCouleur(0.6);
		Couleur couleurToTestVert = Couleur.vert;
		assertEquals("Erreur sur la couleur " , getDispoVert.getCouleur(), couleurToTestVert);
		
		//Test Couleur Rouge
		ChoixCouleur getDispoRouge = new ChoixCouleur(1);
		Couleur couleurToTestRouge = Couleur.rouge;
		assertEquals("Erreur sur la couleur " , getDispoRouge.getCouleur(), couleurToTestRouge);
		
		// Test Couleur Rouge
		ChoixCouleur getDispoOrange = new ChoixCouleur(0.8);
		Couleur couleurToTestOrange = Couleur.orange;
		assertEquals("Erreur sur la couleur " , getDispoOrange.getCouleur(), couleurToTestOrange);
				
	}

}
