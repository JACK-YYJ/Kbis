package org.springblade.modules.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author 元杰
 * @Date 2022/8/25 11:24
 */

@ApiModel(value = "org-springblade-modules-system-entity-SysToken")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_token")
public class SysToken implements Serializable {
    /**
     * 技师编号
     */
    @TableId(value = "user_code", type = IdType.INPUT)
    @ApiModelProperty(value = "技师编号")
    private Integer userCode;

    /**
     * token
     */
    @TableField(value = "token")
    @ApiModelProperty(value = "token")
    private String token;

    /**
     * 到期时间
     */
    @TableField(value = "expire_time")
    @ApiModelProperty(value = "到期时间")
    private LocalDateTime expireTime;

    /**
     * 更改时间
     */
    @TableField(value = "update_time")
    @ApiModelProperty(value = "更改时间")
    private LocalDateTime updateTime;

    private static final long serialVersionUID = 1L;

    public static final String COL_USER_CODE = "user_code";

    public static final String COL_TOKEN = "token";

    public static final String COL_EXPIRE_TIME = "expire_time";

    public static final String COL_UPDATE_TIME = "update_time";
}
