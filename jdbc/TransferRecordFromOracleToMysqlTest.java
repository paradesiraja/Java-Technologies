package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TransferRecordFromOracleToMysqlTest {
  private static final String ORACLE_SELECT_QUERY="SELECT * FROM PRODUCT";
  private static final String MYSQL_INSERT_QUERY="INSERT INTO PRODUCT VALUES(?,?,?,?)";
	public static void main(String[] args) {
      try(Connection oracon=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","xe","xe");
    		  Connection mysqlcon=DriverManager.getConnection("jdbc:mysql:///Raja1234","root","Raja@1998");
    		  Statement st=oracon.createStatement();
    		  PreparedStatement ps=mysqlcon.prepareStatement(MYSQL_INSERT_QUERY);
    		  ){
	
      //EXECUTE SELECT QUERY in oracle db s/w
      try(ResultSet rs=st.executeQuery(ORACLE_SELECT_QUERY)){
    	  //process the resultset and also inseert records to mysql db s/w
    	  int count=0;
    	  if(rs!=null && ps!=null) {
    		  while(rs.next()) {
    			  //get each record from oracle db table
    			  int id=rs.getInt(1);
    			  String name=rs.getString(2);
    			  float price=rs.getFloat(3);
    			  float qty=rs.getFloat(4);
    			  //set the above values insert sql query param values to insert the record to mysql db s/w
    			  ps.setInt(1,id);
    			  ps.setString(2,name);
    			  ps.setFloat(3,price);
    			  ps.setFloat(4,qty);
    			  int result=ps.executeUpdate();
    			  if(result==0)
    				  System.out.println("Record not inserted");
    			  else
    				  System.out.println("Record  inserted");
    			  count++;
    			  System.out.println(count+"no.of records are copied from oracle db table to mysql db table");
    		  }
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
