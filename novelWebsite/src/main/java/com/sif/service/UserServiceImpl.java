package com.sif.service;

import com.sif.mapper.UserMapper;
import com.sif.pojo.User;
import com.sif.pojo.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserMapper userMapper;
    public void insertUserSelective(User user){
        userMapper.insertSelective(user);
    }

    /*
        按照umail查询用户
     */
    @Override
    public User selectUserByUmail(String umail) {
        return userMapper.selectUserByuUmail(umail);
    }
    /*
        按照账号密码匹配用户
     */
    @Override
    public User selectUserByIntro(User user) {
        System.out.println(user.toString());
        return userMapper.selectUserByIntro(user);
    }

    /*匹配激活码*/
    @Override
    public User selectUserByCode(String code) {
        return userMapper.selectUserByCode(code);
    }

    /*激活用户*/
    @Override
    public void updateUstateByUid(String uid) {
        userMapper.updateUstateByUid(uid);
    }

    /*查看是不是作者*/
    @Override
    public Integer selectUisAuthorByUid(String uid) {
        return userMapper.selectUisAuthorByUid(uid);
    }

    /*激活作者*/
    @Override
    public void updateUisAuthorByUid(String uid) {
        userMapper.updateUisAuthorByUid(uid);
    }

    @Override
    public User selectUserByUsername(String username) {
        return userMapper.selectUserByUsername(username);
    }

}
