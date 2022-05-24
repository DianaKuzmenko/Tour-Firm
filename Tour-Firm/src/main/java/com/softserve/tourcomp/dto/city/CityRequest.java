package com.softserve.tourcomp.dto.city;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CityRequest {
  private String name;
  private Long country;
}
