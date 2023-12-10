package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class DateTimeInsertOracleTest {
  private final static String CUSTOMER_INSERT_QUERY="INSERT INTO CUSTOMER_INFO VALUES(CNO_SEQ.NEXTVAL,?,?,?,?,?)";
	public static void main(String[] args) {
    try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","xe","xe");
    		PreparedStatement ps=con.prepareStatement(CUSTOMER_INSERT_QUERY);
    		Scanner sc=new Scanner(System.in);
    		){
    	//read inputs
    	String name=null,dob=null,top=null,orderdt=null;
    	float billamt=0.0f;
    	if(sc!=null) {
    		System.out.println("Enter customer name::");
    		name=sc.next();
    		System.out.println("Enter customer billamt::");
    		billamt=sc.nextFloat();
    		System.out.println("enter customer dob(dd-MM-yyyy)::");
    		dob=sc.next();
    		System.out.println("Enter customer top(hh:mm:ss)::");
    		top=sc.next();
    		System.out.println("Enter customer orderdt(dd-MM-yyyy hh:mm:ss)::");
    		sc.nextLine();
    		orderdt=sc.nextLine();	
    	}
    	
    	//convert string to java.util.Date class obj
    	SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
    	java.util.Date ud=sdf.parse(dob);
    	java.sql.Date sqd=new java.sql.Date(ud.getTime());
    	
    	//convert string to java.sql.Time obj
    	java.sql.Time sqt=java.sql.Time.valueOf(top);
    	
    	//convert string to java.sql.Time obj
    	SimpleDateFormat stm1=new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
    	java.util.Date utst1=stm1.parse(orderdt);
    	java.sql.Timestamp sqtst1=new java.sql.Timestamp(utst1.getTime());
    	
    	//set sql query
    	if(ps!=null) {
    		ps.setString(1, name);
    		ps.setFloat(2, billamt);
    		ps.setDate(3, sqd);
    		ps.setTime(4, sqt);
    		ps.setTimestamp(5, sqtst1);
    		
    		
    	}
    	
    	//execute the query
    	int count=ps.executeUpdate();
    	if(count==0) {
    		System.out.println("record not inserted");
    	}
    	else
    		System.out.println("Record inserted");
	}
	
    catch (SQLException se) {
    	se.printStackTrace();
    }
    catch(Exception e) {
    	e.printStackTrace();
    }
   }
}

