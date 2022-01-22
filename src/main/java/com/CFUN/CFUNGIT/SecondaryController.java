package com.CFUN.CFUNGIT;

import java.io.IOException;

import com.CFUN.app.Arrivee;
import com.CFUN.app.Complexe;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class SecondaryController {
	private char Operation;
	private char typedeSport;

	private static final String nomComplexe = "C Fun";
	private static final String TYPE = "Type opération (E)ntrée ou (S)ortie : ";
	private static final String SORTIE = "N° d'entrée à sortir : ";
	private static final String CHOIX = "(M)usculation, (F)itness : ";
	private static final String AUTRE = "Autre opération (O/N) : ";

	// FXML
	public TextField valueOfTicket = null;
	public AnchorPane TicketNumberSortie = null;
	public Text ComplexeField = null;
	public Text TicketField = null;
	public Text DateField = null;
	public Text TypeField = null;
	public Text MontantField = null;
	public Text HeureField = null;
	public Text ErreurTicket = null;
	public Text ValideField = null;
	
	public AnchorPane BilletInfoAnchor = null;
	
	
	Complexe leComplexe = Arrivee.GetComplexe();

	@FXML
	public void BackToMainPage() throws IOException {
		System.out.println("Back");
		App.setRoot("primary");
	}

	@FXML
	public void initialize() {

		Operation = App.getOperation();
		typedeSport = App.gettypeDeSport();

		if (Operation == 'E') {

			Arrivee newArrivant = new Arrivee(leComplexe, typedeSport);
			Arrivee.AjoutArrivant(newArrivant);
			if (leComplexe.entreeUsager(newArrivant)) {
				ValideField.setVisible(true);
			}
		} else {
			TicketNumberSortie.setVisible(true);
		}
		
	}

	@FXML
	private void ValidateTicket() {

		int TicketNumber = valueOfTicket.getText().equals("") ? 0 : Integer.parseInt(valueOfTicket.getText());

		if (TicketNumber < 1) {
			System.out.println("Vous n'avez pas pris de ticket.");
		} else {
			try{
			String[] BilletInfo = Arrivee.GetArrivantByTicket(String.valueOf(TicketNumber)).GetBilletInfoSortie();
			ComplexeField.setText(BilletInfo[0]);
			TicketField.setText(BilletInfo[1]);
			DateField.setText(BilletInfo[2]);
			HeureField.setText(BilletInfo[3]);
			MontantField.setText(BilletInfo[4]);
			BilletInfoAnchor.setVisible(true);
			System.out.println(leComplexe.sortieUsager(TicketNumber));

			}catch (Exception e) {
				// TODO: handle exception
				System.out.println("Erreur sur le ticket ! ");
				ErreurTicket.setVisible(true);
			}
			
			
		}
	}

}