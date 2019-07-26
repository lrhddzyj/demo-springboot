package com.lrh.repository;

import com.lrh.entity.CoffeeOrder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoffeeOrderRepository extends BaseRepository<CoffeeOrder> {

    List<CoffeeOrder> findByCustomer(String customer);

    List<CoffeeOrder> findByItems_Name(String name);

}
