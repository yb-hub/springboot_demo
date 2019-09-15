package yb.demo.springbootshiro.shiro;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * @Auther: Yang
 * @Date: 2019/9/14 0014 20:39
 * @Description:
 */
public class AdminAuthorizingRealm extends AuthorizingRealm {
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        if (principals == null) {
            throw new AuthorizationException("PrincipalCollection method argument cannot be null.");
        }
//        UmsAdmin admin = (UmsAdmin) getAvailablePrincipal(principals);
//        Integer[] roleIds = admin.getRoleIds();
//        Set<String> roles = roleService.queryByIds(roleIds);
//        Set<String> permissions = permissionService.queryByRoleIds(roleIds);
        Set<String> roles = new HashSet<>();  //从数据库中查询出roles
        Set<String> permissions = new HashSet<>();//从数据库中查询出permissions
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setRoles(roles);
        info.setStringPermissions(permissions);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        String username = upToken.getUsername();
        String password = new String(upToken.getPassword());

        if (StringUtils.isEmpty(username)) {
            throw new AccountException("用户名不能为空");
        }
        if (StringUtils.isEmpty(password)) {
            throw new AccountException("密码不能为空");
        }

        //19e0ab03098e9ffb6485df82d85307e998b799d23c3d77b9e47fce4bb2575b6d
        String password2 = "19e0ab03098e9ffb6485df82d85307e998b799d23c3d77b9e47fce4bb2575b6d";//从数据库中查找

        //principal:用户主体（代表用户，可用id,username,或者一个实例对象）
        //credentials:用户凭证（使用从数据库中查询出来的密码）
        //realmName:获取主体和凭据的域：调用父类方法getName()即可获得。
        //这里生成的SimpleAuthenticationInfo

        ByteSource salt = ByteSource.Util.bytes(username);
        return new SimpleAuthenticationInfo(username, password2, salt,getName());
    }

}
