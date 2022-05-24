package com.softserve.tourcomp.dto.hotel;

import com.softserve.tourcomp.dto.city.CityResponse;
import com.softserve.tourcomp.entity.Citys;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelResponse {
  private Long id;
  private String name;
  private Integer numberRoom;
  private Integer priceNight;
  private String discription;
  private CityResponse city;
}
