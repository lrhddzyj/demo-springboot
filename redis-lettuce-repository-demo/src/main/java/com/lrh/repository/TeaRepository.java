package com.lrh.repository;

import com.lrh.entity.Tea;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeaRepository extends JpaRepository<Tea,Long> {

    Optional<Tea> findOneByName(String name);
}
