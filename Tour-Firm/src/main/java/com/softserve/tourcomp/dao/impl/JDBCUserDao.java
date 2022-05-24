package com.softserve.tourcomp.dao.impl;

import com.softserve.tourcomp.dao.UserDao;
import com.softserve.tourcomp.dao.impl.mapper.*;
import com.softserve.tourcomp.dao.impl.mapper.stats.HotelStatsMapper;
import com.softserve.tourcomp.dao.impl.mapper.stats.UserStatsMapper;
import com.softserve.tourcomp.entity.*;
import com.softserve.tourcomp.entity.stats.UserStats;

import java.sql.*;
import java.util.*;

/**
 *
 */
public class JDBCUserDao extends JDBCGenericDao<Users> implements UserDao {
  private final String FindUserByEmailQuery = "SELECT * FROM USERS WHERE email = ?";
  private final String FindUserByUsernameQuery = "SELECT * FROM USERS WHERE lastName = ?";
  private final String findUsersByCountryIdQuery = "SELECT * FROM USERS WHERE id_country = ?";
  private final String findUsersByVisaIdQuery = "SELECT * FROM USERS_VISAS LEFT JOIN USERS ON USERS.id = USERS_VISAS.id_user WHERE id_visa = ?";
  private final String FindUserByBookingIdQuery = "SELECT * FROM BOOKINGS LEFT JOIN USERS ON BOOKINGS.id_user = USERS.id WHERE BOOKINGS.id = ?";
  private final String createVisasQuery = "INSERT INTO USERS_VISAS(id_user, id_visa) VALUES (?,?)";
  private final String deleteVisasQuery = "DELETE FROM USERS_VISAS WHERE id_user = ?";
  private final String FindVisasQuery = "SELECT * FROM USERS_VISAS LEFT JOIN VISAS ON VISAS.id = id_visa WHERE id_user = ?";
  private final String FindCountryByUserIdQuery = "SELECT * FROM USERS LEFT JOIN COUNTRYS ON USERS.id_country=COUNTRYS.id WHERE USERS.id = ?";
  private final String createStatisticsQuery = "SELECT USERS.id,USERS.firstName,USERS.lastName,COUNTRYS.name FROM USERS\n" +
          "    INNER JOIN BOOKINGS ON users.id = bookings.id_user\n" +
          "LEFT JOIN HOTELS ON bookings.id_hotel = hotels.id\n" +
          " LEFT JOIN CITYS ON hotels.id_city = citys.id " +
          " LEFT JOIN countrys ON citys.id_country = countrys.id ORDER BY USERS.id";
  private CountryMapper countryMapper = new CountryMapper();
  private VisaMapper visaMapper = new VisaMapper();

  public JDBCUserDao(Connection connection) {
    super(connection, "INSERT INTO USERS (firstName, lastName, email, password, isAdmin, id_country) VALUES (?, ?, ?, ?, ?, ?);",
          "SELECT * FROM USERS WHERE id = ?",
          "SELECT * FROM USERS ",
          "SELECT COUNT(*) FROM USERS ",
          "COUNT(*)",
          "UPDATE USERS SET firstName = ?, lastName = ?, email = ?, password = ?, isAdmin = ?, id_country = ? WHERE id = ?",
          7,
          "DELETE FROM USERS WHERE id = ?",
          new UserMapper());
  }

  /**
   * @param user
   * @return
   */
  @Override
  public boolean update(Users user) {
    boolean created = false;
    try (PreparedStatement statement = connection.prepareStatement(UpdateQuery)) {
      deleteVisas(user.getId());
      created = updateAction(statement, user);
      insertVisas(user);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return created;
  }

  /**
   * @param email
   * @return
   */
  @Override
  public Optional<Users> findUserByEmail(String email) {
    Users entity = null;

    try (PreparedStatement statement = connection.prepareStatement(FindUserByEmailQuery)) {
      statement.setString(1, email);
      ResultSet result = statement.executeQuery();
      if (result.next()) {
        entity = extractEntity(result);
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return Optional.ofNullable(entity);
  }

  /**
   * @param usrId
   * @return
   */
  @Override
  public Optional<Users> findById(Long usrId) {
    Users entity = null;

    try (PreparedStatement statement = connection.prepareStatement(FindByIDQuery)) {
      statement.setLong(1, usrId);
      ResultSet result = statement.executeQuery();
      if (result.next()) {
        entity = extractEntity(result);
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return Optional.ofNullable(entity);
  }

  /**
   * @param lastName
   * @return
   */
  public Optional<Users> findUserByName(String lastName) {
    Users entity = null;

    try (PreparedStatement statement = connection.prepareStatement(FindUserByUsernameQuery)) {
      statement.setString(1, lastName);
      ResultSet result = statement.executeQuery();
      if (result.next()) {
        entity = extractEntity(result);
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return Optional.ofNullable(entity);
  }

  /**
   * @param countryId
   * @return
   */
  @Override
  public List<Users> findUsersByCountryId(Long countryId) {
    List<Users> found = null;

    try (PreparedStatement statement = connection.prepareStatement(findUsersByCountryIdQuery)) {
      statement.setLong(1, countryId);
      found = getAllFromStatement(statement);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return found;
  }

  /**
   * @param statement
   * @return
   * @throws SQLException
   */
  @Override
  public List<Users> getAllFromStatement(PreparedStatement statement) throws SQLException {
    List<Users> entities = new ArrayList<>();
    ResultSet rs = statement.executeQuery();
    while (rs.next()) {
      Users extracted = mapper.extractFromResultSet(rs);
      extracted.setCountry(getCountry(extracted.getId()));
      extracted.setVisas(getVisas(extracted.getId()));
      entities.add(extracted);
    }
    return entities;
  }

  /**
   * @param bookingId
   * @return
   */
  @Override
  public Optional<Users> findUserByBookingId(Long bookingId) {
    Users entity = null;

    try (PreparedStatement statement = connection.prepareStatement(FindUserByBookingIdQuery)) {
      statement.setLong(1, bookingId);
      ResultSet result = statement.executeQuery();
      if (result.next()) {
        entity = extractEntity(result);
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return Optional.ofNullable(entity);
  }

  /**
   * @param visaId
   * @return
   */
  @Override
  public List<Users> findUserByVisaId(Long visaId) {
    List<Users> found = null;

    try (PreparedStatement statement = connection.prepareStatement(findUsersByVisaIdQuery)) {
      statement.setLong(1, visaId);
      found = getAllFromStatement(statement);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return found;
  }

  /**
   * @param entity
   * @return
   */
  @Override
  long getId(Users entity) {
    return entity.getId();
  }

  /**
   * @param entity
   * @param Id
   * @throws SQLException
   */
  @Override
  void setId(Users entity, long Id) throws SQLException {
    entity.setId(Id);
  }

  /**
   * @param statement
   * @param entity
   * @throws SQLException
   */
  @Override
  void setEntityValues(PreparedStatement statement, Users entity) throws SQLException {
    statement.setString(1, entity.getFirstName());
    statement.setString(2, entity.getLastName());
    statement.setString(3, entity.getEmail());
    statement.setString(4, entity.getPassword());
    statement.setBoolean(5, entity.getIsAdmin());
    statement.setLong(6, entity.getCountry().getId());
  }

  /**
   * @param rs
   * @return
   * @throws SQLException
   */
  @Override
  Users extractEntity(ResultSet rs) throws SQLException {
    Users extracted = mapper.extractFromResultSet(rs);
    extracted.setCountry(getCountry(extracted.getId()));
    extracted.setVisas(getVisas(extracted.getId()));
    return extracted;
  }

  /**
   * @param id
   * @return
   */
  private Countrys getCountry(Long id) {
    Countrys entity = null;

    try (PreparedStatement statement = connection.prepareStatement(FindCountryByUserIdQuery)) {
      statement.setLong(1, id);
      ResultSet result = statement.executeQuery();
      if (result.next()) {
        entity = countryMapper.extractFromResultSet(result);
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return Optional.ofNullable(entity).get();
  }

  /**
   * @param entity
   * @return
   */
  @Override
  public boolean create(Users entity) {
    boolean created = false;
    try (PreparedStatement statement = connection.prepareStatement(CreateQuery, Statement.RETURN_GENERATED_KEYS)) {

      int affected = insertIntoDb(statement, entity);
      if (affected == 1) {
        setId(entity, getId(entity, statement));
        created = true;
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return created;
  }

  /**
   * @param id
   * @return
   */
  @Override
  public boolean delete(long id) {
    boolean affected = false;
    try (PreparedStatement statement = connection.prepareStatement(DeleteQuery)) {
      affected = transaction(statement, id, this::deleteUsers);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return affected;
  }

  /**
   * @param statement
   * @param id
   * @return
   * @throws SQLException
   */
  boolean deleteUsers(PreparedStatement statement, Long id) throws SQLException {
    statement.setLong(1, id);
    deleteVisas(id);
    return statement.executeUpdate() > 0;
  }

  /**
   * @param from
   * @throws SQLException
   */
  private void insertVisas(Users from) throws SQLException {
    try (PreparedStatement insertVisas = connection.prepareStatement(createVisasQuery)) {
      insertVisas.setLong(1, from.getId());
      for (Visas visa : from.getVisas()) {
        insertVisas.setLong(2, visa.getId());
        insertVisas.execute();
      }
    }
  }
  @Override
public void insertVisa(Long userId,Visas visa) throws SQLException{
  try (PreparedStatement insertVisas = connection.prepareStatement(createVisasQuery)) {
    insertVisas.setLong(1, userId);
      insertVisas.setLong(2, visa.getId());
      insertVisas.execute();
    }
  }


  /**
   * @param userId
   * @return
   * @throws SQLException
   */
  private boolean deleteVisas(long userId) throws SQLException {
    boolean executed = false;
    try (PreparedStatement statement = connection.prepareStatement(deleteVisasQuery)) {
      statement.setLong(1, userId);
      statement.execute();
      executed = true;
    }
    return executed;
  }

  /**
   * @param userId
   * @return
   * @throws SQLException
   */
  @Override
  public List<Visas> getVisas(long userId) throws SQLException {
    List<Visas> result = new ArrayList<>();
    try (PreparedStatement statement = connection.prepareStatement(FindVisasQuery)) {
      statement.setLong(1, userId);
      ResultSet rs = statement.executeQuery();
      while (rs.next()) {
        result.add(visaMapper.extractFromResultSet(rs));
      }
    }
    return result;
  }

  /**
   *
   * @return
   */
  @Override
  public List<UserStats> createStatistics(){
    List<UserStats> found = new ArrayList<>();
    UserStatsMapper userStats = new UserStatsMapper();

    try (PreparedStatement statement = connection.prepareStatement(createStatisticsQuery)) {
      ResultSet rs = statement.executeQuery();
      while (rs.next()) {
        UserStats extracted = userStats.extractFromResultSet(rs);
        found.add(extracted);
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return found;
  }

  public Users validate(String email,String password) {
    Users temp = findUserByEmail(email).get();
    if(temp.getPassword().equals(password)){
return temp;
    }
    throw new RuntimeException();
  }
}
