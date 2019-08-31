package yb.demo.springbootjpa.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yb.demo.springbootjpa.dao.UserDao;
import yb.demo.springbootjpa.pojo.User;
import yb.demo.springbootjpa.vo.UserVo;
import javax.persistence.criteria.*;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserDao userDao;

    @PostMapping("user")
    public ResponseEntity insertUser(@RequestBody User user) {
        User save = userDao.save(user);
        return new ResponseEntity(save, HttpStatus.OK);
    }

    @DeleteMapping("user/{id}")
    public ResponseEntity deleteUser(@PathVariable("id") Long id) {
        userDao.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("user/{id}")
    public ResponseEntity updateUser(@RequestBody User user, @PathVariable("id") Long id) {
        user.setId(id);
        User user1 = userDao.save(user);
        return new ResponseEntity(user1, HttpStatus.OK);
    }

    @GetMapping("user/{id}")
    public ResponseEntity findUserById(@PathVariable("id") int id) {
        User user = userDao.getOne(1L);
        System.out.println(user);
        return new ResponseEntity(user, HttpStatus.OK);
    }

    @GetMapping("users")
    public ResponseEntity findAllUsers() {
        List<User> userList = userDao.findAll(new Sort(Sort.Direction.DESC, "age"));
        return new ResponseEntity(userList, HttpStatus.OK);
    }

    @GetMapping("user")
    public ResponseEntity findUserByName(String name) {
        List<User> userList = userDao.findUserByName(name);
        return new ResponseEntity(userList, HttpStatus.OK);
    }

    @GetMapping("user2")
    public ResponseEntity findUserAndDepartment() {
        List<UserVo> userList = userDao.findUserAndDepartment();
        return new ResponseEntity(userList, HttpStatus.OK);
    }


    @GetMapping("user3")
    public ResponseEntity findUserByAge(int age) {
        Sort sort = new Sort(Sort.Direction.DESC, "name");
        //PageRequest pageRequest = PageRequest.of(page,size,sort);
        List<User> userList = userDao.findByAge(age, sort);
        return new ResponseEntity(userList, HttpStatus.OK);
    }


    //使用equal：直接得到path对象（要比较的属性），然后进行比较即可
    //使用like:得到path对象后，使用path.as来指定比较的参数类型，再去比较
    @GetMapping("user4")
    public ResponseEntity MohuSearch(String name, int age, int dep_id) {
        List<User> userList = userDao.findAll(new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                //获取要比较的属性
                Path<Object> name1 = root.get("name");
                Path<Object> age1 = root.get("age");
                Path<Object> dep_id1 = root.get("dep_id");
                //比较条件
                Predicate like = criteriaBuilder.like(name1.as(String.class), "%" + name + "%");
                Predicate equal1 = criteriaBuilder.equal(age1, age);
                Predicate equal = criteriaBuilder.equal(dep_id1, dep_id);
                //查询条件拼接
                Predicate and = criteriaBuilder.and(like, equal1, equal);
                return and;
            }
        });
        return new ResponseEntity(userList, HttpStatus.OK);
    }


    @GetMapping("user5")
    public ResponseEntity findByAge(@RequestParam(required = false) int age) {
        List<User> userList = userDao.findByAge(age);
        return new ResponseEntity(userList, HttpStatus.OK);
    }

    @GetMapping("user6")
    public ResponseEntity findByNameAndAge(String name, int age) {
        List<User> userList = userDao.findByNameAndAge(name, age);
        return new ResponseEntity(userList, HttpStatus.OK);
    }

    @GetMapping("user7")
    public ResponseEntity findByNameLike(String name) {
        List<User> userList = userDao.findByNameLike("%" + name + "%");
        return new ResponseEntity(userList, HttpStatus.OK);
    }

    @GetMapping("user8")
    public ResponseEntity findByNameLikeAndAge(String name, int age) {
        List<User> userList = userDao.findByNameLikeAndAge("%" + name + "%", age);
        return new ResponseEntity(userList, HttpStatus.OK);
    }


    @GetMapping("user9/{id}")
    public ResponseEntity updateUser1(@RequestBody User user,@PathVariable("id") long id){
        int i = userDao.updateUser(user,id);
        return i==1 ? new ResponseEntity("success",HttpStatus.OK) : new ResponseEntity("fail",HttpStatus.OK);
    }


}
