package com.lrh.mapper;

import com.lrh.model.Coffee;
import org.apache.ibatis.annotations.*;

@Mapper
public interface CoffeeMapper {

    @Insert("insert into t_coffee (name, price, create_time, update_time)"
            + "values (#{name}, #{price}, now(), now())")
    @Options(keyColumn = "id", keyProperty = "id", useGeneratedKeys = true)
    int save(Coffee coffee);


    @Select("select * from t_coffee where id= #{id}")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "name", property = "name")
//            @Result(id = true, column = "name", property = "name"),
    })
    Coffee findById(@Param("id") Long id);
}
