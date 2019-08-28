package yb.demo.springbootsecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyProvider provider;
    @Autowired
    private MySuccessHandler mySuccessHandler;
    @Autowired
    private MyFailureHandler myFailureHandler;
    @Autowired
    private MyDeniedHandler myDeniedHandler;
    @Autowired
    private MyEntryPoint myEntryPoint;

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .inMemoryAuthentication()
//                .passwordEncoder(new BCryptPasswordEncoder())
//                .withUser("user1")
//                .password(new BCryptPasswordEncoder().encode("123456"))
//                .roles("user","admin");
//    }

    //使用自定义的安全认证（provider）
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(provider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //设置过滤路径（ant风格）以及所需要的权限
        http.authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/user/**").hasRole("admin")
                .antMatchers("/test/**").permitAll()
                .antMatchers("/").authenticated()
                .and()
                //使用表单登录，httpBasic()的话是使用弹窗
                .formLogin()
                //自定义登录页面
                .loginPage("/login")
                //自定义登录处理url
                .loginProcessingUrl("/mylogin")
                //自定义登录成功处理器
                .successHandler(mySuccessHandler)
                //自定义登录失败处理器
                .failureHandler(myFailureHandler)
                .and()
                //关闭csrf(暂时)
                .csrf().disable();
        http.exceptionHandling()
                //自定义权限不足处理器
                .accessDeniedHandler(myDeniedHandler)
                //自定义未登录处理器
                .authenticationEntryPoint(myEntryPoint);
    }
}
