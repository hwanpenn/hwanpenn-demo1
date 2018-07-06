package com.example.hwanpenndemo1.service;

import com.example.hwanpenndemo1.entity.User;

import java.util.List;

//import com.answer.entity.User;

/**
 * <p></p>
 * Created by zhezhiyong@163.com on 2017/9/21.
 */
public interface UserService {

    List<User> list();

    User findUserById(Long id);

    User findInfoById(Long id);

    void update(User user);

    void remove(Long id);

    User upuser(Long id);

}
