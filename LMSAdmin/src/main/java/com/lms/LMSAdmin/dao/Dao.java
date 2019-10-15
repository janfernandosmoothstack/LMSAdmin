package com.lms.LMSAdmin.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

@Component
public class Dao {
	public Connection getCon() {
		Connection con = null;
		
		try {
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","******");
            
  		} catch(SQLException e) {
			System.out.println(e);
        } 	
		
		return con;
	}
}
