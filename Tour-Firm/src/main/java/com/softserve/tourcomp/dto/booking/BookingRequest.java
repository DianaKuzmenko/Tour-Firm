package com.softserve.tourcomp.dto.booking;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookingRequest {
  private LocalDate startDate;
  private LocalDate endDate;
  private Integer amountRooms;
  private Long id_user;
  private Long id_hotel;
}
