package com.softserve.tourcomp.service.inteface;

import com.softserve.tourcomp.dto.user.UserRequest;
import com.softserve.tourcomp.dto.user.UserResponse;
import com.softserve.tourcomp.entity.Users;

import java.sql.SQLException;
import java.util.List;

public interface UserServiceInf {
   boolean create(UserRequest userRequest) throws SQLException;
  boolean update(Long id, UserRequest userR) throws SQLException;
  boolean delete(Long id) throws SQLException;
  UserResponse findByLastName(String lastName);
  UserResponse findOne(Long id) throws IllegalArgumentException;
  Users findOneUser(Long id);
  public List<UserResponse> findAll();
  public List<Users> findAllUsers();
  UserResponse findUserByEmail(String email);
}
