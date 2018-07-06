package com.example.libary;


import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * Created by fangzhipeng on 2017/4/19.
 */
@Component
//@Repository
public class Service {

    private final String message;

    public Service(String message) {
        this.message = message;
    }

    public String message() {
        return this.message;
    }
}
