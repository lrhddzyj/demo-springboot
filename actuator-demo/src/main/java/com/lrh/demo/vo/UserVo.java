package com.lrh.demo.vo;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @description:
 * @author: lrh
 * @date: 2020/12/22 23:51
 */
@Slf4j
@Getter
@Setter
public class UserVo implements Serializable {

  public UserVo(long id, String name) {
    this.id = id;
    this.name = name;
  }

  private static final long serialVersionUID = -6094755823077670209L;

  private long id;

  private String name;

}
