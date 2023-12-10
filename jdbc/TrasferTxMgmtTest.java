package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class TrasferTxMgmtTest {
  private final static String GET_BALANCE_BY_ACNO="SELECT BALANCE FROM JDBC_ACCOUNT_INFO WHERE ACNO=?";
	public static void main(String[] args) {
    try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","xe","xe");
    		Statement st=con.createStatement();
    		PreparedStatement ps=con.prepareStatement(GET_BALANCE_BY_ACNO);
    		Scanner sc=new Scanner(System.in);
    		){
    	long srcNo=0,destNo=0;
    	float amount=0.0f;
    	
    	if(sc!=null) {
    		System.out.println("enter source No::");
    		srcNo=sc.nextLong();
    		System.out.println("Enter Destination No::");
    		destNo=sc.nextLong();
    		System.out.println("Enter amount::");
    		amount=sc.nextFloat();
    	}
    	//set the values
    	if(ps!=null) {
    		ps.setLong(1,srcNo);
    		
    	}
    	try(ResultSet rs=ps.executeQuery();){
    		//process the results
    		Float balance=0.f;
    		if(rs!=null) {
    			if(rs.next()) {
    				balance=rs.getFloat(1);
    				if(amount>balance) {
    					System.out.println("Insufficient fund in the sourse amount");
    					return;
    				}
    				
    				}
    			else {
    				System.out.println("source account not found");
    			}
    		}
    	}
    	//Begin Tx
    	if(con!=null) {
    		con.setAutoCommit(false);
    	if(st!=null) {
    		//add queries to the batch for withdraw operation
    		
    	st.addBatch("UPDATE JDBC_ACCOUNT_INFO SET BALANCE=BALANCE-"+amount+" WHERE ACNO="+srcNo);
    	st.addBatch("UPDATE JDBC_ACCOUNT_INFO SET BALANCE=BALANCE+"+amount+" WHERE ACNO="+destNo);
    	//excute the query
    	int result[]=st.executeBatch();
    	//process the result and applying TXMgmt
    	
    	if(result!=null) {
    		boolean taskFlag=true;
    		for(int i=0;i<result.length;++i) {
    			if(result[i]==0) {
    				taskFlag=false;
    				break;
    			}
    		}
    		if(taskFlag) {
    			con.commit();
    			System.out.println("Transaction Committed(Money Trasformed)");
    		}
    		else {
    			con.rollback();
    			System.out.println("Transaction not committed(Rollback) not Transffered");
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
