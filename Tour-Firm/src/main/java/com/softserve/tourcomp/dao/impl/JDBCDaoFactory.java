package com.softserve.tourcomp.dao.impl;

import com.softserve.tourcomp.constants.DataBaseP;
import com.softserve.tourcomp.dao.DaoFactory;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 */
public class JDBCDaoFactory extends DaoFactory {
  private static Connection connection;

  public JDBCDaoFactory() {
  }

  /**
   * @return
   */
  @Override
  public JDBCBookingDao createBookingDao() {
    return new JDBCBookingDao(getConnection());
  }

  /**
   * @return
   */
  @Override
  public JDBCUserDao createUserDao() {
    return new JDBCUserDao(getConnection());
  }

  /**
   * @return
   */
  @Override
  public JDBCHotelDao createHotelDao() {
    return new JDBCHotelDao(getConnection());
  }

  /**
   * @return
   */
  @Override
  public JDBCVisaDao createVisaDao() {
    return new JDBCVisaDao(getConnection());
  }

  /**
   * @return
   */
  @Override
  public JDBCCityDao createCityDao() {
    return new JDBCCityDao(getConnection());
  }

  /**
   * @return
   */
  @Override
  public JDBCCountryDao createCountryDao() {
    return new JDBCCountryDao(getConnection());
  }

  /**
   * @return
   */
  private static synchronized Connection getConnection() {
        if (connection == null){
          try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(DataBaseP.URL,DataBaseP.USER,DataBaseP.PASSWORD);
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
        return connection;
  }
}
