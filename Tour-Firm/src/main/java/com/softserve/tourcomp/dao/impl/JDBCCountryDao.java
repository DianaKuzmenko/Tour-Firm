package com.softserve.tourcomp.dao.impl;

import com.softserve.tourcomp.dao.CountryDao;
import com.softserve.tourcomp.dao.impl.mapper.CountryMapper;
import com.softserve.tourcomp.dao.impl.mapper.VisaMapper;
import com.softserve.tourcomp.entity.Countrys;

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
public class JDBCCountryDao extends JDBCGenericDao<Countrys> implements CountryDao {
  private final String FindByCountryNameQuery = "SELECT * FROM COUNTRYS LEFT JOIN VISAS ON id_visa = VISAS.id WHERE COUNTRYS.name = ?";
  private final String FindByCityIdQuery = "SELECT * FROM COUNTRYS LEFT JOIN CITYS ON COUNTRYS.id = CITYS.id_country LEFT JOIN VISAS ON id_visa = VISAS.id WHERE CITYS.id = ?";
  private final String findCountrysByVisaIdQuery = "SELECT * FROM COUNTRYS LEFT JOIN VISAS ON countrys.id_visa = VISAS.id WHERE id_visa = ?";
  private final String deleteAllCitiesRelatedCountry = "DELETE FROM CITYS WHERE id_country = ?";

  public JDBCCountryDao(Connection connection) {
    super(connection, "INSERT INTO COUNTRYS (name, id_visa) VALUES (?, ?)",
          "SELECT * FROM COUNTRYS LEFT JOIN VISAS ON COUNTRYS.id_visa = VISAS.id WHERE COUNTRYS.id = ?",
          "SELECT * FROM COUNTRYS LEFT JOIN VISAS ON id_visa = VISAS.id",
          "SELECT COUNT(*) FROM COUNTRYS",
          "COUNT(*)",
          "UPDATE COUNTRYS SET name = ?, id_visa = ? WHERE id = ?",
          3,
          "DELETE FROM COUNTRYS WHERE id = ?",
          new CountryMapper());
  }

  /**
   * @param entity
   * @return
   */
  @Override
  long getId(Countrys entity) {
    return entity.getId();
  }

  /**
   * @param entity
   * @param Id
   * @throws SQLException
   */
  @Override
  void setId(Countrys entity, long Id) throws SQLException {
    entity.setId(Id);
  }

  /**
   * @param rs
   * @return
   * @throws SQLException
   */
  @Override
  public Countrys extractEntity(ResultSet rs) throws SQLException {
    VisaMapper visaMapper = new VisaMapper();
    Countrys country = mapper.extractFromResultSet(rs);
    country.setVisa(visaMapper.extractFromResultSet(rs));
    return country;
  }

  /**
   * @param statement
   * @param entity
   * @throws SQLException
   */
  @Override
  void setEntityValues(PreparedStatement statement, Countrys entity) throws SQLException {
    statement.setString(1, entity.getName());
    statement.setLong(2, entity.getVisa().getId());
  }

  @Override
  public Optional<Countrys> findById(Long id) {
    Countrys entity = null;

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

  /**
   * @param countryId
   * @return
   */
  @Override
  public boolean delete(long countryId) {
    boolean affected = false;
    JDBCVisaDao jdbcVisaDao = new JDBCDaoFactory().createVisaDao();
    try (PreparedStatement statement = connection.prepareStatement(DeleteQuery)) {
      statement.setLong(1, countryId);
      deleteAllCitiesRelatedCountry(countryId);
      affected = deleteAllCitiesRelatedCountry(countryId) && transaction(statement, this::deleteEntity);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return affected;
  }

  /**
   * @param countryId
   * @return
   */
  private boolean deleteAllCitiesRelatedCountry(long countryId) {
    boolean affected = false;
    try (PreparedStatement statement = connection.prepareStatement(deleteAllCitiesRelatedCountry)) {
      statement.setLong(1, countryId);
      affected = transaction(statement, this::deleteEntity);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return affected;
  }

  /**
   * @param nameCountry
   * @return
   */
  @Override
  public Optional<Countrys> findByCountryName(String nameCountry) {
    Countrys entity = null;

    try (PreparedStatement statement = connection.prepareStatement(FindByCountryNameQuery)) {
      statement.setString(1, nameCountry);
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
   * @param cityId
   * @return
   */
  @Override
  public Optional<Countrys> findCountryByCityId(Long cityId) {
    Countrys entity = null;
    try (PreparedStatement statement = connection.prepareStatement(FindByCityIdQuery)) {
      statement.setLong(1, cityId);
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
  public List<Countrys> findCountriesByVisaId(Long visaId) {
    List<Countrys> found = null;

    try (PreparedStatement statement = connection.prepareStatement(findCountrysByVisaIdQuery)) {
      statement.setLong(1, visaId);
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
  public List<Countrys> getAllFromStatement(PreparedStatement statement) throws SQLException {
    List<Countrys> entities = new ArrayList<>();
    ResultSet rs = statement.executeQuery();
    while (rs.next()) {
      entities.add(extractEntity(rs));
    }
    return entities;
  }

}
