package com.softserve.tourcomp.dao.impl.mapper.stats;

import com.softserve.tourcomp.dao.impl.mapper.ObjectMapper;
import com.softserve.tourcomp.entity.stats.HotelStats;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HotelStatsMapper implements ObjectMapper<HotelStats> {

  @Override
  public HotelStats extractFromResultSet(ResultSet rs) throws SQLException {
    HotelStats hotelStats = new HotelStats();
    hotelStats.setHotelId(rs.getLong("id"));
    hotelStats.setHotelName(rs.getString("name"));
    hotelStats.setVisitors(rs.getInt("count_users"));
    hotelStats.setAverageBookingTime(rs.getDouble("average_booking_time"));
    return hotelStats;
  }
}
