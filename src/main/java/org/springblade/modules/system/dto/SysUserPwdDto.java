package org.springblade.modules.system.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author 元杰
 * @Date 2022/8/25 15:33
 */
@Data
public class SysUserPwdDto extends SysUserDto {
	/**
	 * 更新者
	 */
	@TableField(value = "update_by")
	@ApiModelProperty(value = "更新者")
	private String updateBy;
}
