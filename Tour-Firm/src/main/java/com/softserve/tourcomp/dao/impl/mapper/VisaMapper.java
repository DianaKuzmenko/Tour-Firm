package com.softserve.tourcomp.dao.impl.mapper;

import com.softserve.tourcomp.entity.Visas;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 */
public class VisaMapper implements ObjectMapper<Visas> {
  /**
   * @param rs
   * @return
   * @throws SQLException
   */
  @Override
  public Visas extractFromResultSet(ResultSet rs) throws SQLException {
    Visas visa = new Visas();
    visa.setId(rs.getLong("VISAS.id"));
    visa.setName(rs.getString("VISAS.name"));
    return visa;
  }
}
