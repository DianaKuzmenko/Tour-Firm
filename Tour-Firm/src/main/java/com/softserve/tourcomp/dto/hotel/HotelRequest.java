package com.softserve.tourcomp.dto.hotel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelRequest {
  private String name;
  private Integer numberRoom;
  private Integer priceNight;
  private String discription;
  private Long city;
}
