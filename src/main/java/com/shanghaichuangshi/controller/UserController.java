package com.shanghaichuangshi.controller;

import com.shanghaichuangshi.model.User;
import com.shanghaichuangshi.service.UserService;

import java.util.List;
import java.util.Map;

public class UserController extends Controller {

    private UserService userService = new UserService();

    public List<User> list() {
        User userMap = new User();
        userMap.set(getAttribute());
        userMap.validate(User.PAGE_INDEX, User.PAGE_SIZE);

        return userService.list(userMap);
    }

    public List<User> listForAdmin() {
        User userMap = new User();
        userMap.set(getAttribute());
        userMap.validate(User.PAGE_INDEX, User.PAGE_SIZE);

        return userService.list(userMap);
    }

    public User find() {
        User userMap = new User();
        userMap.set(getAttribute());
        userMap.validate(User.USER_ID);

        return userService.find(userMap);
    }

    public User findForAdmin() {
        User userMap = new User();
        userMap.set(getAttribute());
        userMap.validate(User.USER_ID);

        return userService.findForAdmin(userMap);
    }

    public void save() {
        //user.save();
    }

    public void update() {

    }

    public void delete() {

    }

}
