package com.nt.jdbc;
	import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

	public class LobInsertionTest1_MYSQL {
	 private final static String Insert_JOB_SEEKERS="INSERT INTO JOB_SEEKERS(NAME,ADDRESS,QUALIFICATION,PHOTO,RESUME,DOB)VALUES(?,?,?,?,?,?)";
		public static void main(String[] args) {
	     try(Scanner sc=new Scanner(System.in);
	    		 Connection con=DriverManager.getConnection("jdbc:mysql:///Raja12345","root","Raja@1998");
	    		 PreparedStatement ps=con.prepareStatement(Insert_JOB_SEEKERS);
	    		 ){
	    	 //read input values
	    	 String name=null,add=null,qualification=null,photopath=null,resumepath=null,dob=null;
	    	 if(sc!=null) {
	    		 System.out.println("Enter seeker name::");
	    		 name=sc.next();
	    		 System.out.println("Enter seeker address::");
	    		 add=sc.next();
	    		 System.out.println("Enter seeker qualification::");
	    		 qualification=sc.next();
	    		 
	    		 System.out.println("Enter seeker photopath::");
	    		 photopath=sc.next().trim().replace("?","");
	    		 System.out.println("Enter seeker resumepath::");
	    		 resumepath=sc.next().trim().replace("?","");
	    		 System.out.println("Enter seeker dob::");
	    		 dob=sc.next();
	    	 }  
	    	 //input stream into the values
	    	 try(InputStream is=new FileInputStream(photopath);
	    			 Reader reader=new FileReader(resumepath);){
	    		SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy"); 
	    	 java.util.Date utd=sdf.parse(dob);
	    	 java.sql.Date sqd=new java.sql.Date(utd.getTime());
	    	 
	    	 //set values into the query
	    	 if(ps!=null) {
	    		 ps.setString(1, name);
	    		 ps.setString(2, add);
	    		 ps.setString(3, qualification);
	             ps.setBinaryStream(4, is);
	             ps.setCharacterStream(5, reader);
	             ps.setDate(6, sqd);
	    	 }
	    	 //execute the query
	    	 int count=ps.executeUpdate();
	    	 //process the query
	    	 if(count==0) {
	    		 System.out.println("Record is not inserted");
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


