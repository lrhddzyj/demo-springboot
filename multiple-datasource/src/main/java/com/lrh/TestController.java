package com.lrh;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
public class TestController {

    @Resource
    private JdbcTemplate fooJdbcTemplate;


    @RequestMapping(value = "/t",method = RequestMethod.GET)
    public String t() throws SQLException {
        log.info(fooJdbcTemplate.getDataSource().toString());
        log.info(fooJdbcTemplate.getDataSource().getConnection().toString());
        List list = new ArrayList<>();
        fooJdbcTemplate.batchUpdate("", new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                preparedStatement.setString(1, "asd");
                preparedStatement.setString(2, "asd");
            }

            @Override
            public int getBatchSize() {
                return list.size();
            }
        });
        return "hello";
    }


}
