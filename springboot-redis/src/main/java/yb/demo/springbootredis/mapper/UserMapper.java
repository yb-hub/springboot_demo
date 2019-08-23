package yb.demo.springbootredis.mapper;

import org.apache.ibatis.annotations.*;
import yb.demo.springbootredis.model.User;


import java.util.List;

@Mapper
public interface UserMapper {
    @Select("select * from user where id = #{id}")
    User getUserById(int id);
    @Select("select * from user")
    List<User> getUserList();
    @Select("select * from user where user.name=#{name} and user.age=#{age}")
    List<User> getUserListByNameAndAge(String name, int age);
    @Update("update user set name=#{user.name},age=#{user.age} where id = #{id}")
    int updateUser(int id, User user);
    @Delete("delete from user where user.id=#{id}")
    int deletUser(int id);
    @Insert("insert into user (name,age) values (#{name},#{age})")
    @Options(useGeneratedKeys=true,keyProperty = "id",keyColumn = "id")
    int insertUser(User user);
}
