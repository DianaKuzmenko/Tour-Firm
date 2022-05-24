package com.softserve.tourcomp.service;

import com.softserve.tourcomp.dao.CityDao;
import com.softserve.tourcomp.dao.CountryDao;
import com.softserve.tourcomp.dao.impl.JDBCDaoFactory;
import com.softserve.tourcomp.dto.city.CityRequest;
import com.softserve.tourcomp.dto.city.CityResponse;
import com.softserve.tourcomp.entity.Citys;
import com.softserve.tourcomp.entity.Countrys;
import com.softserve.tourcomp.service.inteface.CityServiceInf;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CityService implements CityServiceInf {
  private JDBCDaoFactory daoFactory = new JDBCDaoFactory();
  private CityDao cityDao = daoFactory.createCityDao();
  private ServiceFactory serviceFactory = ServiceFactory.getInstance();
  private CountryService countryService = serviceFactory.getCountryService();

  @Override
  public boolean create(CityRequest cityRequest) throws SQLException {
    try{
      Citys city = new Citys();
      city.setName(cityRequest.getName());
      city.setCountry(countryService.findOneCountry(cityRequest.getCountry()));
      return cityDao.create(city);
    }catch (Exception e){
      throw new SQLException("Something went wrong");
    }
  }

  @Override
  public boolean update(Long id, CityRequest cityR) throws SQLException {
    try {
      Citys city = findOneCity(id);
      city.setName(cityR.getName());
      city.setCountry(countryService.findOneCountry(cityR.getCountry()));
      return cityDao.update(city);
    }catch (Exception e){
      throw new SQLException();
    }

  }

  @Override
  public Citys findOneCity(Long id) {
    return cityDao.findById(id)
          .orElseThrow(() -> new IllegalArgumentException("City with id " + id + " not exists"));
  }

  @Override
  public CityResponse findOne(Long id) {
    try {
      Optional<Citys> byId = cityDao.findById(id);
      Citys citys = byId.get();
      return cityToCityResponse(citys);
    } catch (Exception e) {
      throw new IllegalArgumentException("City with id " + id + " not exists");
    }
  }

  @Override
  public List<CityResponse> findAll() {
    try {
      List<CityResponse> list = new ArrayList<>();
      List<Citys> citys = cityDao.findAll();
      for (Citys city : citys) {
        list.add(cityToCityResponse(city));
      }
      return list;
    } catch (Exception e) {
      throw new NullPointerException();
    }
  }

  protected CityResponse cityToCityResponse(Citys city) {
    CityResponse cr = new CityResponse();
    cr.setId(city.getId());
    cr.setName(city.getName());
    Countrys country = city.getCountry();
    if (country != null) {
      cr.setCountry(country);
    }
    return cr;
  }

}
