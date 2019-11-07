package com.hao.genera.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.hao.genera.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 用户表实体类
 *
 * @author teddy
 * @since 2019-11-07
 */
@Data
@TableName("test_user")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "User对象", description = "用户表")
public class User extends BaseEntity {

    private static final long serialVersionUID = 1L;

  @TableId(value = "id", type = IdType.AUTO)
  private Integer id;
    /**
     * 用户名
     */
  @ApiModelProperty(value = "用户名")
  private String username;
    /**
     * 联系电话
     */
  @ApiModelProperty(value = "联系电话")
  private String mobile;
    /**
     * 邮箱
     */
  @ApiModelProperty(value = "邮箱")
  private String email;
    /**
     * 密码
     */
  @ApiModelProperty(value = "密码")
  private String password;
    /**
     * 余额
     */
  @ApiModelProperty(value = "余额")
  private BigDecimal balance;


}
