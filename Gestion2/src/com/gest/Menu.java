package com.gest;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Menu  extends VBox{
	public Menu() {
		this.getChildren().addAll(new Anc(), new BordP());
		this.setPadding(new Insets(10));
//		this.setPrefSize(990, 430);
		this.computeAreaInScreen();
	}
}

 class Anc extends AnchorPane {
	Label l = new Label("COOL_KMNIK");
	TextField tf = new TextField();
	Button serch = new Button("Search");

	public Anc() {
		this.getChildren().addAll(l, tf, serch);
		AnchorPane.setTopAnchor(l, 15.0);
		AnchorPane.setLeftAnchor(l, 10.0);
		AnchorPane.setTopAnchor(tf, 10.0);
		AnchorPane.setRightAnchor(tf, 55.0);
		AnchorPane.setTopAnchor(serch, 10.0);
		AnchorPane.setRightAnchor(serch, 10.0);
	}
}


 class BordP extends BorderPane {
	static PreparedStatement pst = null;
	static ResultSet rs = null;
	static Tab tab = null;
	static ObservableList<Products> data = null;
	public BordP() {
		this.setTop(new MenuChaine());
		this.setLeft(new VbInfoGauche());
		this.setCenter(getTab());
		this.setBottom(new CopyR());
	}
	
	static Tab getTab() {
		if(tab == null) {
			tab = new Tab(getData());
			BorderPane.setMargin(tab, new Insets(20, 0,0,15));
			tab.setOnMouseClicked(e->{
				Products p = BordP.getTab().getItems().get(BordP.getTab().getSelectionModel().getSelectedIndex());
				VbInfoGauche.getBarcod().setText(String.valueOf(p.getBarcode()));
				VbInfoGauche.getName().setText(p.getName());
				VbInfoGauche.getUnitPri().setText(p.getUnitPrice());
				VbInfoGauche.getDesc().setText(p.getDescription());
			});
			
		
		}
		return tab;
	}
	static ObservableList<Products> getData(){
		if(data == null) {
			data = FXCollections.observableArrayList();
			try {
				pst = dbCon.getCon().prepareStatement("Select * from products");
				 rs = pst.executeQuery();
				 while(rs.next()) {
				 data.addAll(new Products(rs.getInt(1),rs.getString(2), rs.getString(3),rs.getString(4)));
				 }
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return data;
	}
	
	
}

 class MenuChaine extends HBox {
	Button pro = new Button("Product");
	Button ven = null;
	Button alert = new Button("Alert");
	Button account = new Button("Account");
	Button report = new Button("Report");

	public MenuChaine() {
		this.getChildren().addAll(pro, getVente(), alert, account, report);
		this.setAlignment(Pos.CENTER);
		this.setSpacing(10);
		this.setPadding(new Insets(18,0,0,0));
	}
	Button getVente() {
		if(ven == null) {
			ven = new Button("Vente");
		
		}
		return ven;
	}
}

  class VbInfoGauche extends VBox {
	static TextField barc = null;
	static TextField name = null;
	static TextField unitPri= null;
	static TextField desc = null;
	static TextField search = null;
	static PreparedStatement pst = null;
	private static ResultSet rs;


	public VbInfoGauche() {
		this.getChildren().addAll(getBarcod(), getName(), getUnitPri(), getDesc(),getSearch(),new Action());
		this.setSpacing(16);
		this.setPadding(new Insets(20,0,0,0));
	}
	
	static TextField getBarcod() {
		if(barc == null) {
			barc = new TextField();
			barc.setPromptText("Barcode");
		}
		return barc;
	}
	static TextField getName() {
		if(name == null) {
			name = new TextField();
			name.setPromptText("Name");
		}
		return name;
	}	
	static TextField getUnitPri() {
		if(unitPri == null) {
			unitPri = new TextField();
			unitPri.setPromptText("Unit_Price");
		}
		return unitPri;
	}	
	static TextField getDesc() {
		if(desc == null) {
			desc = new TextField();
			desc.setPromptText("Description");
		}
		return desc;
	}
	static TextField getSearch() {
		if(search == null) {
			search = new TextField();
			search.setPromptText("Search");
		}
			search.setOnKeyReleased(e->{
				if(search.getText().equals("")) {
					BordP.getTab().getItems().clear();
					Action.loadDataFromDataBase();
				}else {
				try {
					int barc = Integer.parseInt(search.getText());
					String sql = "Select * from products where barcode LIKE '%"+barc+"%'";
					BordP.getData().clear();
					pst = dbCon.getCon().prepareStatement(sql);
					rs = pst.executeQuery();
					while(rs.next()) {
					BordP.getData().add(new Products(rs.getInt(1),rs.getString(2), rs.getString(3),rs.getString(4)));
					}
					BordP.getTab().setItems(BordP.getData());
					
				}catch(SQLException ex){
					ex.getMessage();
				}
				}
			});
		return search;
	}
}
 class Action extends HBox{
	static PreparedStatement pst = null;
	private static ResultSet rs;
	Button sav = null;
	Button upDate = null;
	Button del = null;

	public Action() {
		this.getChildren().addAll(getSave(), getUpDate(), getDel());
		this.setSpacing(5);
		this.setPadding(new Insets(30,0,0,0));
	}
	
	Button getSave() {
		if(sav == null) {
			sav = new Button("SAVE");
			sav.setOnAction(e->{
				int barcode = Integer.valueOf(VbInfoGauche.getBarcod().getText());
				String name = VbInfoGauche.getName().getText();
				String unitp = VbInfoGauche.getUnitPri().getText();
				String descri = VbInfoGauche.getDesc().getText();
				
				String sql = "Insert into products(barcode, name, unitP, descri) values(?,?,?,?) ";
				try {
					pst = dbCon.getCon().prepareStatement(sql);
					pst.setInt(1,barcode);
					pst.setString(2,name);
					pst.setString(3,unitp);
					pst.setString(4,descri);
					int i = pst.executeUpdate();
					if(i == 1) {
						System.out.println("Ok inserrer");
						BordP.getTab().getItems().clear();
						loadDataFromDataBase();
						clearTextField();
					}
				}catch(SQLException ex) {
					ex.getMessage();
				}finally{
					try {
						pst.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
		}
		return sav;
	}
	Button getUpDate(){
		if(upDate == null) {
			upDate = new Button("UPDATE");
			upDate.setOnAction(e->{
				int barcode = Integer.valueOf(VbInfoGauche.getBarcod().getText());
				String name = VbInfoGauche.getName().getText();
				String unitp = VbInfoGauche.getUnitPri().getText();
				String descri = VbInfoGauche.getDesc().getText();
				
				String sql = "Update products set  name = ?, unitp = ?, descri = ? where barcode = ?";
				try {
			
				pst = dbCon.getCon().prepareStatement(sql);
			    pst.setString(1, name);
				pst.setString(2, unitp);
				pst.setString(3, descri);
				pst.setInt(4, barcode);
				int i = pst.executeUpdate();
				if(i == 1) {
					System.out.println("Ok update");
					BordP.getTab().getItems().clear();
					loadDataFromDataBase();
					clearTextField();
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.getMessage();
			}
			

			});
	}
		
		return upDate;
	}
	Button getDel() {
		if(del == null) {
			del = new Button("DELETE");
			del.setOnAction(e->{
			BordP.getTab().getItems().remove(BordP.getTab().getSelectionModel().getSelectedItem()); // il faut ceci pour avoir un impact directe sur le tableau
				String sql =" delete from products where barcode = ?";
				int barcode = Integer.valueOf(VbInfoGauche.getBarcod().getText());
				try {
					pst = dbCon.getCon().prepareStatement(sql);
					pst.setInt(1, barcode);
					int i = pst.executeUpdate();
					if(i==1) {
						System.out.println("essué completement");
						BordP.getTab().getItems().clear();
						loadDataFromDataBase();
						clearTextField();
					}
					//loadDataFromDataBase();
					
				}catch(SQLException e2) {
					e2.getMessage();
				}
			});
		}
		return del;
	}
	public static void loadDataFromDataBase() {
	
		
		try {
			pst = dbCon.getCon().prepareStatement("Select * from products");
			 rs = pst.executeQuery();
			 while(rs.next()) {
			 BordP.getData().add(new Products(rs.getInt(1),rs.getString(2), rs.getString(3),rs.getString(4)));
			 }
		}catch(SQLException e) {
			e.printStackTrace();
		}
		BordP.getTab().setItems(BordP.getData());
	}
	
	
	public void clearTextField() {
		VbInfoGauche.getBarcod().clear();
		VbInfoGauche.getName().clear();
		VbInfoGauche.getUnitPri().clear();
		VbInfoGauche.getDesc().clear();
	}
}

 class CopyR extends VBox{
	Label l = new Label("Copy@Right CAPITAINE COOLMAN First Edition");
	Label l1 = new Label("90417854_Avedji_Lomé-TOGO.");
	public CopyR() {
		this.getChildren().addAll(l,l1);
		this.setAlignment(Pos.CENTER);
		this.setPadding(new Insets(20,0,0,20));
	}
}

















