package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class UpdateTest1 {

	public static void main(String[] args) {
        Connection con=null;
        Statement st=null;
        Scanner sc=null;
        try {
        	sc=new Scanner(System.in);
        	int salper=0;
        	String job=null;
        			
        	if(sc!=null) {
        		
        		System.out.println("Enter Salper ::");
        		salper=sc.nextInt();
        		System.out.println("Enter Job ::");
        		job=sc.next().toUpperCase();
        	}
        	//prapare query
            
           job="'"+job+"'";
             //load jdbc driver class
        	//Class.forName("oracle.jdbc.driver.OracleDriver");
        	//established the connection
        	con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "xe", "xe");
        	//create statement of jdbc 
        	if(con!=null)
        		st=con.createStatement();
        	//prepare SQL Query 
        	//UPDATE EMP SET SAL=SAL+SAL*10/100 WHERE JOB='ANALYST';
        	String query="UPDATE EMP SET SAL=SAL+SAL*"+salper+"/100"+" WHERE JOB="+job;
        	System.out.println(query);
        	//process the result
        	int count=0;
        	if(st!=null) {
        		
        		count=st.executeUpdate(query);
        	}
        	if(count==0)
        		System.out.println("No Records are found");
        	else
        	System.out.println("NO.OF RECORDS ARE EFFECTED::"+count);
        	
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



