package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PostgreInsertTest {
  private final static String INSERT_INTO_PRODUCT="INSERT INTO PRODUCT VALUES(NEXTVAL('PID_SEQ'),?,?,?)";
	public static void main(String[] args) {
    try(Connection con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/Raja12345","postgres","Raja@1998");
    		PreparedStatement ps=con.prepareStatement(INSERT_INTO_PRODUCT);
    		Scanner sc=new Scanner(System.in);
    		){
    	String name=null;
    	double price=0.0;
    	double qty=0.0;
    	
    	if(sc!=null) {
    		System.out.println("Enter product name::");
    		name=sc.next().toUpperCase();
    		System.out.println("Enter product price::");
    		price=sc.nextDouble();
    		System.out.println("Enter product qty::");
    		qty=sc.nextDouble();
    	}
    	//set the values into query
    	ps.setString(1, name);
    	ps.setDouble(2,price);
    	ps.setDouble(3, qty);
    	
    	//excute the query
    		int count=ps.executeUpdate();
    	//process the result
    if(count==0) {
    	System.out.println("Record is not Inserted");
    }
    	else
    		System.out.println("Record is Inserted");   	
    }
    catch(SQLException se) {
    	se.printStackTrace();
    }
    catch(Exception e) {
    	e.printStackTrace();
    }
	}

}
