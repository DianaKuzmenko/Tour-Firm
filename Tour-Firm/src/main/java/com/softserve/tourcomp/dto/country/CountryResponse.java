package com.softserve.tourcomp.dto.country;

import com.softserve.tourcomp.dto.visa.VisaResponse;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CountryResponse {
  private Long Id;
  private String name;
  private VisaResponse visa;
}
