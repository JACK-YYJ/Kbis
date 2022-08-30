package org.springblade.modules.system.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author 元杰
 * @Date 2022/8/25 11:24
 */

@ApiModel(value = "org-springblade-modules-system-entity-SysUser")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_user")
public class SysUser implements Serializable {
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

    /**
     * 创建者
     */
    @TableField(value = "create_by")
    @ApiModelProperty(value = "创建者")
    private String createBy;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 更新者
     */
    @TableField(value = "update_by")
    @ApiModelProperty(value = "更新者")
    private String updateBy;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

	/**
	 * 1删除  默认0
	 */
	@TableLogic
	@TableField(value = "is_deleted")
	@ApiModelProperty(value = "1删除  默认0")
	private Byte isDeleted;

    private static final long serialVersionUID = 1L;

    public static final String COL_USER_CODE = "user_code";

    public static final String COL_USER_PWD = "user_pwd";

    public static final String COL_CREATE_BY = "create_by";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_BY = "update_by";

    public static final String COL_UPDATE_TIME = "update_time";
}
