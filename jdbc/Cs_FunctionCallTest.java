package com.nt.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

/*CREATE OR REPLACE FUNCTION FX_GET_STUD_DETAILS_BY_SNO 
(
  NO IN NUMBER 
, NAME OUT VARCHAR2 
) RETURN FLOAT AS 
AVG FLOAT;
BEGIN
SELECT SNAME,AVG INTO NAME,AVG FROM STUDENT WHERE SNO=NO;
END FX_GET_STUD_DETAILS_BY_SNO;
*/
public class Cs_FunctionCallTest {
 private final static String CALL_FUNCTION="{?=call FX_GET_STUD_DETAILS_BY_SNO(?,?)}";
	public static void main(String[] args) {
      try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","xe","xe");
    		  CallableStatement cs=con.prepareCall(CALL_FUNCTION);
    		  Scanner sc=new Scanner(System.in);
    		  ){
    	  int no=0;
    	  //read inputs
    	  if(sc!=null) {
    	  System.out.println("Enter student no::");
    	  no=sc.nextInt();
      }
      if(cs!=null) {
    	  //register return,OUT params and jdbc Types
    	  cs.registerOutParameter(1,Types.FLOAT);
    	  cs.registerOutParameter(3,Types.VARCHAR);
    	  //set values IN params
    	  cs.setInt(2, no);
    	  //CALL PL/SQL procedure
    	  cs.execute();
    	  //gather results from return,OUT params
    	  System.out.println("Student avg::"+cs.getFloat(1));
    	  System.out.println("Student name::"+cs.getString(3));
    	  
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
