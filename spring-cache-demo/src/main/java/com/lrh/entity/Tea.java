package com.lrh.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tea")
@Builder
public class Tea {

    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 64)
    private String name;

    @Column(length = 8)
    private Integer weight;

    @CreationTimestamp
    private Date createTime;

    @UpdateTimestamp
    private Date updateTime;

}
