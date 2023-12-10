package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectTest4 {

	public static void main(String[] args) {
        Connection con=null;
        Statement st=null;
        ResultSet rs=null;
        try {
        	//load jdbc class
        	Class.forName("oracle.jdbc.driver.OracleDriver");
        	//conection between jdbc and db s/w
        	con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","xe","xe");
        	//create Statement
        	if(con!=null)
        		st=con.createStatement();
        	//write sql query
        	String query="SELECT MAX(SAL)FROM EMP";
        	System.out.println(query);
        	if(st!=null)
        		rs=st.executeQuery(query);
        	//process the query
        	if(rs!=null) {
        		if(rs.next()) {
        			System.out.println(rs.getInt(1));
        		}
        	
        	    }
        }
        catch(SQLException se) {
        	se.printStackTrace();
        }
        catch(ClassNotFoundException cnf) {
        	cnf.printStackTrace();
        }
        
        finally {
        	try {
        		if(rs!=null)
        			rs.close();
        	}
        	catch(SQLException se) {
        		se.printStackTrace();
        	}
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
        	}
        }
      }
	


