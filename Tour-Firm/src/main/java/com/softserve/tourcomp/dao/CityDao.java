package com.softserve.tourcomp.dao;

import com.softserve.tourcomp.entity.Citys;

import java.util.List;
import java.util.Optional;

/**
 *
 */
public interface CityDao extends GenericDao<Citys> {
  /**
   *
   * @param nameCity
   * @return
   */
  Optional<Citys> findByCityName(String nameCity);

  /**
   *
   * @param countryId
   * @return
   */
  List<Citys> findCityByCountryId(Long countryId);

  /**
   *
   * @param hotelId
   * @return
   */
  Optional<Citys> findCityByHotelId(Long hotelId);

}
