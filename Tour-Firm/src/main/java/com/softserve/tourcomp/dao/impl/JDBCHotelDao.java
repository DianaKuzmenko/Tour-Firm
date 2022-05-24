package com.softserve.tourcomp.dao.impl;

import com.softserve.tourcomp.dao.HotelDao;
import com.softserve.tourcomp.dao.impl.mapper.HotelMapper;
import com.softserve.tourcomp.dao.impl.mapper.stats.HotelStatsMapper;
import com.softserve.tourcomp.entity.Hotels;
import com.softserve.tourcomp.entity.stats.HotelStats;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 */
public class JDBCHotelDao extends JDBCGenericDao<Hotels> implements HotelDao {
  private final String findHotelsByCityIdQuery = "SELECT * FROM HOTELS WHERE id_city = ?";
  private final String FindByHotelNameQuery = "SELECT * FROM HOTELS WHERE name = ?";
  private final String findHotelsByNumberOfRoomsQuery = "SELECT * FROM HOTELS WHERE numberRooms >= ?";
  private final String findHotelsByPricePerNightQuery = "SELECT * FROM HOTELS WHERE priceNight > ? AND priceNight < ?";
  private final String findHotelsByLowerPricePerNightQuery = "SELECT * FROM HOTELS WHERE priceNight < ? ";
  private final String findHotelsByHigherPricePerNightQuery = "SELECT * FROM HOTELS WHERE priceNight > ?";
  private final String findHotelsByCountryIdQuery = "SELECT * FROM HOTELS LEFT JOIN CITYS ON hotels.id_city = citys.id WHERE CITYS.id_country = ?";
  private final String FindByBookingIdQuery = "SELECT * FROM BOOKINGS LEFT JOIN HOTELS ON bookings.id_hotel = hotels.id WHERE BOOKINGS.id = ?";
  private final String getStatisticsQuery = "SELECT hotels.id, hotels.name, COUNT(bookings.id_user) AS count_users, AVG(DATEDIFF(endDate, startDate)) AS average_booking_time FROM hotels INNER JOIN bookings "+
                         "ON hotels.id = bookings.id_hotel GROUP BY hotels.id";
  private JDBCCityDao jdbcCityDao = new JDBCCityDao(connection);

  public JDBCHotelDao(Connection connection) {
    super(connection, "INSERT INTO HOTELS (name, numberRooms, id_city, priceNight, discription) VALUES (?,?,?,?,?)",
          "SELECT * FROM HOTELS WHERE id = ?",
          "SELECT * FROM HOTELS",
          "SELECT COUNT(*) FROM HOTELS",
          "COUNT(*)",
          "UPDATE HOTELS SET name = ?, numberRooms = ?,id_city = ?, priceNight = ?, discription = ? WHERE id = ?",
          6,
          "DELETE FROM HOTELS WHERE id = ?",
          new HotelMapper());
  }


  /**
   * @param entity
   * @return
   */
  @Override
  long getId(Hotels entity) {
    return entity.getId();
  }

  /**
   * @param entity
   * @param Id
   * @throws SQLException
   */
  @Override
  void setId(Hotels entity, long Id) throws SQLException {
    entity.setId(Id);
  }

  /**
   * @param statement
   * @param entity
   * @throws SQLException
   */
  @Override
  void setEntityValues(PreparedStatement statement, Hotels entity) throws SQLException {
    statement.setString(1, entity.getName());
    statement.setInt(2, entity.getNumberRoom());
    statement.setLong(3, entity.getCity().getId());
    statement.setInt(4, entity.getPriceNight());
    statement.setString(5, entity.getDiscription());
  }

  /**
   * @param bookingId
   * @return
   */
  @Override
  public Optional<Hotels> findHotelByBookingId(Long bookingId) {
    Hotels entity = null;

    try (PreparedStatement statement = connection.prepareStatement(FindByBookingIdQuery)) {
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
   * @param cityId
   * @return
   */
  @Override
  public List<Hotels> findHotelsByCityId(Long cityId) {
    List<Hotels> found = null;

    try (PreparedStatement statement = connection.prepareStatement(findHotelsByCityIdQuery)) {
      statement.setLong(1, cityId);
      found = getAllFromStatement(statement);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return found;
  }

  /**
   * @param nameHotel
   * @return
   */
  @Override
  public Optional<Hotels> findHotelByName(String nameHotel) {
    Hotels entity = null;

    try (PreparedStatement statement = connection.prepareStatement(FindByHotelNameQuery)) {
      statement.setString(1, nameHotel);
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
   * @param pricePerNight
   * @return
   */
  @Override
  public List<Hotels> findHotelsByLowerPricePerNight(Integer pricePerNight) {
    List<Hotels> found = null;

    try (PreparedStatement statement = connection.prepareStatement(findHotelsByLowerPricePerNightQuery)) {
      statement.setInt(1, pricePerNight);
      found = getAllFromStatement(statement);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return found;
  }

  /**
   * @param pricePerNight
   * @return
   */
  @Override
  public List<Hotels> findHotelsByHigherPricePerNight(Integer pricePerNight) {
    List<Hotels> found = null;

    try (PreparedStatement statement = connection.prepareStatement(findHotelsByHigherPricePerNightQuery)) {
      statement.setInt(1, pricePerNight);
      found = getAllFromStatement(statement);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return found;
  }

  /**
   * @param lowerPricePerNight
   * @param higherPricePerNight
   * @return
   */
  @Override
  public List<Hotels> findHotelsByPricesPerNight(Integer lowerPricePerNight, Integer higherPricePerNight) {
    List<Hotels> found = null;

    try (PreparedStatement statement = connection.prepareStatement(findHotelsByPricePerNightQuery)) {
      statement.setInt(1, lowerPricePerNight);
      statement.setInt(2, higherPricePerNight);
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
  public List<Hotels> findHotelsByCountryId(Long countryId) {
    List<Hotels> found = null;

    try (PreparedStatement statement = connection.prepareStatement(findHotelsByCountryIdQuery)) {
      statement.setLong(1, countryId);
      found = getAllFromStatement(statement);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return found;
  }

  /**
   * @param numberOfRooms
   * @return
   */
  @Override
  public List<Hotels> findHotelsByNumberOfRooms(Integer numberOfRooms) {
    List<Hotels> found = null;

    try (PreparedStatement statement = connection.prepareStatement(findHotelsByNumberOfRoomsQuery)) {
      statement.setInt(1, numberOfRooms);
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
  public List<Hotels> getAllFromStatement(PreparedStatement statement) throws SQLException {
    List<Hotels> entities = new ArrayList<>();
    ResultSet rs = statement.executeQuery();
    while (rs.next()) {
      Hotels hotel = mapper.extractFromResultSet(rs);
      hotel.setCity(jdbcCityDao.findCityByHotelId(hotel.getId()).get());
      entities.add(hotel);
    }
    return entities;
  }

  /**
   * @param rs
   * @return
   * @throws SQLException
   */
  Hotels extractEntity(ResultSet rs) throws SQLException {
    Hotels hotel = mapper.extractFromResultSet(rs);
    hotel.setCity(jdbcCityDao.findCityByHotelId(hotel.getId()).get());
    return hotel;
  }

  @Override
  public List<HotelStats> createStatistics() {
    List<HotelStats> found = new ArrayList<>();
    HotelStatsMapper hotelStatsMapper = new HotelStatsMapper();
    try (PreparedStatement statement = connection.prepareStatement(getStatisticsQuery)) {
      ResultSet rs = statement.executeQuery();
      while (rs.next()) {
        HotelStats hotelStats = hotelStatsMapper.extractFromResultSet(rs);
        found.add(hotelStats);
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return found;
  }

  @Override
  public Optional<Hotels> findById(Long hotelId) {
    Hotels entity = null;

    try (PreparedStatement statement = connection.prepareStatement(FindByIDQuery)) {
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
}
