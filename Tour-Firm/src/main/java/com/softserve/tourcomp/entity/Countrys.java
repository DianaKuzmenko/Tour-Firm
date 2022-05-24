package com.softserve.tourcomp.entity;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Countrys {
  private Long Id;
  private String name;
  private Visas visa;
}
