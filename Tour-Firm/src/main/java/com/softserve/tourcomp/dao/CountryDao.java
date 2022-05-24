package com.softserve.tourcomp.dao;

import com.softserve.tourcomp.entity.Countrys;

import java.util.List;
import java.util.Optional;

/**
 *
 */
public interface CountryDao extends GenericDao<Countrys>{
  /**
   *
   * @param nameCountry
   * @return
   */
  Optional<Countrys> findByCountryName(String nameCountry);

  /**
   *
   * @param cityId
   * @return
   */
  Optional<Countrys> findCountryByCityId(Long cityId);

  /**
   *
   * @param visaId
   * @return
   */
  List<Countrys> findCountriesByVisaId(Long visaId);



}