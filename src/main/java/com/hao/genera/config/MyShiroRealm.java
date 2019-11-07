package com.hao.genera.config;

import com.hao.genera.entity.User;
import com.hao.genera.mapper.UserMapper;
import com.hao.genera.support.Condition;
import com.hao.genera.utils.Func;
import com.hao.genera.vo.UserVO;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * @Description:
 * @author: 吴昊
 * @date: 2019/11/5
 */

public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    UserMapper adminUserMapper;

    /**
     * 认证信息，主要针对用户登录，
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        UsernamePasswordToken utoken = (UsernamePasswordToken) authcToken;
        User adminUser = new User();
        adminUser.setUsername(utoken.getUsername());
        adminUser.setPassword(new String(utoken.getPassword()));
        //根据账号密码查用户信息
        User user = adminUserMapper.selectOne(Condition.getQueryWrapper(adminUser));
        if (null == user) {
            throw new AccountException("账号不存在！");
        } else if (!StringUtils.equals(user.getPassword(), adminUser.getPassword())) {
            throw new AccountException("密码不正确！");
        }
        UserVO userVO = new UserVO();
        Func.copy(user,userVO);
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(
                userVO,// 用户名
                user.getPassword(), // 密码
                ByteSource.Util.bytes(user.getUsername()),
                getName());
        return simpleAuthenticationInfo;
    }

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        UserVO manageInfoVo = (UserVO) principals.getPrimaryPrincipal();
        //根据用户userName查询权限（permission) 此处省略sql写固定权限
        Set<String> permissions = new HashSet<>();
        if ("string".equals(manageInfoVo.getUsername())) {
            permissions.add("test:shiro");
        }
        info.setStringPermissions(permissions);
        return info;
    }
}
