package com.softserve.tourcomp.service.inteface;

import com.softserve.tourcomp.dto.booking.BookingRequest;
import com.softserve.tourcomp.dto.booking.BookingResponse;
import com.softserve.tourcomp.entity.Bookings;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface BookingServiceInf {
  Boolean create(BookingRequest bookingR) throws SQLException;
  Boolean update(Long id, BookingRequest bookingR);
  Boolean delete(Long id);
  List<Bookings> findByUserBookings(Long id);
  Bookings findOneBook(Long id);
  BookingResponse findOne(Long id);
  List<BookingResponse> findAll(Long id) throws SQLException;
  Integer bookRoom(Long idHotel, LocalDate start, LocalDate end) throws SQLException;
}
