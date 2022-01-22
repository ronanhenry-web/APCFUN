package com.CFUN.CFUNGIT;

import static org.junit.Assert.*;

import org.junit.Test;

import com.CFUN.app.Arrivee;
import com.CFUN.app.Complexe;

public class TestMonetaire {

	@Test
	public void test() {
		Complexe newComplexe = new Complexe(2, 4, "JimmyComplex");
		Arrivee newArrivant = new Arrivee(newComplexe, 'F');

		newArrivant.addTime(12);
		assertEquals("Erreur sur le prix ", 0, newArrivant.getMontant(), 0.001);
		newArrivant.clearTime();
		newArrivant.addTime(32);
		assertEquals("Erreur sur le prix ", 1.0, newArrivant.getMontant(), 0.001);
		newArrivant.clearTime();
		newArrivant.addTime(75);
		assertEquals("Erreur sur le prix ", 1.5, newArrivant.getMontant(), 0.001);
		newArrivant.clearTime();
		newArrivant.addTime(80);
		assertEquals("Erreur sur le prix ", 2.0, newArrivant.getMontant(), 0.001);

	}

}
