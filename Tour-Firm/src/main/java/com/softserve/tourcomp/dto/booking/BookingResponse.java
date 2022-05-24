package com.softserve.tourcomp.dto.booking;

import com.softserve.tourcomp.dto.hotel.HotelResponse;
import com.softserve.tourcomp.dto.user.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookingResponse {
  private Long id;
  private LocalDate startDate;
  private LocalDate endDate;
  private Integer amountRooms;
  private Integer price;
  private UserResponse user;
  private HotelResponse hotel;
}
