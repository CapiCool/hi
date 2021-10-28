package com.gest;

public class Products {
	int barcode ;
	String name = "Name";
	String unitPrice = "Unit-Price";
	String description = "Description";
	public Products(int barcode, String name, String unitPrice, String description) {
		super();
		this.barcode = barcode;
		this.name = name;
		this.unitPrice = unitPrice;
		this.description = description;
	}
	public int getBarcode() {
		return barcode;
	}

	public String getName() {
		return name;
	}
	
	public String getUnitPrice() {
		return unitPrice;
	}
	
	public String getDescription() {
		return description;
	}
	
	@Override
	public String toString() {
		return barcode +  name +  unitPrice + unitPrice + description;
	}
}
