package com.lrh.service;

import com.lrh.entity.Tea;
import java.util.List;

public interface TeaServicie {

    List<Tea> findAll();

    void reloadTea();

    void save(Tea tea);
}
