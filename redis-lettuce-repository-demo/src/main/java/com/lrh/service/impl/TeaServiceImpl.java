package com.lrh.service.impl;

import com.lrh.entity.Tea;
import com.lrh.entity.TeaCache;
import com.lrh.repository.TeaCacheRepository;
import com.lrh.repository.TeaRepository;
import com.lrh.service.TeaServiice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class TeaServiceImpl implements TeaServiice {

    @Autowired
    private TeaRepository teaRepository;

    @Autowired
    private TeaCacheRepository teaCacheRepository;

    @Override
    public Optional<Tea> findByName(String name){
        Optional<TeaCache> optional = teaCacheRepository.findOneByName(name);
        if(optional.isPresent()){
            TeaCache teaCache = optional.get();
            Tea t =  Tea.builder().id(teaCache.getId())
                    .name(teaCache.getName())
                    .price(teaCache.getPrice())
                    .build();
            log.info("从缓存中获取数据");
            return Optional.of(t);
        }else{
            Optional<Tea> teaOptional = teaRepository.findOneByName(name);
            teaOptional.ifPresent(c ->{
                TeaCache teaCache = TeaCache.builder()
                        .id(c.getId())
                        .name(c.getName())
                        .price(c.getPrice())
                        .build();
                log.info("从数据库中查找数据");
                teaCacheRepository.save(teaCache);
            });

            return teaOptional;
        }

    }

}
