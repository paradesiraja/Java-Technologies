package com.nt.jdbc;

import java.io.FileWriter;
import java.io.Writer;
import java.sql.SQLException;

import oracle.jdbc.rowset.OracleWebRowSet;

public class WebRowSetdemo {

	public static void main(String[] args) {
   try(OracleWebRowSet wrs=new OracleWebRowSet()){
	   //set jdbc properties
	   wrs.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
	   wrs.setUsername("xe");
	   wrs.setPassword("xe");
	   wrs.setCommand("SELECT SNO,SNAME,SADD,AVG FROM STUDENT");
	   wrs.execute();
	   //process the result
	   while(wrs.next()) {
  		 System.out.println(wrs.getInt(1)+" "+wrs.getString(2)+" "+wrs.getString(3)+" "+wrs.getFloat(4));

	   }
	   //wrs.writeXml(new FileWriter("Student.xml"));
	   wrs.writeXml(System.out);
	   Writer writer=new FileWriter("STUDENT.xml");
	   wrs.writeXml(writer);
   }
   catch(SQLException se) {
	   se.printStackTrace();
   }
   catch(Exception e) {
	   e.printStackTrace();
   }
	}

}
