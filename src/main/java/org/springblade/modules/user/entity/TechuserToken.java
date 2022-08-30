package org.springblade.modules.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "org-springblade-modules-user-entity-TechuserToken")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "user_token")
public class TechuserToken {
    public static final String COL_IPDATE_TIME = "ipdate_time";
    /**
     * 技师编号
     */
    @TableId(value = "user_code" , type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "技师编号")
    private Integer userCode;

    @TableField(value = "token")
    @ApiModelProperty(value = "")
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

    public static final String COL_TECHUSER_CODE = "techuser_code";

    public static final String COL_TOKEN = "token";

    public static final String COL_DEVICE_CODE = "device_code";

    public static final String COL_EXPIRE_TIME = "expire_time";

    public static final String COL_UPDATE_TIME = "update_time";
}
