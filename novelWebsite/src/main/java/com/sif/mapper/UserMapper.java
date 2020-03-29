package com.sif.mapper;

import com.sif.pojo.User;
import com.sif.pojo.UserExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    long countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(String uid);

    int insert(User record);

    int insertSelective(User record);

    void updateUstateByUid(String uid);

    /*查看是不是作者*/
    Integer selectUisAuthorByUid(String uid);

    /*激活作者*/
    void updateUisAuthorByUid(String uid);

    /*
        按照umail查User
    */
    User selectUserByuUmail(String umail);
    /*
        按照账号密码匹配用户
    */
    User selectUserByIntro(User user);
    /*
        匹配激活码
    */
    User selectUserByCode(String ucode);
    /*根据用户名查询用户*/
    User selectUserByUsername(String username);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(String uid);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);


}