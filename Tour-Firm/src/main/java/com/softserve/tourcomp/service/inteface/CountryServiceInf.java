package com.softserve.tourcomp.service.inteface;

import com.softserve.tourcomp.dto.country.CountryRequest;
import com.softserve.tourcomp.dto.country.CountryResponse;
import com.softserve.tourcomp.entity.Countrys;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface CountryServiceInf {
  List<CountryResponse> findAll();

  boolean update(Long id, CountryRequest country) throws SQLException;

  boolean create(CountryRequest country) throws SQLException;

  Countrys findOneCountry(Long id);

  CountryResponse findOne(Long id);

  Map<Countrys, Integer> statisticAll();
}
