package com.nt.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import oracle.jdbc.pool.OracleConnectionPoolDataSource;

public class ConnPoolTest {

	public static void main(String[] args) {
		OracleConnectionPoolDataSource ds=null;
   try {
	   //create DataSource obj
	   ds=new OracleConnectionPoolDataSource();
	   //set jdbc properties
	   ds.setDriverType("thin");
	   ds.setURL("jdbc:oracle:thin:@localhost:1521:xe");
	   ds.setUser("xe");
	   ds.setPassword("xe");
   }
   catch(SQLException se) {
	   se.printStackTrace();
	   
   }
   catch(Exception e) {
	   e.printStackTrace();
   }
   //get pooled jdbc con object
   try(Connection con=ds.getConnection();
		  PreparedStatement ps=con.prepareStatement("SELECT * FROM STUDENT");
		   ResultSet rs=ps.executeQuery();
		   ){
	   //process the result query
	   if(rs!=null) {
		   while(rs.next()) {
			   System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
			   
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
