package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DeleteTest1 {

	public static void main(String[] args) {
        Connection con=null;
        Statement st=null;
        Scanner sc=null;
        try {
        	sc=new Scanner(System.in);
        	int no=0;
        	if(sc!=null) {
        		System.out.println("Enter SNO number::");
        		no=sc.nextInt();
        	}
        
        	//load jdbc driver class
        	//Class.forName("oracle.jdbc.driver.OracleDriver");
        	//established the connection
        	con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "xe", "xe");
        	//create statement of jdbc 
        	if(con!=null)
        		st=con.createStatement();
        	//prepare SQL Query 
        	//DELETE FROM STUDENT WHERE SNO=113;
        	String query="DELETE FROM STUDENT WHERE SNO="+no;
        	System.out.println(query);
        	//process the result
        	int count=0;
        	if(st!=null) {
        		
        		count=st.executeUpdate(query);
        	}
        	if(count==0)
        		System.out.println("No records found");
        	else
        		System.out.println("Records founded and display::"+count);
        	
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

