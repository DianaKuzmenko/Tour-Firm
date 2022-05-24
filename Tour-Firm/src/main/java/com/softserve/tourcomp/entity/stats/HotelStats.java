package com.softserve.tourcomp.entity.stats;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelStats {
  private Long hotelId;
  private String hotelName;
  private Integer visitors;
  private Double averageBookingTime;
}
