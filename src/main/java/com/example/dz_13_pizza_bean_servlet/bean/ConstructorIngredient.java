package com.example.dz_13_pizza_bean_servlet.bean;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Setter
@NoArgsConstructor
public class ConstructorIngredient implements Serializable {
    private int id;
    private int category_id;
    private String name_category;
    private String name_ingredient;
    private boolean multi;

    public int getId() {
        return id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public String getName_category() {
        return name_category;
    }

    public String getName_ingredient() {
        return name_ingredient;
    }

    public boolean getMulti() {
        return multi;
    }
}
