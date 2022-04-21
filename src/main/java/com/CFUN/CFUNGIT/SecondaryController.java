package com.CFUN.CFUNGIT;

import java.io.IOException;

import com.CFUN.app.Arrivee;
import com.CFUN.app.Complexe;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.time.LocalTime;

public class SecondaryController {
	private char Operation;
	private char typedeSport;

	// FXML
	public TextField valueOfTicket = null;
	public AnchorPane TicketNumberSortie = null;
	public AnchorPane InfoTicketEntree = null;
	public Text ComplexeField = null;
	public Text TicketField = null;
	public Text DateField = null;
	public Text TypeField = null;
	public Text MontantField = null;
	public Text HeureField = null;
	public Text ErreurTicket = null;
	public Text ValideField = null;
	public Text FieldNumber = null;
	public Text FieldCours = null;
	public Text FieldClientPresent = null;
	public Text FieldTicketRestant = null;
	public Text FieldTauxOccupation = null;
	public Text FieldCouleur = null;
	
	public AnchorPane BilletInfoAnchor = null;
	
	Complexe leComplexe = Arrivee.GetComplexe();
	
	@FXML
	public void BackToMainPage() throws IOException {
//		System.out.println("Back");
		App.setRoot("primary");
	}

	@FXML
	public void initialize() {
		Operation = App.getOperation();
		typedeSport = App.gettypeDeSport();
		
		//	Place musculation
		String[] elDoc = Complexe.lesInfos();
		int PlaceRestanteM = Integer.parseInt(elDoc[0]) - 1;
		int ClientPresentM = Integer.parseInt(elDoc[1]) + 1;
		int PercentOccupationM = (ClientPresentM*100) / (PlaceRestanteM+ClientPresentM);
		
		//	Taux d'occupation couleur musculation
		String CouleurM;
		if (PercentOccupationM < 70) {
			CouleurM = "Vert";
		} else if (PercentOccupationM < 100) {
			CouleurM = "Orange";
		} else {
			CouleurM = "Rouge";
		}
		
		//	Place fitness
		int PlaceRestanteF = Integer.parseInt(elDoc[2]) - 1;
		int ClientPresentF = Integer.parseInt(elDoc[3]) + 1;
		int PercentOccupationF = (ClientPresentF*100) / (PlaceRestanteF+ClientPresentF);
		
		//	Taux d'occupation couleur fitness
		String CouleurF;
		if (PercentOccupationF < 70) {
			CouleurF = "Vert";
		} else if (PercentOccupationF < 100) {
			CouleurF = "Orange";
		} else {
			CouleurF = "Rouge";
		}
		
		//	Générer le ticket d'entrée
		if (Operation == 'E') {
			Arrivee newArrivant = new Arrivee(leComplexe, typedeSport);
			Arrivee.AjoutArrivant(newArrivant);
			if (leComplexe.entreeUsager(newArrivant)) {
				ValideField.setVisible(true);
				InfoTicketEntree.setVisible(true);
				if (typedeSport == 'M') {
					FieldCours.setText("Cours de : Musculation");
					FieldClientPresent.setText("Client(s) Présent(s) : " + ClientPresentM);
					FieldTicketRestant.setText("Ticket(s) Restant(s) : " + PlaceRestanteM);
					FieldTauxOccupation.setText("Taux d'occupation : " + PercentOccupationM +"%");
	                FieldCouleur.setText("Disponibilité : " + CouleurM);
	                if (CouleurM == "Vert") {
						FieldCouleur.setStyle("-fx-fill: green; -fx-font-weight: bold;");
					} else if (CouleurM == "Orange") {
						FieldCouleur.setStyle("-fx-fill: orange; -fx-font-weight: bold;");
					} else {
						FieldCouleur.setStyle("-fx-fill: red; -fx-font-weight: bold;");
					}
				} else if (typedeSport == 'F') {
					FieldCours.setText("Cours de : Fitness");
					FieldClientPresent.setText("Client(s) Présent(s) : " + ClientPresentF);
					FieldTicketRestant.setText("Ticket(s) Restant(s) : " + PlaceRestanteF);
					FieldTauxOccupation.setText("Taux d'occupation : " + PercentOccupationF +"%");
	                FieldCouleur.setText("Disponibilité : " + CouleurF);
	                if (CouleurF == "Vert") {
						FieldCouleur.setStyle("-fx-fill: green; -fx-font-weight: bold;");
					} else if (CouleurF == "Orange") {
						FieldCouleur.setStyle("-fx-fill: orange; -fx-font-weight: bold;");
					} else {
						FieldCouleur.setStyle("-fx-fill: red; -fx-font-weight: bold;");
					}	                
				}
				FieldNumber.setText("Numéro : " + Complexe.getNumeroActuel());				
				
			} else {
				//	Plus de place fit ou muscu en entrée
				if (typedeSport == 'M') {
					ValideField.setText("Il n'y a plus de place disponible pour le cours de Musculation. :)");
				} else if (typedeSport == 'F') {
					ValideField.setText("Il n'y a plus de place disponible pour le cours de Fitness. :)");
				}
				ValideField.setStyle("-fx-fill: red; -fx-font-weight: bold;");
				ValideField.setVisible(true);
			}
		} else {
			TicketNumberSortie.setVisible(true);
		}
	}

	//	Sortie ticket
	@FXML
	private void ValidateTicket() {
		int TicketNumber = valueOfTicket.getText().equals("") ? 0 : Integer.parseInt(valueOfTicket.getText());

		if (TicketNumber < 1) {
			//	System.out.println("Vous n'avez pas pris de ticket.");
			ErreurTicket.setVisible(true);
		} else {
			try{
			String[] BilletInfo = Arrivee.GetArrivantByTicket(String.valueOf(TicketNumber)).GetBilletInfoSortie();
			ComplexeField.setText(BilletInfo[0]);
			TicketField.setText(BilletInfo[1]);
			DateField.setText(BilletInfo[2]);
			HeureField.setText(BilletInfo[3]);
			
			/* CALCUL MONTANT */
			
			float montant = (float) ((float) 0);
			String[] splittedA = BilletInfo[3].split(":");
			int hA = Integer.parseInt(splittedA[0])*60;
			int mA = Integer.parseInt(splittedA[1]);
			String heureNow = String.valueOf(LocalTime.now());
			String[] splittedD = heureNow.split(":");
			int hD = Integer.parseInt(splittedD[0])*60;
			int mD = Integer.parseInt(splittedD[1]);
			//	 int difference = 70;
			int difference =  (hD+mD) - (hA+mA);
			
			if(difference <= 15) {
				montant = 0;
			}else if(difference <= 30) {
				montant = (float) 0.5;
			}else if (difference <= 60) {
				montant = 1;					
			} else {
				montant = (float) 1;
				while (difference > 60) {
					montant += 0.5;
					difference -= 15;
				}
			}
			/* CALCUL MONTANT */
			
			MontantField.setText(String.valueOf(montant));
			
			BilletInfoAnchor.setVisible(true);
			//	System.out.println(leComplexe.sortieUsager(TicketNumber));

			}catch (Exception e) {
				//	System.out.println("Erreur sur le ticket ! ");
				ErreurTicket.setVisible(true);
			}
		}
	}
}