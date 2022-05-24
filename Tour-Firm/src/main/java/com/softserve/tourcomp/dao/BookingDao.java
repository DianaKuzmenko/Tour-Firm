package com.softserve.tourcomp.dao;

import com.softserve.tourcomp.entity.Bookings;

import java.time.LocalDate;
import java.util.List;

/**
 *
 */
public interface BookingDao extends GenericDao<Bookings>{
  /**
   *
   * @param userId
   * @return
   */
  List<Bookings> findBookingsByUserId(Long userId);

  /**
   *
   * @param email
   * @return
   */
  List<Bookings> findByUserEmail(String email);

  /**
   *
   * @param fromDate
   * @param endDate
   * @return
   */
  List<Bookings> findBookingsByDates(LocalDate fromDate,LocalDate endDate);

  /**
   *
   * @param hotelId
   * @return
   */
  List<Bookings> findBookingsByHotelId(Long hotelId);

  /**
   *
   * @param hotelId
   * @param fromDate
   * @param endDate
   * @return
   */
  List<Bookings> findBookingsByHotelId(Long hotelId, LocalDate fromDate,LocalDate endDate);

  /**
   *
   * @param cityId
   * @return
   */
  List<Bookings> findBookingsByCityId(Long cityId);

  /**
   *
   * @param cityId
   * @param fromDate
   * @param endDate
   * @return
   */
  List<Bookings> findBookingsByCityId(Long cityId, LocalDate fromDate , LocalDate endDate);

  /**
   *
   * @param countryId
   * @param fromDate
   * @param endDate
   * @return
   */
  List<Bookings> findBookingsByCountryId(Long countryId, LocalDate fromDate,LocalDate endDate);

  /**
   *
   * @param countryId
   * @return
   */
  List<Bookings> findBookingsByCountryId(Long countryId);

  /**
   *
   * @param hotelId
   * @param fromDate
   * @param endDate
   * @return
   */
  Integer countBookedRooms(Long hotelId,LocalDate fromDate,LocalDate endDate);

  /**
   *
   * @param hotelId
   * @return
   */
  Double averageBookingTime(Long hotelId);
}
