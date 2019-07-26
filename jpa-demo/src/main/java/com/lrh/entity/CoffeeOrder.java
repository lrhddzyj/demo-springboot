package com.lrh.entity;

import com.lrh.common.OrderState;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "coffee_order")
@Entity
@ToString(callSuper = true)
public class CoffeeOrder extends BaseEntity implements Serializable{

//    @Id
//    @GeneratedValue
//    private Long id;

    private String customer;

    @ManyToMany
    @JoinTable(name = "t_order_coffee")
    private List<Coffee> items;

    @Enumerated
    @Column(nullable = false)
    private OrderState state;

//    @Column(updatable = false)
//    @CreationTimestamp
//    private Date createTime;
//
//    @UpdateTimestamp
//    private Date updateTime;
}
