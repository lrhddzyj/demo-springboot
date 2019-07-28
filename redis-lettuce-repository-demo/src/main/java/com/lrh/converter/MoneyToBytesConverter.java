package com.lrh.converter;

import org.joda.money.Money;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

import java.nio.charset.StandardCharsets;

@WritingConverter
public class MoneyToBytesConverter implements Converter<Money,byte[]> {
    @Override
    public byte[] convert(Money money) {
        long amountMinorLong = money.getAmountMinorLong();
        String value = Long.toString(amountMinorLong);
        return value.getBytes(StandardCharsets.UTF_8);
    }
}
