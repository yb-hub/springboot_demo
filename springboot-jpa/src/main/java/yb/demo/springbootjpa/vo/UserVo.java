package yb.demo.springbootjpa.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserVo {

    private Long id;

    private String name;

    private int age;

    private int dep_id;

    private String dep_name;
}
