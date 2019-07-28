package com.lrh.repository;

import com.lrh.model.Coffee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoffeeRepository extends MongoRepository<Coffee,String> {

    List<Coffee> findByName(String name);
}
