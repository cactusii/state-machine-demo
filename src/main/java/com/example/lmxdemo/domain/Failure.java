package com.example.lmxdemo.domain;


import lombok.Data;

import java.io.Serializable;

@Data
public class Failure implements Serializable {
    private long id;
    private String name;
    private Integer state;
}
