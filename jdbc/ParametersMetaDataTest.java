package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ParametersMetaDataTest {
  private final static String INSERT_QUERY="INSERT INTO PRODUCT VALUES(?,?,?,?)";
	public static void main(String[] args) {
    try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","xe","xe");
    		PreparedStatement ps=con.prepareStatement(INSERT_QUERY)){
    	//create ParametersMetadata object
    	ParameterMetaData pmd=null;
    	if(ps!=null) {
    		pmd=ps.getParameterMetaData();
    		if(pmd!=null) {
    			int paramCount=pmd.getParameterCount();
    			for(int i=1;i<=paramCount;++i) {
    				System.out.println(pmd.getParameterClassName(i));
    				System.out.println(pmd.getParameterMode(i));
    				System.out.println(pmd.getParameterType(i));
    				System.out.println(pmd.getParameterTypeName(i));
    				System.out.println(pmd.getScale(i));
    				System.out.println(pmd.getPrecision(i));
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
