package com.example.dz_13_pizza_bean_servlet.bean;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class Region implements Serializable {
    private int id;
    private String name_region;
}
