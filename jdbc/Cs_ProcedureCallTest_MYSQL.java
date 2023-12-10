package com.nt.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/*USE `raja12345`;
DROP procedure IF EXISTS `P_GET_PROD_DETAILS_BY_PRICE_RANGE`;

DELIMITER $$
USE `raja12345`$$
CREATE PROCEDURE `P_GET_PROD_DETAILS_BY_PRICE_RANGE` (IN startPrice FLOAT,IN endPrice FLOAT)

BEGIN
SELECT * FROM PRODUCT1 WHERE PRICE>=startPrice AND PRICE<=endPrice;
END$$

DELIMITER ;
*/
public class Cs_ProcedureCallTest_MYSQL {
 private final static String CALL_PROCEDURE="{call P_GET_PROD_DETAILS_BY_PRICE_RANGE(?,?)}";
	public static void main(String[] args) {
      try(Connection con=DriverManager.getConnection("jdbc:mysql:///Raja12345","root","Raja@1998");
    		  CallableStatement cs=con.prepareCall(CALL_PROCEDURE);
    		  Scanner sc=new Scanner(System.in);
    		  ){
    	  float startPrice=0.0f,endPrice=0.0f;
    	  //read inputs
    	  if(sc!=null) {
    	  System.out.println("Enter product startPrice ::");
    	  
    	  startPrice=sc.nextFloat();
          System.out.println("Enter product endtPrice ::");
    	  
    	   endPrice=sc.nextFloat();
      }
      if(cs!=null) {
    	  cs.setFloat(1,startPrice);
    	  cs.setFloat(2,endPrice);
    	  //CALL PL/SQL procedure
    	  cs.execute();
    	  //gather results from return,OUT params
    	 try(ResultSet rs=cs.executeQuery();){
    		 if(rs!=null) {
    			 while(rs.next()) {
    				 System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getFloat(3)+" "+rs.getFloat(4));
    			 }
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
