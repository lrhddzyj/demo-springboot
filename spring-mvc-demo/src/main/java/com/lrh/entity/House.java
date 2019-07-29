package com.lrh.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "house")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class House implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String  roomNum;

    private String landlord;
}
