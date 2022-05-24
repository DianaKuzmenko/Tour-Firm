package com.softserve.tourcomp.dto.city;

import com.softserve.tourcomp.entity.Countrys;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CityResponse {
  private Long Id;
  private String name;
  private Countrys country;
}
