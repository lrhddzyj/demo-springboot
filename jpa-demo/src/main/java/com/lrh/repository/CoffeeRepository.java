package com.lrh.repository;

import com.lrh.entity.Coffee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoffeeRepository extends CrudRepository<Coffee,Long> {

    List<Coffee> findByName(String name);

}
