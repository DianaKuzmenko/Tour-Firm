package com.softserve.tourcomp.service.inteface;

import com.softserve.tourcomp.dto.city.CityRequest;
import com.softserve.tourcomp.dto.city.CityResponse;
import com.softserve.tourcomp.entity.Citys;

import java.sql.SQLException;
import java.util.List;

public interface CityServiceInf {
  boolean create(CityRequest cityRequest) throws SQLException;

  boolean update(Long id, CityRequest cityR) throws SQLException;

  Citys findOneCity(Long id);

  CityResponse findOne(Long id);

  List<CityResponse> findAll();
}
