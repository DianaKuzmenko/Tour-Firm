package com.softserve.tourcomp.dao;

import java.util.List;
import java.util.Optional;

/**
 * @param <E>
 */
public interface GenericDao<E> extends AutoCloseable {
  /**
   * @param entity
   * @return
   */
  boolean create(E entity);

  /**
   * @return
   */
  List<E> findAll();

  /**
   * @param id
   * @return
   */
  Optional<E> findById(Long id);

  /**
   * @return
   */
  long count();

  /**
   * @param entity
   * @return
   */
  boolean update(E entity);

  /**
   * @param id
   * @return
   */
  boolean delete(long id);

  /**
   *
   */
  void close();
}

