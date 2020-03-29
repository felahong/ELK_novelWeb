package com.sif.service;

import com.sif.pojo.User;

public interface UserService {
    /*
        按照部分信息插入用户
     */
    public void insertUserSelective(User user);

    /*
        按照邮箱查找用户
     */
    User selectUserByUmail(String umail);

    /*
        按照账号密码匹配用户
     */
    User selectUserByIntro(User user);

    User selectUserByCode(String code);
    /*
        激活用户
    * */
    void updateUstateByUid(String uid);

    Integer selectUisAuthorByUid(String uid);

    void updateUisAuthorByUid(String uid);

    /*根据用户名判断用户*/
    User selectUserByUsername(String username);
}
