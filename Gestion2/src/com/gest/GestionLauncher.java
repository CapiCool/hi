package com.gest;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GestionLauncher extends Application{

	VBox root;
	TextField tfUser;
	PasswordField pw;
	Button b;

	@Override
	public void start(Stage win) throws Exception {
		win.setTitle("CAPTIAIN COOLMAN");
		 root = new Login();
		
		Scene sc = new Scene(root,500,400);
		sc.getStylesheets().add(getClass().getResource("/ressource/style.css").toString());
		win.setScene(sc);
		
		UnionClass.setThePrimaryStage(win);
		win.show();
		win.centerOnScreen();
	}

}
