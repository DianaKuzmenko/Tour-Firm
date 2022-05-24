package com.softserve.tourcomp.dao.impl;

import com.softserve.tourcomp.dao.CityDao;
import com.softserve.tourcomp.dao.impl.mapper.CityMapper;
import com.softserve.tourcomp.dao.impl.mapper.ObjectMapper;
import com.softserve.tourcomp.entity.Citys;
import com.softserve.tourcomp.entity.Countrys;
import com.softserve.tourcomp.entity.Visas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 */
public class JDBCCityDao extends JDBCGenericDao<Citys> implements CityDao {
  private final String FindByCityNameQuery = "SELECT * FROM CITYS WHERE name = ?";
  private final String findCityByCountryIdQuery = "SELECT * FROM CITYS WHERE id_country = ?";
  private final String FindByHotelIdQuery = "SELECT * FROM CITYS LEFT JOIN HOTELS ON CITYS.id = HOTELS.id_city WHERE HOTELS.id = ?";
  JDBCCountryDao jdbcCountryDao = new JDBCCountryDao(connection);

  public JDBCCityDao(Connection connection) {
    super(connection,
          "INSERT INTO CITYS(name, id_country) VALUES(?,?)",
          "SELECT * FROM CITYS WHERE CITYS.id = ?",
          "SELECT * FROM CITYS ",
          "SELECT COUNT(*) FROM CITYS ",
          "COUNT(*)",
          "UPDATE CITYS SET name = ?, id_country = ? WHERE id = ?",
          3,
          "DELETE FROM CITYS WHERE id = ?",
          new CityMapper());
  }


  /**
   * @param entity
   * @return
   */
  @Override
  long getId(Citys entity) {
    return entity.getId();
  }

  /**
   * @param entity
   * @param Id
   * @throws SQLException
   */
  @Override
  void setId(Citys entity, long Id) throws SQLException {
    entity.setId(Id);
  }

  /**
   * @param statement
   * @param entity
   * @throws SQLException
   */
  @Override
  void setEntityValues(PreparedStatement statement, Citys entity) throws SQLException {
    statement.setString(1, entity.getName());
    statement.setLong(2, entity.getCountry().getId());
  }

  /**
   * @param nameCity
   * @return
   */
  @Override
  public Optional<Citys> findByCityName(String nameCity) {
    Citys entity = null;

    try (PreparedStatement statement = connection.prepareStatement(FindByCityNameQuery)) {
      statement.setString(1, nameCity);
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
  public List<Citys> findCityByCountryId(Long countryId) {
    List<Citys> found = null;

    try (PreparedStatement statement = connection.prepareStatement(findCityByCountryIdQuery)) {
      statement.setLong(1, countryId);
      found = getAllFromStatement(statement);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return found;
  }

  /**
   * @param hotelId
   * @return
   */
  @Override
  public Optional<Citys> findCityByHotelId(Long hotelId) {
    Citys entity = null;

    try (PreparedStatement statement = connection.prepareStatement(FindByHotelIdQuery)) {
      statement.setLong(1, hotelId);
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
   * @param statement
   * @return
   * @throws SQLException
   */
  @Override
  public List<Citys> getAllFromStatement(PreparedStatement statement) throws SQLException {
    ObjectMapper<Citys> cityMapper = new CityMapper();
    List<Citys> entities = new ArrayList<>();
    ResultSet rs = statement.executeQuery();
    while (rs.next()) {
      entities.add(extractEntity(rs));
    }
    return entities;
  }

  /**
   * @param rs
   * @return
   * @throws SQLException
   */
  @Override
  public Citys extractEntity(ResultSet rs) throws SQLException {
    Citys city = mapper.extractFromResultSet(rs);
    city.setCountry(jdbcCountryDao.findCountryByCityId(city.getId()).get());
    return city;
  }

  @Override
  public Optional<Citys> findById(Long id) {
    Citys entity = null;

    try (PreparedStatement statement = connection.prepareStatement(FindByIDQuery)) {
      statement.setLong(1, id);
      ResultSet result = statement.executeQuery();
      if (result.next()) {
        entity = extractEntity(result);
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return Optional.ofNullable(entity);
  }
}
