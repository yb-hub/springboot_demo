package yb.demo.springbootsecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class MyProvider implements AuthenticationProvider {

    @Autowired
    private MyUserDetailService userDetailService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        //获取表单输入的用户名
        String username = (String) authentication.getPrincipal();
        //获取密码
        String password = (String) authentication.getCredentials();

        UserDetails userDetails = userDetailService.loadUserByUsername(username);

        if(!userDetails.getPassword().equals(password))
        {
            throw new BadCredentialsException("密码错误");
        }

        return new UsernamePasswordAuthenticationToken(username,password,userDetails.getAuthorities());


    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
