package com.lrh.repository;

import com.lrh.entity.Tea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeaRepository extends JpaRepository<Tea,Long> {
}
