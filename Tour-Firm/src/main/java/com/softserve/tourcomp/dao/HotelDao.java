package com.softserve.tourcomp.dao;

import com.softserve.tourcomp.entity.Hotels;
import com.softserve.tourcomp.entity.stats.HotelStats;

import java.util.List;
import java.util.Optional;

/**
 *
 */
public interface HotelDao extends GenericDao<Hotels>{
  /**
   *
   * @param cityId
   * @return
   */
  List<Hotels> findHotelsByCityId(Long cityId);

  /**
   *
   * @param bookingId
   * @return
   */
  Optional<Hotels> findHotelByBookingId(Long bookingId);

  /**
   *
   * @param nameHotel
   * @return
   */
  Optional<Hotels> findHotelByName(String nameHotel);

  /**
   *
   * @param pricePerNight
   * @return
   */
  List<Hotels> findHotelsByLowerPricePerNight(Integer pricePerNight);

  /**
   *
   * @param pricePerNight
   * @return
   */
  List<Hotels> findHotelsByHigherPricePerNight(Integer pricePerNight);

  /**
   *
   * @param lowerPricePerNight
   * @param higherPricePerNight
   * @return
   */
  List<Hotels> findHotelsByPricesPerNight(Integer lowerPricePerNight,Integer higherPricePerNight);

  /**
   *
   * @param countryId
   * @return
   */
  List<Hotels> findHotelsByCountryId(Long countryId);

  /**
   *
   * @param numberOfRooms
   * @return
   */
  List<Hotels> findHotelsByNumberOfRooms(Integer numberOfRooms);

  /**
   *
   * @return
   */
  List<HotelStats> createStatistics();
}
