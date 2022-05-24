package com.softserve.tourcomp.dao.impl.mapper;

import com.softserve.tourcomp.entity.Bookings;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 */
public class BookingMapper implements ObjectMapper<Bookings> {
  /**
   * @param rs
   * @return
   * @throws SQLException
   */
  @Override
  public Bookings extractFromResultSet(ResultSet rs) throws SQLException {
    Bookings booking = new Bookings();
    booking.setId(rs.getLong("BOOKINGS.id"));
    booking.setAmountRooms(rs.getInt("BOOKINGS.amountRooms"));
    booking.setPrice(rs.getInt("BOOKINGS.price"));
    booking.setStartDate(rs.getDate("BOOKINGS.startDate").toLocalDate());
    booking.setEndDate(rs.getDate("BOOKINGS.endDate").toLocalDate());
    return booking;
  }
}
