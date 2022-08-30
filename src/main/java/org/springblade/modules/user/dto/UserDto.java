package org.springblade.modules.user.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author 元杰
 * @Date 2022/8/23 17:19
 */
@Data
public class UserDto {
    /**
     * 工号 or 姓名
     */
    @ApiModelProperty(value = "工号 or 姓名")
    private String userCodeOrName;

    /**
     * 岗位id
     */

    @ApiModelProperty(value="岗位id")
    private Integer jId;

    /**
     * 职称id
     */

    @ApiModelProperty(value="职称id")
    private Integer pId;
    /**
     * 学历id
     */

    @ApiModelProperty(value="学历id")
    private Integer dId;
    @ApiModelProperty("当前页")
    private Integer current;
    @ApiModelProperty("每页的数量")
    private Integer size;

}
