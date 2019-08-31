package yb.demo.springbootjpa.dao;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import yb.demo.springbootjpa.pojo.User;
import yb.demo.springbootjpa.vo.UserVo;

import javax.transaction.Transactional;
import java.util.List;

//@Repository
public interface UserDao extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    List<User> findByAge(int age);

    List<User> findByNameAndAge(String name, int age);

    List<User> findByNameLike(String name);

    List<User> findByNameLikeAndAge(String name, int age);

    List<User> findByAge(int age, Sort sort);

    //使用sql语句
    @Query(nativeQuery = true, value = "select * from tb_user u where u.u_name = :name")
    List<User> findUserByName(@Param("name") String name);
    //使用jpql语句
    @Query(value = "select new yb.demo.springbootjpa.vo.UserVo(u.id,u.name,u.age,u.dep_id,d.name) from User u, yb.demo.springbootjpa.pojo.Department d where u.dep_id = d.id")
    List<UserVo> findUserAndDepartment();

    @Query(value = "update User u set u.name=:#{#user.name},u.age=:#{#user.age},u.dep_id=:#{#user.dep_id} where u.id = :id")
    @Modifying
    @Transactional
    int updateUser(@Param("user") User user, @Param("id") Long id);
}
