package org.springblade.modules.system.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Map;

/**
 * @Author 元杰
 * @Date 2022/10/14 11:52
 */
@Data
public class UserInfo {
	/**
	 * 工号
	 */
	@TableId(value = "user_code", type = IdType.INPUT)
	@ApiModelProperty(value = "工号")
	private Integer userCode;

	/**
	 * 创建者
	 */
	@TableField(value = "create_by")
	@ApiModelProperty(value = "创建者")
	private String createBy;


	private  String  token;


}
