package com.softserve.tourcomp.service;

import com.softserve.tourcomp.dao.VisaDao;
import com.softserve.tourcomp.dao.impl.JDBCDaoFactory;
import com.softserve.tourcomp.dto.visa.VisaRequest;
import com.softserve.tourcomp.dto.visa.VisaResponse;
import com.softserve.tourcomp.entity.Visas;
import com.softserve.tourcomp.service.inteface.VisaServiceInf;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VisaService implements VisaServiceInf {
  private JDBCDaoFactory daoFactory = new JDBCDaoFactory();
  private VisaDao visaDao = daoFactory.createVisaDao();

  @Override
  public boolean create(VisaRequest visa) throws SQLException {
    try {
      Visas visas = new Visas();
      visas.setName(visa.getName());
      return visaDao.create(visas);
    } catch (Exception e) {
      throw new SQLException();
    }
  }

  @Override
  public boolean update(Long id, VisaRequest visaR) throws SQLException {
    try {
      Visas visa = findOneVisa(id);
      visa.setName(visa.getName());
      return visaDao.update(visa);
    }catch (Exception e){
      throw new SQLException();
    }
  }


  @Override
  public Visas findOneVisa(Long id) {
    return visaDao.findById(id)
          .orElseThrow(() -> new IllegalArgumentException("Visa with id " + id + " not exists"));
  }

  @Override
  public List<VisaResponse> findAll() {
    try {
      List<VisaResponse> list = new ArrayList<>();
      List<Visas> visas = visaDao.findAll();
      for (Visas visa : visas) {
        list.add(visaToVisaResponse(visa));
      }
      return list;
    } catch (Exception e) {
      throw new NullPointerException();
    }
  }

  @Override
  public VisaResponse findOne(Long id) {
    try {
      Optional<Visas> byId = visaDao.findById(id);
      Visas visas = byId.get();
      return visaToVisaResponse(visas);
    } catch (Exception e) {
      throw new IllegalArgumentException("Visa with id " + id + " not exists");
    }
  }

  @Override
  public Integer countOwners(Long id){
    try{
      return visaDao.countOwnersOfVisa(id);
    }catch (Exception e){
      throw new IllegalArgumentException("Visa with id " + id + " not exists");
    }
  }


  protected VisaResponse visaToVisaResponse(Visas visa) {
    VisaResponse vr = new VisaResponse();
    vr.setId(visa.getId());
    vr.setName(visa.getName());
    return vr;
  }
}
