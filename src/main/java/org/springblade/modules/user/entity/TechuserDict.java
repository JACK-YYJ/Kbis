package org.springblade.modules.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 用户表
 */
@ApiModel(value = "org-springblade-modules-user-entity-TechuserDict")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "techuser_dict")
public class TechuserDict implements Serializable {

	private static final long serialVersionUID = 1L;
    /**
     * 操作技师或管理员姓名
     */
    @TableId(value = "techuser_code", type = IdType.INPUT)
    @ApiModelProperty(value = "操作技师或管理员姓名")
    private Integer techuserCode;

    /**
     * 密码
     */
    @TableField(value = "techuser_pwd")
    @ApiModelProperty(value = "密码")
    private String techuserPwd;

    /**
     * 权限 仅设置两级权限 0技师 1管理员
     */
    @TableField(value = "user_priv")
    @ApiModelProperty(value = "权限 仅设置两级权限 0技师 1管理员")
    private String userPriv;

    /**
     * 状态：0离线，1上班
     */
    @TableField(value = "status")
    @ApiModelProperty(value = "状态：0离线，1上班")
    private String status;

    /**
     * 启用停用状态
     */
    @TableField(value = "is_enabled")
    @ApiModelProperty(value = "启用停用状态 ")
    private Boolean isEnabled;

    @TableField(value = "role_id")
    @ApiModelProperty(value = "")
    private Integer roleId;

    @TableField(value = "token")
    @ApiModelProperty(value = "")
    private String token;

    /**
     * 设备编号
     */
    @TableField(value = "device_code")
    @ApiModelProperty(value = "设备编号")
    private String deviceCode;

    /**
     * ip地址
     */
    @TableField(value = "ipaddr")
    @ApiModelProperty(value = "ip地址")
    private String ipaddr;

    /**
     * 操作系统
     */
    @TableField(value = "os")
    @ApiModelProperty(value = "操作系统")
    private String os;

    public static final String COL_TECHUSER_CODE = "techuser_code";

    public static final String COL_TECHUSER_PWD = "techuser_pwd";

    public static final String COL_USER_PRIV = "user_priv";

    public static final String COL_STATUS = "status";

    public static final String COL_IS_ENABLED = "is_enabled";

    public static final String COL_ROLE_ID = "role_id";

    public static final String COL_TOKEN = "token";

    public static final String COL_DEVICE_CODE = "device_code";

    public static final String COL_IPADDR = "ipaddr";

    public static final String COL_OS = "os";
}
