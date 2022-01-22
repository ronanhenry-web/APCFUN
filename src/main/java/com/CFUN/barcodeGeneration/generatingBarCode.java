package com.CFUN.barcodeGeneration;

import com.CFUN.app.Arrivee;
import com.CFUN.app.Complexe;
import com.keepautomation.barcode.*;

public class generatingBarCode {

	String CodeBar;
	
	public void GetInfo() {
		Complexe lecomplexe = new Complexe(4, 4, "CFUN");
		Arrivee MonArrivant = new Arrivee(lecomplexe, 'M');
		Arrivee.AjoutArrivant(MonArrivant);
		lecomplexe.entreeUsager(MonArrivant);
		Arrivee MonArrivant2 = Arrivee.GetArrivantByTicket("1");

		String[] Infobillet = MonArrivant.GetBilletInfo();
		String[] parts = Infobillet[2].split("/");
		String[] HourParts = Infobillet[3].split(":");

		// NUMERO DU TICKET pour ecrire sur le barcode
		String CodeBarNumticket = "";
		if (Integer.parseInt(Infobillet[1]) < 10) {
			CodeBarNumticket = "0" + Infobillet[1];
		} else {
			CodeBarNumticket = Infobillet[1];
		}

		// DATE DU TICKET pour ecrire sur le barcode
		int parts2 = Integer.parseInt(parts[2]) - 2000;
		String DateTicket = parts[0] + parts[1] + parts2;

		// HEURE DU TICKET pour ecrire sur le barcode
		String HourTicket = HourParts[0] + HourParts[1];

		// ECRITURE DU BARCODE
		CodeBar = CodeBarNumticket + DateTicket + HourTicket;
		//System.out.println(CodeBar);
		
		// CALCUL DU 13eme DIGIT
		int total = 0;
		int valueCodenumber = 0;
		char[] Codenumber = CodeBar.toCharArray();
		
		for(int i = 0; i < Codenumber.length; i++) {
			//CONVERTISSEUR Codenumber to int
			if(Codenumber[i] == '0') {
				valueCodenumber = 0; }
			if(Codenumber[i] == '1') {
				valueCodenumber = 1; }
			if(Codenumber[i] == '2') {
				valueCodenumber = 2; }
			if(Codenumber[i] == '3') {
				valueCodenumber = 3; }
			if(Codenumber[i] == '4') {
				valueCodenumber = 4; }
			if(Codenumber[i] == '5') {
				valueCodenumber = 5; }
			if(Codenumber[i] == '6') {
				valueCodenumber = 6; }
			if(Codenumber[i] == '7') {
				valueCodenumber = 7; }
			if(Codenumber[i] == '8') {
				valueCodenumber = 8; }
			if(Codenumber[i] == '9') {
				valueCodenumber = 9; }
			
			//CALCUL TOTAL DIGIT-13
			if(i % 2 == 0) {
				// MULTIPLICATEUR PAR 1
				total = total + valueCodenumber;
			} else {
				// MULTIPLICATEUR PAR 3
				total = total + valueCodenumber * 3;
			}
            //System.out.println("NOMBRE n°" + (i+1) + " " + Codenumber[i]);
        }
        //System.out.println("TOTAL = " + total);
        total = total % 10;
        total = 10 - total;
		CodeBar += total;
	}
	

	public void GenerateCode() {

		BarCode barcode = new BarCode();
		barcode.setCodeToEncode(CodeBar);
		barcode.setSymbology(IBarCode.CODE128);
		barcode.setX(2);
		barcode.setY(50);
		barcode.setRightMargin(0);
		barcode.setLeftMargin(0);
		barcode.setTopMargin(0);
		barcode.setBottomMargin(0);
		barcode.setChecksumEnabled(false);
		barcode.setFnc1(IBarCode.FNC1_NONE);
		try {
			barcode.draw("C:\\Users\\pierr\\git\\CFUN_MAVEN\\src\\main\\java\\com\\CFUN\\barcodeGeneration\\BarcodeImages\\barcode_" + CodeBar + ".png");
			//barcode.draw("/home/etudiant/barcode.png");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}