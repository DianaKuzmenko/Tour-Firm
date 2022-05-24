package com.softserve.tourcomp.dao;

import com.softserve.tourcomp.entity.Visas;

import java.util.Optional;

/**
 *
 */
public interface VisaDao extends GenericDao<Visas> {
  /**
   *
   * @param visaId
   * @return
   */
  Optional<Visas> findById(Long visaId);

  /**
   *
   * @param visaId
   * @return
   */
  Optional<Visas> findByName(String visaId);

  /**
   *
   * @param visaId
   * @return
   */
  int countOwnersOfVisa(Long visaId);
}
