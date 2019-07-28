package com.lrh.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.money.Money;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@RedisHash(value = "spring-tea", timeToLive = 60)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeaCache {

    @Id
    private Long id;

    @Indexed
    private String name;

    private Money price;

}
