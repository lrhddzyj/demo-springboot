package com.lrh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

@NoRepositoryBean
public interface BaseRepository<T> extends JpaRepository<T,Long> {

    List<T> findTop3ByOrderByUpdateTimeDescIdAsc();
}
