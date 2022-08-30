package org.springblade.modules.system.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author 元杰
 * @Date 2022/8/25 14:59
 */
@Data
public class SysUserDto {
	/**
	 * 工号
	 */
	@TableId(value = "user_code", type = IdType.INPUT)
	@ApiModelProperty(value = "工号")
	private Integer userCode;

	/**
	 * 密码
	 */
	@TableField(value = "user_pwd")
	@ApiModelProperty(value = "密码")
	private String userPwd;
}
