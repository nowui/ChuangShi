package com.shanghaichuangshi.controller;

import com.shanghaichuangshi.annotation.Column;
import com.shanghaichuangshi.annotation.Comment;
import com.shanghaichuangshi.annotation.Path;
import com.shanghaichuangshi.model.User;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ApplicationController extends Controller {

    @Path("/app/index")
    public void index() {
        User user = new User().find("select * from table_user", new ArrayList<Object>());
        user.keep(User.COLUMN_USER_ID);

        List<User> userList = new User().list("select * from table_user", new ArrayList<Object>());
        for (User u : userList) {
            u.keep(User.COLUMN_USER_ID);
        }

        Field[] fields = User.class.getDeclaredFields();
        for(Field field : fields) {
            Column column = field.getAnnotation(Column.class);
            Comment comment = field.getAnnotation(Comment.class);

            try {
                System.out.println(field.getName() + " " + field.get(User.class) + " " + comment.value());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        renderJson(0, userList);
    }

    @Path("/app/detail")
    public void detail() {

        renderJson("ApplicationController detail");
    }

}
