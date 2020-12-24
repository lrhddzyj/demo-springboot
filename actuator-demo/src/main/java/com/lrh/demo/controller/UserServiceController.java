package com.lrh.demo.controller;

import com.lrh.demo.ThreadPoolProvider;
import com.lrh.demo.vo.UserVo;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: lrh
 * @date: 2020/12/22 23:51
 */
@RestController
public class UserServiceController {

  @RequestMapping("user")
  public UserVo getUser(@RequestParam("userId") long id) {
    if (ThreadLocalRandom.current().nextInt() % 2 == 0) {
      return new UserVo(id, "name_" + id);
    } else {
      throw  new RuntimeException("mock error");
    }
  }


  @GetMapping("slowTask")
  public void slowTask() {
    ThreadPoolProvider.getDemoThreadPool().execute(() -> {
      try {
        TimeUnit.HOURS.sleep(1);
      } catch (InterruptedException e) {
      }
    });
  }

}
