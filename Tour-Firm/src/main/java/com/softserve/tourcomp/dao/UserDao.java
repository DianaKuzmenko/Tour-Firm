package com.softserve.tourcomp.dao;

import com.softserve.tourcomp.entity.Users;
import com.softserve.tourcomp.entity.Visas;
import com.softserve.tourcomp.entity.stats.UserStats;

import java.sql.SQLException;
import java.util.Optional;
import java.util.List;

/**
 *
 */
public interface UserDao extends GenericDao<Users>{
  /**
   *
   * @param email
   * @return
   */
  Optional<Users> findUserByEmail(String email);

  /**
   *
   * @param usrId
   * @return
   */
  Optional<Users> findById(Long usrId);

  /**
   *
   * @param lastName
   * @return
   */
  Optional<Users> findUserByName(String lastName);

  /**
   *
   * @param CountryId
   * @return
   */
  List<Users> findUsersByCountryId(Long CountryId);

  /**
   *
   * @param bookingId
   * @return
   */
  Optional<Users> findUserByBookingId(Long bookingId);

  /**
   *
   * @param visaId
   * @return
   */
  List<Users> findUserByVisaId(Long visaId);

  /**
   *
   * @return
   */
  List<UserStats> createStatistics();

  /**
   *
   * @param userId
   * @return
   * @throws SQLException
   */
  List<Visas> getVisas(long userId) throws SQLException;

  /**
   *
   * @param userId
   * @param visa
   * @throws SQLException
   */
  void insertVisa(Long userId,Visas visa) throws SQLException;
}
