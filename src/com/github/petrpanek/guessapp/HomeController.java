package com.github.petrpanek.guessapp;

import java.net.URL;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class HomeController extends GridPane implements Initializable {

	@FXML
	private Label spravneOdpovedi;
	@FXML
	private Label spatneOdpovedi;
	@FXML 
	private Label hadejSlovo;
	@FXML
	private HBox radek;
	@FXML
	private Button hraj;

	private String hadej = "";
	private int spravne = 0;
	private int spatne = 0;

	@Override
	public void initialize(URL location, ResourceBundle resources) {		
		spravneOdpovedi.setText(Integer.toString(spravne));
		spatneOdpovedi.setText(Integer.toString(spatne));
		nastavHadani();
		hraj.fire();
		
		VBox prvniSloupec = new VBox();
		VBox druhySloupec = new VBox();
		VBox tretiSloupec = new VBox();

		Button catButton = nastavTlacitko("cat.png", "kočka");
		Button cowButton = nastavTlacitko("cow.png", "kráva");
		Button dogButton = nastavTlacitko("dog.png", "pes");
		Button duckButton = nastavTlacitko("duck.png", "kachna");
		Button mouseButton = nastavTlacitko("mouse.png", "myš");
		Button sheepButton = nastavTlacitko("sheep.png", "ovce");

		prvniSloupec.getChildren().addAll(catButton, cowButton);
		druhySloupec.getChildren().addAll(dogButton, duckButton);
		tretiSloupec.getChildren().addAll(mouseButton, sheepButton);

		radek.getChildren().addAll(prvniSloupec, druhySloupec, tretiSloupec);
	}

	private void nastavHadani() {
		hraj.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Random nahodne = new Random();
				int cislo = nahodne.nextInt(6) + 1;
				
				switch (cislo) {
					case 1:
						hadej = "kočka";
						hadejSlovo.setText("Cat");
						break;
					case 2:
						hadej = "kráva";
						hadejSlovo.setText("Cow");
						break;
					case 3:
						hadej = "pes";
						hadejSlovo.setText("Dog");
						break;
					case 4:
						hadej = "kachna";
						hadejSlovo.setText("Duck");
						break;
					case 5:
						hadej = "myš";
						hadejSlovo.setText("Mouse");
						break;
					case 6:
						hadej = "ovce";
						hadejSlovo.setText("Sheep");
						break;
				}
			}

		});

	}

	private Button nastavTlacitko(String cesta, String jmeno) {
		Image obrazek = new Image(this.getClass().getResourceAsStream("/res/" + cesta), 200, 200, true, false);
		Button tlacitko = new Button("", new ImageView(obrazek));
		tlacitko.setTooltip(new Tooltip(jmeno));
		tlacitko.setUserData(jmeno.toUpperCase());

		tlacitko.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (hadej == jmeno) {
					spravne++;
					spravneOdpovedi.setText(Integer.toString(spravne));

					Alert upozorneni = new Alert(AlertType.INFORMATION);
					upozorneni.setTitle("VÝSLEDEK");
					upozorneni.setHeaderText(null);
					upozorneni.setContentText("SPRÁVNÁ ODPOVĚĎ!");
					upozorneni.showAndWait().ifPresent(response -> {
						if (response == ButtonType.OK) {
							hraj.fire();
						}
					});
				} else {
					spatne++;
					spatneOdpovedi.setText(Integer.toString(spatne));

					Alert upozorneni = new Alert(AlertType.CONFIRMATION);
					upozorneni.setTitle("VÝSLEDEK");
					upozorneni.setHeaderText("ŠPATNÁ ODPOVĚĎ!");
					upozorneni.setContentText("Zkusíš se ještě opravit?");
					
					ButtonType buttonTypeYes = new ButtonType("Ano");
					ButtonType buttonTypeNo = new ButtonType("Ne");
					
					upozorneni.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);
					
					Optional<ButtonType> result = upozorneni.showAndWait();
					if (result.get() == buttonTypeNo) {
						hraj.fire();
					} else {
						upozorneni.close();
					}
				}
			}

		});

		return tlacitko;
	}
}