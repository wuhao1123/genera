package com.hao.genera.dto;

import com.hao.genera.entity.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户表数据传输对象实体类
 *
 * @author teddy
 * @since 2019-11-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserDTO extends User {
	private static final long serialVersionUID = 1L;

}
