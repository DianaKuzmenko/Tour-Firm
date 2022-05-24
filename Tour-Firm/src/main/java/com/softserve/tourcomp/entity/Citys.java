package com.softserve.tourcomp.entity;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Citys {
  private Long Id;
  private String name;
  private Countrys country;
}
