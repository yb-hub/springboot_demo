package yb.demo.springbootcache.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import yb.demo.springbootcache.model.User;

@Mapper
public interface UserMapper {
    @Select("select * from user where id = #{id}")
    User getUserById(int id);
}
