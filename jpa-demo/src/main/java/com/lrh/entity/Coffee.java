package com.lrh.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;
import org.joda.money.Money;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(name = "coffer")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString(callSuper = true)
public class Coffee extends BaseEntity implements Serializable{

//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "s_g" )
//    @SequenceGenerator(name = "s_g",sequenceName = "coffee_seq")
//    private Long id;

    private String name;

    @Type(type = "org.jadira.usertype.moneyandcurrency.joda.PersistentMoneyAmount",
                parameters = {@org.hibernate.annotations.Parameter(name="currencyCode",value = "CNY")})
    private Money price;


//    @Column(updatable = false)
//    @CreationTimestamp
//    private Date createTime;
//
//    @UpdateTimestamp
//    private Date updateTime;


}
