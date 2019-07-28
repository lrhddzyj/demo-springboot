package com.lrh.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;
import org.joda.money.Money;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tea")
@Builder
public class Tea implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 64)
    private String name;

    @CreationTimestamp
    private Date createTime;

    @UpdateTimestamp
    private Date updateTime;

    @Type(type = "org.jadira.usertype.moneyandcurrency.joda.PersistentMoneyAmount",parameters = {
            @org.hibernate.annotations.Parameter(name = "currencyCode",value = "CNY")
    })
    private Money price;
}
