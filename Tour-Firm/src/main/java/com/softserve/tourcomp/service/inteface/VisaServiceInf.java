package com.softserve.tourcomp.service.inteface;

import com.softserve.tourcomp.dto.city.CityRequest;
import com.softserve.tourcomp.dto.visa.VisaRequest;
import com.softserve.tourcomp.dto.visa.VisaResponse;
import com.softserve.tourcomp.entity.Visas;

import java.sql.SQLException;
import java.util.List;

public interface VisaServiceInf {
  boolean create(VisaRequest visa) throws SQLException;

  boolean update(Long id, VisaRequest visaR) throws SQLException;

  Visas findOneVisa(Long id);

  List<VisaResponse> findAll();

  VisaResponse findOne(Long id);

  Integer countOwners(Long id);

}
