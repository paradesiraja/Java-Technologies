package com.nt.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class Cs_ProcedureCallTest {
 private final static String CALL_PROCEDURE="{call p_sum_data(?,?,?)}";
 /*create or replace procedure p_sum_data(x in number,y in number,z out number)
 as
 begin
 z:=x+y;
 end;
 /
*/
 
	public static void main(String[] args) {
  try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","xe","xe");
		  CallableStatement cs=con.prepareCall(CALL_PROCEDURE);
		  Scanner sc=new Scanner(System.in);
		  ){
	  int val1=0,val2=0;
	  //read input values
	  if(sc!=null) {
	  System.out.println("Enter val1::");
	  val1=sc.nextInt();
	  System.out.println("Enter val2::");
	  val2=sc.nextInt();
	  }
	  if(cs!=null) {
		  
		  //register OUT params jdbc type(all out,return params must be registered
		  cs.registerOutParameter(3, Types.INTEGER);
		  
		  //set values to IN params
		  cs.setInt(1, val1);
		  cs.setInt(2, val2);
		  
		  //call PL/SQL procedure
		  cs.execute();
		  //gather Results from OUT param
		  int result=cs.getInt(3);
		  System.out.println("Result::"+result);
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
