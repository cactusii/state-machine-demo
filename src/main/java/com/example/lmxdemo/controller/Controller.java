package com.example.lmxdemo.controller;

import com.example.statemachinedemo.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Autowired
    public Service service;



    @RequestMapping(value = "/transform", method = RequestMethod.POST)
    public ResponseEntity<String> transform(
            @RequestParam("event") int eventValue) {
        if (service.transform(eventValue)) {
            return ResponseEntity.ok().body("状态转换成功");
        }
        return ResponseEntity.status(403).body("非法状态转换");
    }

    @RequestMapping(value = "/init", method = RequestMethod.POST)
    public ResponseEntity<String> init() {
        service.init();
        return ResponseEntity.ok().body("初始化成功");
    }
}
