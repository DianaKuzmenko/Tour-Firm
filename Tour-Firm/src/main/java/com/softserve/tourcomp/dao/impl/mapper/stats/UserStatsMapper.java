package com.softserve.tourcomp.dao.impl.mapper.stats;

import com.softserve.tourcomp.dao.impl.mapper.ObjectMapper;
import com.softserve.tourcomp.entity.stats.UserStats;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

public class UserStatsMapper implements ObjectMapper<UserStats> {

  @Override
  public UserStats extractFromResultSet(ResultSet rs) throws SQLException {
   UserStats userStats=new UserStats();
   Long userId=rs.getLong("USERS.id");
   userStats.setUserId(userId);
    userStats.setFirstName(rs.getString("firstName"));
    userStats.setLastName(rs.getString("lastName"));
    HashSet<String> temp = new HashSet<>();
    do {
      temp.add(rs.getString("COUNTRYS.name"));
    }while(rs.next() && rs.getLong("USERS.id") == userId);
  userStats.setCountries(temp);
  rs.previous();
  return userStats;
  }



}
