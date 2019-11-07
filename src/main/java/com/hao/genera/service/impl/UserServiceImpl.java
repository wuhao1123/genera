package com.hao.genera.service.impl;

import com.hao.genera.entity.User;
import com.hao.genera.exception.ValidateException;
import com.hao.genera.vo.UserVO;
import com.hao.genera.mapper.UserMapper;
import com.hao.genera.service.IUserService;
import com.hao.genera.base.BaseServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * 用户表 服务实现类
 *
 * @author teddy
 * @since 2019-11-07
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserVO login(String username, String password) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try{
            subject.login(token);
        }catch (Exception e){
            throw new ValidateException("用户名或密码错误");
        }
        if(subject.isAuthenticated()){
            UserVO manageInfoVo = (UserVO) subject.getPrincipal();
            Subject subjectToken = SecurityUtils.getSubject();
            manageInfoVo.setToken((String)subjectToken.getSession().getId());
            return manageInfoVo;
        }else {
            throw new ValidateException("用户名或密码错误，登录失败。");
        }
    }
}
