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

public class LoBRetrieveTest_MYSQL {
 private final static String GET_JOB_SEEKERS_BY_JID="SELECT * FROM JOB_SEEKERS WHERE jID=?";
	public static void main(String[] args) {
     try(Scanner sc=new Scanner(System.in);
    		 Connection con=DriverManager.getConnection("jdbc:mysql:///Raja12345","root","Raja@1998");
    		 PreparedStatement ps=con.prepareStatement(GET_JOB_SEEKERS_BY_JID);
    		 ){
    	 int id=0;
    	 //read input values
    	 if(sc!=null) {
    		 System.out.println("Enter seekers id::");
    		 id=sc.nextInt();
    		 
    	 }
    	 //set the values
    	 if(ps!=null) {
    		 ps.setInt(1, id);
    	 }
    	 //execute the Query
    	 try(ResultSet rs=ps.executeQuery();){
    		
    		 if(rs!=null) {
    			 if(rs.next()) {
    				 int jid=rs.getInt(1);
    				 String name=rs.getString(2);
    				 String address=rs.getString(3);
    				 String qualification=rs.getString(4);
    				 try(InputStream is=rs.getBinaryStream(5);
    						 Reader reader=rs.getCharacterStream(6);
    				 //create empty destination file
    				 OutputStream os=new FileOutputStream("retrive_photo1.jpg");
    				 Writer writer=new FileWriter("retrieve_resume1.txt");
    						 ){
    					 java.sql.Date dob=rs.getDate(7);
    				 
    			 IOUtils.copy(is, os);
    			 IOUtils.copy(reader, writer);
    			 System.out.println("job_seekers::"+jid+" "+name+"Date"+dob+"LoBs are Retrived");
    				 }
    			 }
    			 else {
    				 System.out.println("Record is not retrieved");
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
