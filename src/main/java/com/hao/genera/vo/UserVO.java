package com.hao.genera.vo;

import com.hao.genera.entity.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;

/**
 * 用户表视图实体类
 *
 * @author teddy
 * @since 2019-11-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "UserVO对象", description = "用户表")
public class UserVO extends User {
	private static final long serialVersionUID = 1L;

	private String token;
}
