package com.gest;

import javafx.scene.Scene;
import javafx.stage.Stage;

public abstract class UnionClass {
	private static Stage thePrimaryStage;
	private static Scene sc1 = new Scene(new Menu(),980,440);
	

	
	public static void setThePrimaryStage(Stage primaryStage) {
		UnionClass.thePrimaryStage = primaryStage;
		
	}
	public static void swich1(){
		thePrimaryStage.setScene(sc1);
	}
}
