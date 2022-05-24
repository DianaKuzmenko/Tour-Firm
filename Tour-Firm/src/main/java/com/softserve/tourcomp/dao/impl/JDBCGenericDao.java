package com.softserve.tourcomp.dao.impl;

import com.softserve.tourcomp.dao.GenericDao;
import com.softserve.tourcomp.dao.impl.functional.PreparedStatementAction;
import com.softserve.tourcomp.dao.impl.functional.PreparedStatementAndEntityAction;
import com.softserve.tourcomp.dao.impl.mapper.ObjectMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @param <E>
 */
public abstract class JDBCGenericDao<E> implements GenericDao<E> {
  Connection connection;

  String CreateQuery;
  String FindByIDQuery;
  String FindAllQuery;
  String CountQuery;
  String CountColumnLabel;
  String UpdateQuery;
  int UpdateIdParameterIndex;
  String DeleteQuery;
  ObjectMapper<E> mapper;

  JDBCGenericDao(Connection connection, String createQuery, String findByIDQuery, String findAllQuery, String countQuery, String countColumnLabel, String updateQuery, int updateIdParameterIndex, String deleteQuery, ObjectMapper<E> mapper) {
    this.connection = connection;
    CreateQuery = createQuery;
    FindByIDQuery = findByIDQuery;
    FindAllQuery = findAllQuery;
    CountQuery = countQuery;
    CountColumnLabel = countColumnLabel;
    UpdateQuery = updateQuery;
    UpdateIdParameterIndex = updateIdParameterIndex;
    DeleteQuery = deleteQuery;
    this.mapper = mapper;
  }

  /**
   * @param entity
   * @return
   */
  @Override
  public boolean create(E entity) {
    boolean created = false;
    try (PreparedStatement statement = connection.prepareStatement(CreateQuery, Statement.RETURN_GENERATED_KEYS)) {
      created = transaction(statement, entity, this::createAction);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return created;
  }

  /**
   * @param id
   * @return
   */
  public Optional<E> findById(long id) {
    E entity = null;

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
   * @return
   */
  @Override
  public List<E> findAll() {
    List<E> found = null;
    try (PreparedStatement statement = connection.prepareStatement(FindAllQuery)) {
      found = getAllFromStatement(statement);
    } catch (Exception ex) {
      ex.printStackTrace();
      found = new ArrayList<>();
    }
    return found;
  }

  /**
   * @return
   */
  @Override
  public long count() {
    return count(CountQuery, CountColumnLabel);
  }

  /**
   * @param entity
   * @return
   */
  @Override
  public boolean update(E entity) {
    boolean created = false;
    try (PreparedStatement statement = connection.prepareStatement(UpdateQuery, Statement.RETURN_GENERATED_KEYS)) {
      created = transaction(statement, entity, this::updateAction);
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
      statement.setLong(1, id);
      affected = deleteEntity(statement);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return affected;
  }

  /**
   *
   */
  @Override
  public void close() {
    try {
      connection.close();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  boolean transaction(PreparedStatement statement, PreparedStatementAction action) throws SQLException {
    boolean success = false;
    boolean wasAutocommit = connection.getAutoCommit();
    connection.setAutoCommit(false);
    try {
      if (action.execute(statement)) {
        success = true;
      } else {
        connection.rollback();
      }
    } catch (Exception ex) {
      connection.rollback();
      ex.printStackTrace();
    }
    if (wasAutocommit) {
      connection.commit();
      connection.setAutoCommit(false);
    }
    return success;
  }

  /**
   * @param statement
   * @param entity
   * @param action
   * @param <T>
   * @return
   * @throws SQLException
   */
  <T> boolean transaction(PreparedStatement statement, T entity, PreparedStatementAndEntityAction<T> action) throws SQLException {
    boolean success = false;
    boolean wasAutocommit = connection.getAutoCommit();
    connection.setAutoCommit(false);
    try {
      if (action.execute(statement, entity)) {
        success = true;
      } else {
        connection.rollback();
      }
    } catch (Exception ex) {
      connection.rollback();
      ex.printStackTrace();
    }
    if (wasAutocommit) {
      connection.commit();
      connection.setAutoCommit(false);
    }
    return success;
  }

  /**
   * @param statement
   * @param entity
   * @return
   * @throws SQLException
   */
  boolean createAction(PreparedStatement statement, E entity) throws SQLException {
    if (insertIntoDb(statement, entity) == 1) {
      setId(entity, getId(entity, statement));
      return true;
    }
    return false;
  }

  /**
   * @param statement
   * @param entity
   * @return
   * @throws SQLException
   */
  boolean updateAction(PreparedStatement statement, E entity) throws SQLException {
    return updateOnDb(statement, entity) == 1;
  }

  /**
   * @param entity
   * @param statement
   * @return
   * @throws SQLException
   */
  long getId(E entity, Statement statement) throws SQLException {

    ResultSet generatedKeys = statement.getGeneratedKeys();
    if (generatedKeys.next()) {
      return generatedKeys.getLong(1);
    } else {
      throw new IllegalArgumentException("generatedKeys is empty");
    }
  }

  /**
   * @param statement
   * @return
   * @throws SQLException
   */
  List<E> getAllFromStatement(PreparedStatement statement) throws SQLException {
    List<E> entities = new ArrayList<>();
    ResultSet rs = statement.executeQuery();
    while (rs.next()) {
      entities.add(extractEntity(rs));
    }
    return entities;
  }

  /**
   * @param statement
   * @param entity
   * @return
   * @throws SQLException
   */
  int insertIntoDb(PreparedStatement statement, E entity) throws SQLException {
    setEntityValues(statement, entity);
    return statement.executeUpdate();
  }

  /**
   * @param statement
   * @param entity
   * @return
   * @throws SQLException
   */
  int updateOnDb(PreparedStatement statement, E entity) throws SQLException {
    setEntityValues(statement, entity);
    statement.setLong(UpdateIdParameterIndex, getId(entity));
    return statement.executeUpdate();
  }

  /**
   * @param rs
   * @return
   * @throws SQLException
   */
  E extractEntity(ResultSet rs) throws SQLException {
    return mapper.extractFromResultSet(rs);
  }

  /**
   * @param statement
   * @return
   * @throws SQLException
   */
  boolean deleteEntity(PreparedStatement statement) throws SQLException {
    return statement.executeUpdate() > 0;
  }

  /**
   * @param query
   * @param countColumnLabel
   * @return
   */
  long count(String query, String countColumnLabel) {
    int count = 0;
    try (Statement statement = connection.createStatement()) {
      ResultSet rs = statement.executeQuery(query);
      if (rs.next()) {
        count = rs.getInt(countColumnLabel);
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return count;
  }

  /**
   * @param entity
   * @return
   */
  abstract long getId(E entity);

  /**
   * @param entity
   * @param Id
   * @throws SQLException
   */
  abstract void setId(E entity, long Id) throws SQLException;

  /**
   * @param statement
   * @param entity
   * @throws SQLException
   */
  abstract void setEntityValues(PreparedStatement statement, E entity) throws SQLException;
}
