package com.softserve.tourcomp.dao.impl.mapper;

import com.softserve.tourcomp.entity.Hotels;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 */
public class HotelMapper implements ObjectMapper<Hotels> {
  /**
   * @param rs
   * @return
   * @throws SQLException
   */
  @Override
  public Hotels extractFromResultSet(ResultSet rs) throws SQLException {

    Hotels hotel = new Hotels();
    hotel.setId(rs.getLong("HOTELS.id"));
    hotel.setDiscription(rs.getString("HOTELS.discription"));
    hotel.setName(rs.getString("HOTELS.name"));
    hotel.setNumberRoom(rs.getInt("HOTELS.numberRooms"));
    hotel.setPriceNight(rs.getInt("HOTELS.priceNight"));
    return hotel;
  }
}
