package com.softserve.tourcomp.dao.impl;

import com.softserve.tourcomp.dao.BookingDao;
import com.softserve.tourcomp.dao.impl.mapper.BookingMapper;
import com.softserve.tourcomp.entity.Bookings;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JDBCBookingDao extends JDBCGenericDao<Bookings> implements BookingDao {
  private final String findBookingsByUserEmailQuery = "SELECT * FROM BOOKINGS LEFT JOIN USERS ON USERS.Id = BOOKINGS.id_user WHERE USERS.email = ?";
  private final String findBookingsByUserIdQuery = "SELECT * FROM BOOKINGS WHERE id_user = ?";
  private final String findBookingsByHotelIdQuery = "SELECT * FROM BOOKINGS WHERE id_hotel = ?";
  private final String findBookingsByCityIdQuery = "SELECT * FROM BOOKINGS LEFT JOIN HOTELS ON bookings.id_hotel = hotels.id WHERE id_city = ?";
  private final String findBookingsByCountryIdQuery = "SELECT * FROM BOOKINGS LEFT JOIN HOTELS ON bookings.id_hotel = hotels.id LEFT JOIN CITYS ON hotels.id_city = citys.id WHERE id_country = ?";
  private final String findBookingsByDatesQuery = "SELECT * FROM bookings where (startDate <= ? and endDate >= ?) or (startDate BETWEEN ? AND ?) or (endDate between ? and ?);";
  private final String CountBookedRoomsQuery = "SELECT SUM(bookings.amountRooms) FROM BOOKINGS WHERE id_hotel = ? ";
  private final String byDatesQuery = " AND ((startDate <= ? and endDate >= ?) or (startDate BETWEEN ? AND ?) or (endDate between ? and ?))";
  private final String averageBookingTimeQuery = "SELECT AVG(DATEDIFF(bookings.endDate,bookings.startDate)) FROM BOOKINGS WHERE BOOKINGS.id = ?";
  private JDBCHotelDao jdbcHotelDao = new JDBCHotelDao(connection);
  private JDBCUserDao jdbcUserDao = new JDBCUserDao(connection);


  public JDBCBookingDao(Connection connection) {
    super(connection, "INSERT INTO BOOKINGS(startDate, endDate, amountRooms, price, id_user, id_hotel) VALUES(?, ?, ?, ?, ?, ?)",
          "SELECT * FROM BOOKINGS WHERE id = ?",
          "SELECT * FROM BOOKINGS ",
          "SELECT COUNT(*) FROM BOOKINGS",
          "COUNT(*)",
          "UPDATE BOOKINGS SET startDate = ?, endDate = ?, amountRooms = ?, price = ?, id_user = ?, id_hotel = ? WHERE id = ?",
          7,
          "DELETE FROM BOOKINGS WHERE id = ?",
          new BookingMapper());
  }

  /**
   * @param entity
   * @return
   */
  @Override
  long getId(Bookings entity) {
    return entity.getId();
  }

  /**
   * @param entity
   * @param Id
   * @throws SQLException
   */
  @Override
  void setId(Bookings entity, long Id) throws SQLException {
    entity.setId(Id);
  }

  /**
   * Auxiliary method to extract entity from result with multiple rows
   *
   * @param statement Input statement
   * @return Returns a list of bookings from defined statement
   * @throws SQLException
   */
  @Override
  public List<Bookings> getAllFromStatement(PreparedStatement statement) throws SQLException {
    List<Bookings> entities = new ArrayList<>();
    ResultSet rs = statement.executeQuery();
    while (rs.next()) {
      Bookings book = extractEntity(rs);
      entities.add(book);
    }
    return entities;
  }

  /**
   * Extrqcts entity from ResultSet
   *
   * @param rs ResultSet
   * @return Returns Booking
   * @throws SQLException
   */
  @Override
  public Bookings extractEntity(ResultSet rs) throws SQLException {
    Bookings book = mapper.extractFromResultSet(rs);
    book.setHotel(jdbcHotelDao.findHotelByBookingId(book.getId()).get());
    book.setUser(jdbcUserDao.findUserByBookingId(book.getId()).get());
    return book;
  }

  /**
   * @param statement
   * @param entity
   * @throws SQLException
   */
  @Override
  void setEntityValues(PreparedStatement statement, Bookings entity) throws SQLException {
    statement.setDate(1, Date.valueOf(entity.getStartDate()));
    statement.setDate(2, Date.valueOf(entity.getEndDate()));
    statement.setInt(3, entity.getAmountRooms());
    statement.setInt(4, entity.getPrice());
    statement.setLong(5, entity.getUser().getId());
    statement.setLong(6, entity.getHotel().getId());
  }

  /**
   * Find the user by email.
   *
   * @param email String of email
   * @return Returns list of bookings by user with defined email
   */
  @Override
  public List<Bookings> findByUserEmail(String email) {
    List<Bookings> found = null;

    try (PreparedStatement statement = connection.prepareStatement(findBookingsByUserEmailQuery)) {
      statement.setString(1, email);
      found = getAllFromStatement(statement);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return found;
  }

  /**
   * Finds bookings by user_id
   *
   * @param userId
   * @return Returns list of bookings with defined user_id
   */
  @Override
  public List<Bookings> findBookingsByUserId(Long userId) {
    List<Bookings> found = null;

    try (PreparedStatement statement = connection.prepareStatement(findBookingsByUserIdQuery)) {
      statement.setLong(1, userId);
      found = getAllFromStatement(statement);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return found;
  }

  /**
   * @param fromDate
   * @param endDate
   * @return
   */
  @Override
  public List<Bookings> findBookingsByDates(LocalDate fromDate, LocalDate endDate) {
    List<Bookings> found = null;

    try (PreparedStatement statement = connection.prepareStatement(findBookingsByDatesQuery)) {
      statement.setDate(1, Date.valueOf(fromDate));
      statement.setDate(3, Date.valueOf(fromDate));
      statement.setDate(5, Date.valueOf(fromDate));
      statement.setDate(2, Date.valueOf(endDate));
      statement.setDate(4, Date.valueOf(endDate));
      statement.setDate(6, Date.valueOf(endDate));

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
  public List<Bookings> findBookingsByHotelId(Long hotelId) {
    List<Bookings> found = null;

    try (PreparedStatement statement = connection.prepareStatement(findBookingsByHotelIdQuery)) {
      statement.setLong(1, hotelId);
      found = getAllFromStatement(statement);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return found;
  }

  /**
   * @param hotelId
   * @param fromDate
   * @param endDate
   * @return
   */
  @Override
  public List<Bookings> findBookingsByHotelId(Long hotelId, LocalDate fromDate, LocalDate endDate) {
    List<Bookings> found = null;

    try (PreparedStatement statement = connection.prepareStatement(findBookingsByHotelIdQuery + byDatesQuery)) {
      statement.setLong(1, hotelId);
      statement.setDate(2, Date.valueOf(fromDate));
      statement.setDate(3, Date.valueOf(endDate));
      statement.setDate(4, Date.valueOf(fromDate));
      statement.setDate(5, Date.valueOf(endDate));
      statement.setDate(6, Date.valueOf(fromDate));
      statement.setDate(7, Date.valueOf(endDate));
      found = getAllFromStatement(statement);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return found;
  }

  /**
   * @param hotelId
   * @param fromDate
   * @param endDate
   * @return
   */
  @Override
  public Integer countBookedRooms(Long hotelId, LocalDate fromDate, LocalDate endDate) {
    Integer count = 0;
    try (PreparedStatement statement = connection.prepareStatement(CountBookedRoomsQuery + byDatesQuery)) {
      statement.setLong(1, hotelId);
      statement.setDate(2, Date.valueOf(fromDate));
      statement.setDate(3, Date.valueOf(endDate));
      statement.setDate(4, Date.valueOf(fromDate));
      statement.setDate(5, Date.valueOf(endDate));
      statement.setDate(6, Date.valueOf(fromDate));
      statement.setDate(7, Date.valueOf(endDate));
      ResultSet result = statement.executeQuery();
      if (result.next()) {
        count = result.getInt(1);
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    } finally {
      return count;
    }
  }


  /**
   * @param cityId
   * @return
   */
  @Override
  public List<Bookings> findBookingsByCityId(Long cityId) {
    List<Bookings> found = null;

    try (PreparedStatement statement = connection.prepareStatement(findBookingsByCityIdQuery)) {
      statement.setLong(1, cityId);
      found = getAllFromStatement(statement);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return found;
  }

  /**
   * @param cityId
   * @param fromDate
   * @param endDate
   * @return
   */
  @Override
  public List<Bookings> findBookingsByCityId(Long cityId, LocalDate fromDate, LocalDate endDate) {
    List<Bookings> found = null;

    try (PreparedStatement statement = connection.prepareStatement(findBookingsByCityIdQuery + byDatesQuery)) {
      statement.setLong(1, cityId);
      statement.setDate(2, Date.valueOf(fromDate));
      statement.setDate(3, Date.valueOf(endDate));
      statement.setDate(4, Date.valueOf(fromDate));
      statement.setDate(5, Date.valueOf(endDate));
      statement.setDate(6, Date.valueOf(fromDate));
      statement.setDate(7, Date.valueOf(endDate));
      found = getAllFromStatement(statement);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return found;
  }

  /**
   * @param countryId
   * @return
   */
  @Override
  public List<Bookings> findBookingsByCountryId(Long countryId) {
    List<Bookings> found = null;

    try (PreparedStatement statement = connection.prepareStatement(findBookingsByCountryIdQuery)) {
      statement.setLong(1, countryId);
      found = getAllFromStatement(statement);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return found;
  }

  /**
   * @param countryId
   * @param fromDate
   * @param endDate
   * @return
   */
  @Override
  public List<Bookings> findBookingsByCountryId(Long countryId, LocalDate fromDate, LocalDate endDate) {
    List<Bookings> found = null;

    try (PreparedStatement statement = connection.prepareStatement(findBookingsByCountryIdQuery + byDatesQuery)) {
      statement.setLong(1, countryId);
      statement.setDate(2, Date.valueOf(fromDate));
      statement.setDate(3, Date.valueOf(endDate));
      statement.setDate(4, Date.valueOf(fromDate));
      statement.setDate(5, Date.valueOf(endDate));
      statement.setDate(6, Date.valueOf(fromDate));
      statement.setDate(7, Date.valueOf(endDate));
      found = getAllFromStatement(statement);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return found;
  }

  @Override
  public Double averageBookingTime(Long hotelId) {
    Double count = 0.0;
    try (PreparedStatement statement = connection.prepareStatement(averageBookingTimeQuery)) {
      statement.setLong(1, hotelId);
      ResultSet result = statement.executeQuery();
      if (result.next()) {
        count = result.getDouble(1);
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    } finally {
      return count;
    }
  }

  @Override
  public Optional<Bookings> findById(Long bookingId) {
    Bookings entity = null;

    try (PreparedStatement statement = connection.prepareStatement(FindByIDQuery)) {
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
}
