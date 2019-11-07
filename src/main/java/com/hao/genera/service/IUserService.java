package com.hao.genera.service;

import com.hao.genera.entity.User;
import com.hao.genera.vo.UserVO;
import com.hao.genera.base.BaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * 用户表 服务类
 *
 * @author teddy
 * @since 2019-11-07
 */
public interface IUserService extends BaseService<User> {

    UserVO login(String username, String password);
}
