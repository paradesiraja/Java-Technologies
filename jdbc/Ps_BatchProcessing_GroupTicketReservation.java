package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Ps_BatchProcessing_GroupTicketReservation {
   private final static String INSERT_INTO_JDBC_TRAIN_JOURNEY="INSERT INTO JDBC_TRAIN_JOURNEY VALUES(TICKETID_SEQ.NEXTVAL,?,?,?,?,?,?)";
	public static void main(String[] args) {
       try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","xe","xe");
    		   PreparedStatement ps=con.prepareStatement(INSERT_INTO_JDBC_TRAIN_JOURNEY);
    		   Scanner sc=new Scanner(System.in);
    		   ){
    	   //READ INPUTS
    	   String name=null,srcplace=null,destplace=null,doj=null;
    			   java.sql.Timestamp sqld=null;;
    	   int trainNo=0;
    	    int groupSize=0;
    	   boolean isItGroupBooking=false;
    	   if(sc!=null && ps!=null) {
    		   System.out.println("Enter passenger groupSize::");
    		   groupSize=sc.nextInt();
    		   
       		   System.out.println("Enter passenger srcPlace::");
    		   srcplace=sc.next();
    		   System.out.println("Enter Passenger destplace::");
    		   destplace=sc.next();
    		   System.out.println("Enter passenger trainno::");
    		   trainNo=sc.nextInt();
    		   System.out.println("Enter doj(dd/MM/yyyy hh:mm:ss)::");
    		   sc.nextLine();
    		   doj=sc.nextLine();
    		   System.out.println("Enter passenger isItGroupBooking::");
    		   isItGroupBooking=sc.nextBoolean();
    		   
    		   //convert String date to sql.timeslamp
    		   SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
    		   java.util.Date utd=sdf.parse(doj);
    		    sqld=new java.sql.Timestamp(utd.getTime());
    		   
    		   //set the values into the Query
    		   for(int i=1;i<=groupSize;++i) {
    			   System.out.println("enter passenger "+i+" name");
    			    name=sc.next();
    			    ps.setString(1, name);
    			    ps.setString(2,srcplace);
    			    ps.setString(3, destplace);
    			    ps.setBoolean(4, isItGroupBooking);
    			    ps.setInt(5, trainNo);
    			    ps.setTimestamp(6,sqld);
    			    ps.addBatch();
    			    
    		   }
    		   //excute the query
    		   int count[]=ps.executeBatch();
    		   //process the query
    		   if(count!=null) {
    			   System.out.println("TrainTeckets are booked"+count +" passengers");
    		   }
    		   else
    			   System.out.println("train tickets are not booked yet.");
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
