package com.lrh.restremplatedemo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class House implements Serializable {

    private Long id;

    private String name;

    private String  roomNum;

    private String landlord;
}
