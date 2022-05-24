package com.softserve.tourcomp.dto.other;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class PageResponse<T> {
    private Integer pages;
    private Long elements;
    private List<T> data;
}
