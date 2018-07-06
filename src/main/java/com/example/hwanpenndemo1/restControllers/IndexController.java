package com.example.hwanpenndemo1.restControllers;

import java.util.List;
import java.util.Map;

import com.example.hwanpenndemo1.entity.User;
import com.example.hwanpenndemo1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//import com.answer.entity.User;
//import com.answer.service.UserService;
import com.google.common.collect.ImmutableMap;

/**
 * <p></p>
 * Created by zhezhiyong@163.com on 2017/9/21.
 */
@RestController
@RequestMapping(value = "/cache")
public class IndexController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    @ResponseBody
    public List<User> users() {
        return userService.list();
    }

    @GetMapping("/user/{id}")
    @ResponseBody
    public User findUserById(@PathVariable("id") Long id) {
        System.out.println("into controller");
        return userService.findUserById(id);
    }

    @GetMapping("/upuser/{id}")
    @ResponseBody
    public User upuser(@PathVariable("id") Long id) {
        return userService.upuser(id);
    }

    @GetMapping("/info/{id}")
    @ResponseBody
    public User findInfoById(@PathVariable("id") Long id) {
        return userService.findInfoById(id);
    }

    @GetMapping("/user/{id}/{name}")
    @ResponseBody
    public Map update(@PathVariable("id") Long id, @PathVariable("name") String name) {
        User user = userService.findUserById(id);
        user.setName(name);
        userService.update(user);
        return ImmutableMap.of("ret", 0, "msg", "ok");
    }


}
