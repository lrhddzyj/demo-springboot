package com.lrh.service.impl;

import com.lrh.entity.Tea;
import com.lrh.repository.TeaRepository;
import com.lrh.service.TeaServicie;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
@CacheConfig(cacheNames = "tea")
public class TeaServiiceImpl implements TeaServicie {

    @Autowired
    private TeaRepository teaRepository;

    @Override
    @Cacheable
    public List<Tea> findAll() {
        return teaRepository.findAll();
    }

    @CacheEvict
    @Override
    public void reloadTea(){

    }

    @Transactional
    @Override
    public void save(Tea tea){
        teaRepository.save(tea);
    }


}
