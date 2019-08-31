package yb.demo.springbootjpa.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import javax.persistence.*;


@Entity
@Table(name = "tb_user")
@Data
@ToString
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "u_id")
    private Long id;
    @Column(name = "u_name")
    private String name;
    @Column(name = "u_age")
    private int age;
    @Column(name = "dep_id")
    private int dep_id;
}
