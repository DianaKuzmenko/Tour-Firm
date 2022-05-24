package com.softserve.tourcomp.dao.impl.mapper;


import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @param <T>
 */
public interface ObjectMapper<T> {
  /**
   * @param rs
   * @return
   * @throws SQLException
   */
  public T extractFromResultSet(ResultSet rs) throws SQLException;
}