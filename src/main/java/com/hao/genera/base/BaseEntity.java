package com.hao.genera.base;


import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.hao.genera.utils.DateUtil;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 基础实体类
 *
 * @author 吴昊
 */
@Data
public class BaseEntity implements Serializable {



	/**
	 * 创建时间
	 */
	@DateTimeFormat(pattern = DateUtil.PATTERN_DATETIME)
	@JsonFormat(pattern = DateUtil.PATTERN_DATETIME)
	@ApiModelProperty(value = "创建时间")
	private Date created;


	/**
	 * 更新时间
	 */
	@DateTimeFormat(pattern = DateUtil.PATTERN_DATETIME)
	@JsonFormat(pattern = DateUtil.PATTERN_DATETIME)
	@ApiModelProperty(value = "更新时间")
	private Date updated;

	/**
	 * 状态[0:未删除,1:删除]
	 */
	@TableLogic
	@ApiModelProperty(value = "是否已删除")
	private Integer deleted;
}
