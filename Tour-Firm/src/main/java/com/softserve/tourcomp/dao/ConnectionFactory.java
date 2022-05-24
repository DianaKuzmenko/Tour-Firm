package com.softserve.tourcomp.dao;

import java.sql.Connection;
import com.mysql.jdbc.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 */
public class ConnectionFactory {
  public static final String URL = "jdbc:mysql://localhost:3306/TourFirm?serverTimezone=UTC";
  public static final String USER = "root";
  public static final String PASS = "Root1234";

  private static Connection instance;

  private ConnectionFactory() {
  }

  /**
   *
   * @return
   */
  public static Connection getConnection()
  {
    if(instance == null) {
      try {
        DriverManager.registerDriver(new Driver());
        return DriverManager.getConnection(URL, USER, PASS);
      } catch (SQLException ex) {
        throw new RuntimeException("Error connecting to the database", ex);
      }
    }else{
      return instance;
    }
  }


}
