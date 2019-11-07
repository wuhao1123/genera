package com.hao.genera.base;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 租户基础实体类
 *
 * @author 吴昊
 */
@Data
public class TenantEntity extends BaseEntity {

	/**
	 * 租户ID
	 */
	@ApiModelProperty(value = "租户ID")
	private String tenantId;

}
