package com.softserve.tourcomp.dao.impl.mapper;

import com.softserve.tourcomp.entity.Countrys;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 */
public class CountryMapper implements ObjectMapper<Countrys> {
  /**
   * @param rs
   * @return
   * @throws SQLException
   */
  @Override
  public Countrys extractFromResultSet(ResultSet rs) throws SQLException {
    Countrys country = new Countrys();
    country.setId(rs.getLong("COUNTRYS.id"));
    country.setName(rs.getString("COUNTRYS.name"));
    return country;
  }
}
