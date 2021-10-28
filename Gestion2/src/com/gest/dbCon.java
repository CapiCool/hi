package com.gest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class dbCon {
	static String dbUrl = null;
	static String usr = null;
	static String pw = null;
	static Connection con = null;
	PreparedStatement pst = null;
	public static void main(String[] args) throws SQLException{
	

		try {
			dbUrl ="jdbc:mysql://localhost:3306/gestion";
			pw = "Cool.0000";
			usr = "cool";
	con = DriverManager.getConnection(dbUrl, usr, pw);
	Statement st = con.createStatement();
	String sql = "insert into products(barcode,name,unitp,descri) values(4,'Voiture','4.000.000','Toyota')";
	ResultSet rs = st.executeQuery("Select * from products");
	st.executeUpdate(sql);
	while(rs.next()) {
		System.out.println("Product type " + rs.getInt("barcode"));
	}
		}catch(Exception e1) {
			System.out.println(e1.getMessage());
		}finally {
			con.close();
		}
}
	static Connection getCon() {
		String dbUrl ="jdbc:mysql://localhost:3306/gestion";
		String pw = "Cool.0000";
		String usr = "cool";
	    try {
			con = DriverManager.getConnection(dbUrl, usr, pw);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return con;
	}
}
