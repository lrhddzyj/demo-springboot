package com.lrh.repository;

import com.lrh.entity.TeaCache;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TeaCacheRepository extends CrudRepository<TeaCache,Long> {

    Optional<TeaCache> findOneByName(String name);
}
