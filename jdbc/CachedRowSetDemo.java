package com.nt.jdbc;

import java.sql.SQLException;

import oracle.jdbc.rowset.OracleCachedRowSet;

public class CachedRowSetDemo {

	public static void main(String[] args) {
     try(OracleCachedRowSet crs=new OracleCachedRowSet()){
    	 //set jdbc properties
    	 crs.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
    	 crs.setUsername("xe");
    	 crs.setPassword("xe");
    	 crs.setCommand("SELECT * FROM STUDENT");
    	 crs.execute();
    	 //process the result
    	 while(crs.next()) {
    		 System.out.println(crs.getInt(1)+" "+crs.getString(2)+" "+crs.getString(3)+" "+crs.getFloat(4));
    	 }
    	 System.out.println("stop the database s/w during the sleep period of the application");
    	 Thread.sleep(20000);
    	 crs.absolute(3);
    	 crs.updateString(3, "adoni");
    	 crs.updateRow();
    	 System.out.println("start the database s/w during the sleep period of the application");
    	 Thread.sleep(20000);
    	 crs.acceptChanges();
    	 //process the results
    	 while(crs.next()) {
    		 System.out.println(crs.getInt(1)+" "+crs.getString(2)+" "+crs.getString(3)+" "+crs.getFloat(4));

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
