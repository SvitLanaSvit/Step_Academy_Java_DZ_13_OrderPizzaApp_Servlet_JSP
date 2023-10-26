package com.example.dz_13_pizza_bean_servlet.bean;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class Person implements Serializable {
    private String firstname;
    private String lastname;
    private String phone;
    private String email;
    private String region;
}
