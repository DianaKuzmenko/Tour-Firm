package com.softserve.tourcomp.service.inteface;

import com.softserve.tourcomp.dto.hotel.HotelRequest;
import com.softserve.tourcomp.dto.hotel.HotelResponse;
import com.softserve.tourcomp.entity.Hotels;
import com.softserve.tourcomp.entity.stats.HotelStats;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface HotelServiceInf {
  Boolean create(HotelRequest hotelR) throws SQLException;
  Boolean update(Long id, HotelRequest hotelR) throws SQLException;
  List<HotelResponse> findByPrice(Integer lower, Integer high) throws SQLException;
  List<HotelResponse> findByLowerPrice(Integer lower) throws SQLException;
  List<HotelResponse> findByHigherPrice(Integer high) throws SQLException;
  List<HotelResponse> findByCountry(Long countryId) throws SQLException;
  List<HotelResponse> findAvailableFromToInCity(Long id, LocalDate start, LocalDate end, Long idCity) throws SQLException;
  Boolean isHotelAvailable(Long id,LocalDate start,LocalDate end) throws SQLException;
  Boolean canBooking(Long userId,LocalDate start,LocalDate end, Long hotelId,Integer number) throws SQLException;
  List<HotelResponse> findByCity(Long cityId) throws SQLException;
  List<HotelResponse> findAll() throws SQLException;
  List<HotelStats> statistic() throws SQLException;
  HotelResponse findOne(Long id);
  Hotels findOneHotel(Long id);
}
