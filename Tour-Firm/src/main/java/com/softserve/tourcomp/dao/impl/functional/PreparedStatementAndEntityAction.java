package com.softserve.tourcomp.dao.impl.functional;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @param <E>
 */
@FunctionalInterface
public interface PreparedStatementAndEntityAction<E> {
  boolean execute(PreparedStatement statement, E entity) throws SQLException;
}

