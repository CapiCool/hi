package com.gest;

import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class Tab extends TableView<Products>{
	public Tab() {
		initTab();

	}
	public Tab(ObservableList<Products> items) {
		super(items);
		initTab();
		
	}
	
	public void initTab() {
		TableColumn<Products, Integer> bcode = new TableColumn<>("Barcode");
		bcode.setCellValueFactory(new PropertyValueFactory<Products, Integer>("barcode"));
		
		TableColumn<Products, String> name = new TableColumn<>("Name");
		name.setCellValueFactory(new PropertyValueFactory<Products, String>("name"));
		
		TableColumn<Products, String> unitPrice = new TableColumn<>("Unit-Price");
		unitPrice.setCellValueFactory(new PropertyValueFactory<Products, String>("unitPrice"));
		TableColumn<Products, String>  description= new TableColumn<>("Description");
		description.setCellValueFactory(new PropertyValueFactory<Products, String>("description"));
		
		this.getColumns().addAll(bcode,name,unitPrice,description);
		this.setColumnResizePolicy(CONSTRAINED_RESIZE_POLICY);
//		this.setMaxHeight(265);
//		this.setMaxWidth(700);
	}
}












