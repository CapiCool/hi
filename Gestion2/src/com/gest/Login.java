package com.gest;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class Login extends VBox{
	Label name = new Label( "user_name");
	Label pass = new Label("user_password");
	Button b = null;
	TextField tNom = null;
	PasswordField tPw = null;
	
	public Login() {
		this.getChildren().addAll(name,getTnom(),pass,getTpw(),getB());
		this.setAlignment(Pos.TOP_CENTER);
		this.setPadding(new Insets(40,0,0,0));
		this.setSpacing(15);
		this.setId("login");
	}
	TextField getTnom() {
		if(tNom == null) {
			tNom = new TextField();
//			tNom.setPromptText("Enter your name");
			tNom.setMaxSize(150, 40);
			tNom.setId("tt1");
			
		}
		return tNom;
	}
	PasswordField getTpw() {
		if(tPw == null) {
			tPw = new PasswordField();
//			tPw.setPromptText("Enter your pw");
			tPw.setMaxSize(150, 40);
			tPw.setId("tPw");
		}
		return tPw;
	}
	Button getB() {
		if(b == null) {
			b = new Button("Ok Login");
			b.setOnAction(e->{
				if(getTnom().getText().equals("cool") & getTpw().getText().equals("0000")){
				UnionClass.swich1();
			}
			});
		}
		return b;
	}
	

}
