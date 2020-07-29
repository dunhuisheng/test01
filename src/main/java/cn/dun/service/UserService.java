package cn.dun.service;

import cn.dun.bean.User;

public interface UserService {

    User checkUser(String username, String password);
}
