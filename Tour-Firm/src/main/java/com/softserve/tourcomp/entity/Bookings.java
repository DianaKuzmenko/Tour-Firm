package com.softserve.tourcomp.entity;

import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bookings {
  private Long id;
  private LocalDate startDate;
  private LocalDate endDate;
  private Integer amountRooms;
  private Integer price;
  private Users user;
  private Hotels hotel;

}
