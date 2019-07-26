package com.lrh.mapper;


import com.github.pagehelper.PageRowBounds;
import com.lrh.model.House;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface HousePlusMapper {

    @Select("select  * from house")
    List<House> selectByExampleWithPageRowbounds(PageRowBounds pageRowBounds);

    @Select("select  * from house")
    List<House> selectByRowbounds(RowBounds rowBounds);

    @Select("select  * from house")
    List<House> selectWithParam(@Param("pageNum") int pageNum, @Param("pageSize") int pageSize);

}
