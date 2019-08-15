package com.lrh.controller.request;

import lombok.Data;
import org.joda.money.Money;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class NewHouseRequest {

    @NotEmpty
    private String name;

    @NotNull
    private Money price;

}
