package org.springblade.modules.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 技师访问记录表
 */
@ApiModel(value = "org-springblade-modules-user-entity-TechuserAccessHistory")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "techuser_access_history")
public class TechuserAccessHistory {
    /**
     * 技师编号
     */
    @TableField(value = "techuser_code")
    @ApiModelProperty(value = "技师编号")
    private Integer techuserCode;

    /**
     * 操作类型
     */
    @TableField(value = "op_type")
    @ApiModelProperty(value = "操作类型")
    private String opType;

    /**
     * 操作时间
     */
    @TableField(value = "op_time")
    @ApiModelProperty(value = "操作时间")
    private Date opTime;

    /**
     * 设备类型
     */
    @TableField(value = "device_code")
    @ApiModelProperty(value = "设备类型")
    private String deviceCode;

    /**
     * 时长
     */
    @TableField(value = "min")
    @ApiModelProperty(value = "时长")
    private Long min;

    public static final String COL_TECHUSER_CODE = "techuser_code";

    public static final String COL_OP_TYPE = "op_type";

    public static final String COL_OP_TIME = "op_time";

    public static final String COL_DEVICE_CODE = "device_code";

    public static final String COL_MIN = "min";
}