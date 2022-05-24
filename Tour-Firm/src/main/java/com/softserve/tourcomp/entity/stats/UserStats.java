package com.softserve.tourcomp.entity.stats;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserStats {
  private Long userId;
  private String firstName;
  private String lastName;
  private Set<String> countries;

}
