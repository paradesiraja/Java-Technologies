package com.nt.jdbc;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class LobInsertionTest_Oracle {
  private final static String ACTOR_INSERT_QUERY="INSERT INTO ACTOR_INFO VALUES(AID_SEQ.NEXTVAL,?,?,?)";
	public static void main(String[] args) {
      try(Scanner sc=new Scanner(System.in);
    		  Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","xe","xe");
    		  PreparedStatement ps=con.prepareStatement(ACTOR_INSERT_QUERY)){
    	  //READ INPUT VALUES
    	  String name=null,photopath=null,profilepath=null;
    	  if(sc!=null) {
    		  System.out.println("Enter Actor name::");
    		  name=sc.next();
    		  System.out.println("Enter Actor photopath::");
    		  photopath=sc.next().trim().replace("?", "");
    		  System.out.println("Enter Actor profilepath::");
    		  profilepath=sc.next().trim().replace("?", "");
    	  
    	  }
    	  //create stream pointing to the files
    	  try(InputStream is=new FileInputStream(photopath);
    			  FileReader reader=new FileReader(profilepath)){
    		  if(ps!=null) {
    			  //set values to Query param
    			  ps.setString(1, name);
    			  ps.setBinaryStream(2, is);
    			  ps.setCharacterStream(3, reader);
    			  
    			  //execute the query
    			  int count=ps.executeUpdate();
    			  //process the result
    			  if(count==0) {
    				  System.out.println("Record not inserted");
    			  }
    			  else
    				  System.out.println("Record inserted");
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
