package cn.dun.service;

import cn.dun.bean.User;
import cn.dun.dao.UserRepository;
import cn.dun.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User checkUser(String username, String password) {

        User user = userRepository.findByUsernameAndPassword(username, MD5Utils.code(password));
        return user;
    }



    public void test(){
        System.out.println("111");
    }

}
