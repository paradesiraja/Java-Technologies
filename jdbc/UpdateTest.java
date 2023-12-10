package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class UpdateTest {

	public static void main(String[] args) {
        Connection con=null;
        Statement st=null;
        Scanner sc=null;
        try {
        	sc=new Scanner(System.in);
        	int sno=0;
        	String newSadd=null;
        	float newAvg=0.0f;
        			
        	if(sc!=null) {
        		System.out.println("Enter SADD ::");
        		newSadd=sc.next();
        		System.out.println("Enter avg ::");
        		newAvg=sc.nextFloat();
        		System.out.println("Enter Sno ::");
        		sno=sc.nextInt();
        	}
        	//prapare query
            newSadd="'"+newSadd+"',";
           
        	//load jdbc driver class
        	//Class.forName("oracle.jdbc.driver.OracleDriver");
        	//established the connection
        	con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "xe", "xe");
        	//create statement of jdbc 
        	if(con!=null)
        		st=con.createStatement();
        	//prepare SQL Query 
        	//UPDATE STUDENT SET SADD='hyd',AVG=67.66 WHERE SNO=111;
        	String query="UPDATE STUDENT SET SADD="+newSadd+"AVG="+newAvg+"WHERE SNO="+sno;
        	System.out.println(query);
        	//process the result
        	int count=0;
        	if(st!=null) {
        		
        		count=st.executeUpdate(query);
        	}
        	if(count==0)
        		System.out.println("No records found");
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


