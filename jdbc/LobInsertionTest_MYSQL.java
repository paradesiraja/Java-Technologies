package com.nt.jdbc;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class LobInsertionTest_MYSQL {
 private final static String Insert_Actor_INFO="INSERT INTO ACTOR_INFO(NAME,PHOTO,PROFILE)VALUES(?,?,?)";
	public static void main(String[] args) {
     try(Scanner sc=new Scanner(System.in);
    		 Connection con=DriverManager.getConnection("jdbc:mysql:///Raja12345","root","Raja@1998");
    		 PreparedStatement ps=con.prepareStatement(Insert_Actor_INFO);
    		 ){
    	 //read input values
    	 String name=null,photopath=null,profilepath=null;
    	 if(sc!=null) {
    		 System.out.println("Enter Actor name::");
    		 name=sc.next();
    		 System.out.println("Enter Actor photopath::");
    		 photopath=sc.next().trim().replace("?","");
    		 System.out.println("Enter Actor profilepath::");
    		 profilepath=sc.next().trim().replace("?","");
    	 }
    	 //create stream pointing to the files
    	 try(InputStream is=new FileInputStream(photopath);
    			Reader reader=new FileReader(profilepath);
    			 ){
    	 //set the values 
    	 if(ps!=null) {
    		 ps.setString(1,name);
    		 ps.setBinaryStream(2,is);
    		 ps.setCharacterStream(3,reader);
    	 }
    	 //execute the sql query
    	 int count=ps.executeUpdate();
    	 //process the query
    	 if(count==0) {
    		 System.out.println("Record not inserted");
    	 }
    		 else
    			 System.out.println("Record inserted");
    		 
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
