package com.nt.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class Cs_ProcedureCallTest1 {
  private final static String CALL_PROCEDURE="{call p_square_data(?,?)}";
 /* create or replace procedure p_square_data(x in number,z OUT param)
  as
  begin
  z:=x*x;
  end;
  /
   
   */

	public static void main(String[] args) {
   try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","xe","xe");
		   CallableStatement cs=con.prepareCall(CALL_PROCEDURE);
		   Scanner sc=new Scanner(System.in);
		   ){
	   int val1=0;
	   if(sc!=null) {
	   System.out.println("enter val1::");
	   val1=sc.nextInt();
	  
	   }
	   //register OUT params
	   cs.registerOutParameter(2,Types.INTEGER);
	   //set the values to IN params
	   cs.setInt(1, val1);
	  
	   //call PL/SQL procedure
	   cs.execute();
	   //get results from OUT params
	   int result=cs.getInt(2);
	   System.out.println("result::"+result);
   }
   catch(SQLException se) {
	   se.printStackTrace();
   }
   catch(Exception e) {
	   e.printStackTrace();
   }
	}

}
