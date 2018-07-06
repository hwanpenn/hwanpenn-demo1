package com.example.hwanpenndemo1.restControllers;

import com.example.hwanpenndemo1.beans.ConfigBean;
import com.example.hwanpenndemo1.beans.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableConfigurationProperties({ConfigBean.class})
public class ConfigTestController {

    @Autowired
    ConfigBean configBean;

    @Autowired
    User user;

    @Value("${my.name}")
    private String name;
    @Value("${my.age}")
    private int age;
    @Value("${my.number}")
    private int number;

    @RequestMapping(value = "/configTest")
    public String configTest(){
        return name+":"+age+":"+number;
    }

    @RequestMapping(value = "/lucy")
    public String lucy(){
        return configBean.getGreeting()+" >>>>"+configBean.getName()+" >>>>"+ configBean.getUuid()+" >>>>"+configBean.getMax();
    }

    @RequestMapping(value = "/user")
    public String user(){
        return user.getName()+user.getAge();
    }

}
