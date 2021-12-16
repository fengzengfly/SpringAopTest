package com.fengsir.aop.controller;

import com.fengsir.aop.myaop.MyAnnotation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 */
@RestController
public class MyAopController {

  @MyAnnotation
  @GetMapping("/t")
  public String test(@RequestParam("userId") String userId) {
    return "success";
  }
}
