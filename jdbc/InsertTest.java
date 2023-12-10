package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class InsertTest {

	public static void main(String[] args) {
        Connection con=null;
        Statement st=null;
        Scanner sc=null;
        try {
        	sc=new Scanner(System.in);
        	int no=0;
        	
        	String name=null,sadd=null;
        	float avg=0.0f;
        			
        	if(sc!=null) {
        		
        		System.out.println("Enter Sno ::");
        		no=sc.nextInt();
        		System.out.println("Enter name ::");
        		name=sc.next();
        		System.out.println("Enter sadd ::");
        		sadd=sc.next();
        		System.out.println("Enter avg ::");
        		avg=sc.nextFloat();
        	}
        	//prapare query
            
           name="'"+name+"',";
           sadd="'"+sadd+"',";
             //load jdbc driver class
        	//Class.forName("oracle.jdbc.driver.OracleDriver");
        	//established the connection
        	con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "xe", "xe");
        	//create statement of jdbc 
        	if(con!=null)
        		st=con.createStatement();
        	//prepare SQL Query 
        	//INSERT INTO STUDENT VALUES(115,'veer','mumbai',89.89);
        	String query="INSERT INTO STUDENT VALUES("+no+","+name+sadd+avg+")";
        	System.out.println(query);
        	//process the result
        	int count=0;
        	if(st!=null) {
        		
        		count=st.executeUpdate(query);
        	}
        	if(count==0)
        		System.out.println("No Records are inserted");
        	else
        	System.out.println("RECORD IS INSERTED::"+count);
        	
        	}
        catch(SQLException se) {
        	se.printStackTrace();
        }
        catch(Exception e) {
        	e.printStackTrace();
        }
        finally {
        	try {
        		if(st!=null)
        			st.close();
        	}
        	catch(SQLException se) {
        		se.printStackTrace();
        	}
        	try {
        		if(con!=null)
        			con.close();
        	}
        	catch(SQLException se) {
        		se.printStackTrace();
        	}
        	try {
        		if(sc!=null)
        			sc.close();
        	}
        	catch(Exception e) {
        		e.printStackTrace();
        	}
        }
	}

}




