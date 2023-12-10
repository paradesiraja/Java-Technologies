package com.nt.jdbc;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.commons.io.IOUtils;

public class LoBsRetrieveTest_Oracle {
  private final static String GET_ACTOR_INFO_BY_AID="SELECT * FROM ACTOR_INFO WHERE AID=?";
	public static void main(String[] args) {
       try(Scanner sc=new Scanner(System.in);
    		   Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","xe","xe");
    		   PreparedStatement ps=con.prepareStatement(GET_ACTOR_INFO_BY_AID);
    		   ){
    	   //READ INPUTS
    	   int no=0;
    	   if(sc!=null) {
    		   System.out.println("Enter actor aid::");
    		   no=sc.nextInt();
    	   }
    	   //set the values
    	   if(ps!=null) 
    		   ps.setInt(1, no);
    	   //execute the query
    	   try(ResultSet rs=ps.executeQuery();){
    		   if(rs!=null) {
    			   if(rs.next()) {
    				  int id=rs.getInt(1);
    				  String name=rs.getString(2);
    				  try(InputStream is=rs.getBinaryStream(3);
    						  Reader reader=rs.getCharacterStream(4);
    					  //create empty destination file using Streams
    					  OutputStream os=new FileOutputStream("retrive_photo.jpg");
    					  Writer writer=new FileWriter("retrive_profile.text");
    						  ){
    					  //copy lobs collected from DB table to destination files
    					  IOUtils.copy(is,os);
    					  IOUtils.copy(reader,writer);
    					  System.out.println("actor_info::"+id+" "+name+"LoBs are Retrived");
    				  }
    			   }
    			   else {
    				   System.out.println("Acter not found");  
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
