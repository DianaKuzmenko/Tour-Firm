package com.softserve.tourcomp.dao;
import com.softserve.tourcomp.dao.impl.JDBCDaoFactory;

/**
 *
 */
public abstract class DaoFactory {
  private static volatile DaoFactory daoFactory;

  public abstract BookingDao createBookingDao();

  public abstract UserDao createUserDao();

  public abstract HotelDao createHotelDao();

  public abstract VisaDao createVisaDao();

  public abstract CityDao createCityDao();

  public abstract CountryDao createCountryDao();

  /**
   *
   * @return
   */
  public static synchronized DaoFactory getInstance(){
    if( daoFactory == null){
      synchronized (DaoFactory.class) {
        if(daoFactory == null) {
          daoFactory = new JDBCDaoFactory();
        }
      }
    }
    return daoFactory;
  }}