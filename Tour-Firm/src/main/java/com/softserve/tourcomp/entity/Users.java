package com.softserve.tourcomp.entity;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users {
  private Long id;
  private String firstName;
  private String lastName;
  private String email;
  private String password;
  private Boolean isAdmin;
  private Countrys country;
  private List<Visas> visas;
}
