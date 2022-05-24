package com.softserve.tourcomp.dao.impl.mapper;

import com.softserve.tourcomp.entity.Users;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 */
public class UserMapper implements ObjectMapper<Users> {
  /**
   * @param rs
   * @return
   * @throws SQLException
   */
  @Override
  public Users extractFromResultSet(ResultSet rs) throws SQLException {
    Users user = new Users();
    user.setId(rs.getLong("USERS.id"));
    user.setPassword(rs.getString("USERS.password"));
    user.setEmail(rs.getString("USERS.email"));
    user.setFirstName(rs.getString("USERS.firstName"));
    user.setLastName(rs.getString("USERS.lastName"));
    user.setIsAdmin(rs.getBoolean("USERS.isAdmin"));
    return user;

  }
}
