package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbCappabilityTest {

	public static void main(String[] args) {
    //try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","xe","xe");
    		try(Connection con=DriverManager.getConnection("jdbc:mysql:///Raja12345","root","Raja@1998");){
    	//create DatabaseMetaData
    	DatabaseMetaData dmd=null;
    	if(con!=null) {
    		dmd=con.getMetaData();
    		if(dmd!=null) {
    	       System.out.println("db s/w name::"+dmd.getDatabaseProductName());
    	       System.out.println("db s/w Majorversion::"+dmd.getDatabaseMajorVersion());
    	       System.out.println("db s/w minerversion::"+dmd.getDatabaseMinorVersion());
    	       System.out.println("db s/w drivername::"+dmd.getDriverName());
    	       System.out.println("db s/w version::"+dmd.getDriverVersion());
    	       System.out.println("db s/w maxConnection::"+dmd.getMaxConnections());
    	       System.out.println("db s/w RowSize::"+dmd.getMaxRowSize());
    		}
    	}
    	
    }
    catch(SQLException se) {
    	se.printStackTrace();
    }
    catch(Exception e) {
    	e.printStackTrace();
    }
	}

}
