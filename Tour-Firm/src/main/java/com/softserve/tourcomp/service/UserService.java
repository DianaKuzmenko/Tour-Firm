package com.softserve.tourcomp.service;

import com.softserve.tourcomp.dao.BookingDao;
import com.softserve.tourcomp.dao.impl.JDBCDaoFactory;
import com.softserve.tourcomp.dao.impl.JDBCUserDao;
import com.softserve.tourcomp.dto.user.UserRequest;
import com.softserve.tourcomp.dto.user.UserResponse;
import com.softserve.tourcomp.dto.visa.VisaResponse;
import com.softserve.tourcomp.entity.Bookings;
import com.softserve.tourcomp.entity.Countrys;
import com.softserve.tourcomp.entity.Users;
import com.softserve.tourcomp.entity.Visas;
import com.softserve.tourcomp.entity.stats.UserStats;
import com.softserve.tourcomp.service.inteface.UserServiceInf;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserService implements UserServiceInf {
  private JDBCDaoFactory daoFactory = new JDBCDaoFactory();
  private JDBCUserDao userDao = daoFactory.createUserDao();
  private ServiceFactory serviceFactory = ServiceFactory.getInstance();
  private CountryService countryService = serviceFactory.getCountryService();
  private VisaService visaService = serviceFactory.getVisaService();
  private BookingDao bookingDao = daoFactory.createBookingDao();

  @Override
  public boolean create(UserRequest userRequest) throws SQLException {
    try {
      Users user = new Users();
      user.setFirstName(userRequest.getFirstName());
      user.setLastName(userRequest.getLastName());
      user.setEmail(userRequest.getEmail());
      user.setIsAdmin(false);
      user.setPassword(userRequest.getPassword());
      user.setCountry(countryService.findOneCountry(userRequest.getCountry()));
      return userDao.create(user);
    } catch (Exception e) {
      throw new SQLException();
    }
  }

  public UserResponse valid(String email, String password) {
    UserResponse user = findUserByEmail(email);
    Users users = findOneUser(user.getId());
    if (users.getPassword().equals(password)) {
      return user;
    } else {
      throw new RuntimeException("Password incorrect!");
    }
  }

  @Override
  public boolean update(Long id, UserRequest userR) throws SQLException {
    try {
      Users user = findOneUser(id);
      user.setFirstName(userR.getFirstName());
      user.setLastName(userR.getLastName());
      user.setCountry(countryService.findOneCountry(userR.getCountry()));
      return userDao.update(user);
    } catch (Exception e) {
      throw new SQLException();
    }
  }

  @Override
  public boolean delete(Long id) throws SQLException {
    try {
      List<Bookings> bookingsByUserId = bookingDao.findBookingsByUserId(id);
      if (bookingsByUserId.isEmpty()) {
        return userDao.delete(id);
      } else {
        for (Bookings booking : bookingsByUserId) {
          bookingDao.delete(booking.getId());
        }
        return userDao.delete(id);
      }
    } catch (Exception e) {
      throw new SQLException();
    }
  }

  @Override
  public UserResponse findByLastName(String lastName) {
    try {
      Optional<Users> userByName = userDao.findUserByName(lastName);
      return userToUserResponse(userByName.get());
    } catch (Exception e) {
      throw new IllegalArgumentException("User with lastname " + lastName + " not exists");
    }
  }

  @Override
  public UserResponse findOne(Long id) throws IllegalArgumentException {
    try {
      Optional<Users> byId = userDao.findById(id);
      Users users = byId.get();
      return userToUserResponse(users);
    } catch (Exception e) {
      throw new IllegalArgumentException("Visa with id " + id + " not exists");
    }
  }

  @Override
  public Users findOneUser(Long id) {
    return userDao.findById(id)
          .orElseThrow(() -> new IllegalArgumentException("User with id " + id + " not exists"));
  }

  @Override
  public List<UserResponse> findAll() {
    List<UserResponse> list = new ArrayList<>();
    List<Users> users = userDao.findAll();
    for (Users user : users) {
      list.add(userToUserResponse(user));
    }
    return list;
  }

  @Override
  public List<Users> findAllUsers() {
    try {
      return userDao.findAll();
    } catch (Exception e) {
      throw new NullPointerException();
    }
  }

  @Override
  public UserResponse findUserByEmail(String email) {
    try {
      Optional<Users> userByEmail = userDao.findUserByEmail(email);
      return userToUserResponse(userByEmail.get());
    } catch (Exception e) {
      throw new NullPointerException("User with " + email + " don't found");
    }
  }

  public List<UserStats> userStats() {
    List<UserStats> statistics = userDao.createStatistics();
    return statistics;
  }

  protected UserResponse userToUserResponse(Users user) {
    UserResponse ur = new UserResponse();
    ur.setId(user.getId());
    ur.setEmail(user.getEmail());
    ur.setFirstName(user.getFirstName());
    ur.setLastName(user.getLastName());
    ur.setIsAdmin(user.getIsAdmin());
    Countrys country = user.getCountry();
    if (country != null) {
      ur.setCountry(countryService.findOne(country.getId()));
    }
    List<Visas> list = user.getVisas();
    if (!list.isEmpty()) {
      List<VisaResponse> listr = new ArrayList<>();
      for (int i = 0; i < list.size(); i++) {
        if (list.get(i) != null) {
          listr.add(visaService.visaToVisaResponse(list.get(i)));
        }
      }
      ur.setVisas(listr);
    }
    ur.setAmountVisa(user.getVisas().size());
    return ur;
  }


}
