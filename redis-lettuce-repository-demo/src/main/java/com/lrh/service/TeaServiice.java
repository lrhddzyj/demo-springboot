package com.lrh.service;

import com.lrh.entity.Tea;

import java.util.Optional;

public interface TeaServiice {

    Optional<Tea> findByName(String name);
}
