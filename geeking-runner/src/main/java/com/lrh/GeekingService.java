package com.lrh;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class GeekingService {

    private String name;

    public GeekingService(String name) {
        this.name = name;
        log.info("GeekingService init name is {}", name);

    }
}
