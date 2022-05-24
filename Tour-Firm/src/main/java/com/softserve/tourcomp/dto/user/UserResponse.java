package com.softserve.tourcomp.dto.user;

import com.softserve.tourcomp.dto.country.CountryResponse;
import com.softserve.tourcomp.dto.visa.VisaResponse;
import com.softserve.tourcomp.entity.Countrys;
import com.softserve.tourcomp.entity.Visas;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserResponse {
  private Long id;
  private String firstName;
  private String lastName;
  private String email;
  private Boolean isAdmin;
  private CountryResponse country;
  private List<VisaResponse> visas;
  private Integer amountVisa;
}
