package org.springblade.modules.user.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springblade.modules.user.entity.JobWork;

/**
 * @Author 元杰
 * @Date 2022/9/14 13:37
 */
@Data
public class JobWorkVo extends JobWork {
	/**
     * 工作类型名称
     */
    @TableField(value = "work_name")
    @ApiModelProperty(value="工作类型名称")
    private String workName;
}
