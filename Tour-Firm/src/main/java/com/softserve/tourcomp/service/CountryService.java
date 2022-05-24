package com.softserve.tourcomp.service;

import com.softserve.tourcomp.dao.CountryDao;
import com.softserve.tourcomp.dao.VisaDao;
import com.softserve.tourcomp.dao.impl.JDBCDaoFactory;
import com.softserve.tourcomp.dto.country.CountryRequest;
import com.softserve.tourcomp.dto.country.CountryResponse;
import com.softserve.tourcomp.entity.Countrys;
import com.softserve.tourcomp.entity.Visas;
import com.softserve.tourcomp.service.inteface.CountryServiceInf;

import java.sql.SQLException;
import java.util.*;

public class CountryService implements CountryServiceInf {
  private JDBCDaoFactory daoFactory = new JDBCDaoFactory();
  private CountryDao countryDao = daoFactory.createCountryDao();
  private ServiceFactory serviceFactory = ServiceFactory.getInstance();
  private VisaService visaService = serviceFactory.getVisaService();

  @Override
  public boolean create(CountryRequest country) throws SQLException {
    try {
      Countrys countrys = new Countrys();
      countrys.setName(country.getName());
      countrys.setVisa(visaService.findOneVisa(country.getVisa()));
      return countryDao.create(countrys);
    } catch (Exception e) {
      throw new SQLException();
    }
  }

  @Override
  public boolean update(Long id, CountryRequest country) throws SQLException {
    try {
      Countrys countrys = findOneCountry(id);
      countrys.setName(country.getName());
      countrys.setVisa(visaService.findOneVisa(country.getVisa()));
      return countryDao.update(countrys);
    } catch (Exception e) {
      throw new SQLException();
    }

  }

  @Override
  public List<CountryResponse> findAll() {
    try {
      List<CountryResponse> list = new ArrayList<>();
      List<Countrys> countrys = countryDao.findAll();
      for (Countrys country : countrys) {
        list.add(countryToCountryResponse(country));
      }
      return list;
    } catch (Exception e) {
      throw new NullPointerException();
    }
  }

  @Override
  public Countrys findOneCountry(Long id) {
    return countryDao.findById(id)
          .orElseThrow(() -> new IllegalArgumentException("Country with id " + id + " not exists"));
  }

  @Override
  public CountryResponse findOne(Long id) {
    Optional<Countrys> byId = countryDao.findById(id);
    if (byId.isPresent()) {
      Countrys country = byId.get();
      return countryToCountryResponse(country);
    }
    new IllegalArgumentException("Country with id " + id + " not exists");
    return null;
  }

  @Override
  public Map<Countrys, Integer> statisticAll() {
    Map<Countrys, Integer> stat = new HashMap<Countrys, Integer>();
    List<Countrys> countrys = countryDao.findAll();
    for (Countrys country : countrys) {
      stat.put(country, visaService.countOwners(country.getVisa().getId()));
    }
    return stat;
  }

  protected CountryResponse countryToCountryResponse(Countrys country) {
    CountryResponse cr = new CountryResponse();
    cr.setId(country.getId());
    cr.setName(country.getName());
    Visas visa = country.getVisa();
    if (visa != null) {
      cr.setVisa(visaService.visaToVisaResponse(visa));
    }
    return cr;
  }

}

