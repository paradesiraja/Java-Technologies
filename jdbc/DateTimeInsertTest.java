package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class DateTimeInsertTest {
     private final static String INSERT_CUSTOMER_QUERY="INSERT INTO CUSTOMER_INFO(CNAME,BILL_AMT,DOB,TOP,ORDER_DATE_TIME) VALUES(?,?,?,?,?)";
	public static void main(String[] args) {
     try(Connection con=DriverManager.getConnection("jdbc:mysql:///Raja12345","root","Raja@1998");
    		 PreparedStatement ps=con.prepareStatement(INSERT_CUSTOMER_QUERY);
    		 Scanner sc=new Scanner(System.in);
    		 ){
    	 
    	 //read inputs
    	 String name=null,dob=null,top=null,orderdt=null;
    	 String billamt=null;
    	 
    	 if(sc!=null) {
    		 System.out.println("Enter customer name::");
    		 name=sc.next();
    		 System.out.println("Enter customer billamt::");
    		 billamt=sc.next();
    		 System.out.println("Enter customer dob (dd-MM-yyyy)::");
    		 dob=sc.next();
    		 System.out.println("Enter customer top(hh:mm:ss)::");
    		 top=sc.next();
    		 System.out.println("Enter customer orderdt(dd-MM-yyyy hh:mm:ss)::");
    		 sc.nextLine();
    		 orderdt=sc.nextLine();
    		 
    	 }
    	 
    	 //convert String to java.util.Date class obj
    	 SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
    	 java.util.Date udt=sdf.parse(dob);
    	 java.sql.Date sqd=new java.sql.Date(udt.getTime());
    	 
    	 //convert String to top(time of Purchase) to java.sql.Time class obj
    	 java.sql.Time sqt= java.sql.Time.valueOf(top);
    	 
    	 //convert String to dateTime to java.sql.Timestamp obj
    	 
    	 SimpleDateFormat sdf1=new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
    	 java.util.Date orderdt1=sdf1.parse(orderdt);
    	 java.sql.Timestamp sqts=new java.sql.Timestamp(orderdt1.getTime());
    	 
    	 //set values sql query params
    	 if(ps!=null) {
    		 ps.setString(1, name);
    		 ps.setString(2, billamt);
    		 ps.setDate(3,sqd);
    		 ps.setTime(4, sqt);
    		 ps.setTimestamp(5, sqts);
    		
    		 //execute the sql query
    		 int count =ps.executeUpdate();
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