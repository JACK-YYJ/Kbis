package org.springblade.modules.user.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @Author 元杰
 * @Date 2022/9/8 17:38
 */

/**
 * 用户表
 */
@ApiModel(value = "org-springblade-modules-user-entity-User")
@Data
@Setter
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "dict_user")
public class User implements Serializable {
	/**
	 * 用户id
	 */
	@TableId(value = "u_id", type = IdType.INPUT)
	@ApiModelProperty(value="用户id")
	private Integer uId;

	/**
	 * 工号
	 */
	@TableField(value = "user_code")
	@ApiModelProperty(value="工号")
	private Integer userCode;

	/**
	 * 姓名
	 */
	@TableField(value = "user_name")
	@ApiModelProperty(value="姓名")
	private String userName;

	/**
	 * 身份证
	 */
	@TableField(value = "card_id")
	@ApiModelProperty(value="身份证")
	private String cardId;

	/**
	 * 岗位id
	 */
	@TableField(value = "j_id")
	@ApiModelProperty(value="岗位id")
	private Integer jId;

	/**
	 * 岗位名称
	 */
	@TableField(value = "job_name")
	@ApiModelProperty(value="岗位名称")
	private String jobName;

	/**
	 * 职称id
	 */
	@TableField(value = "p_id")
	@ApiModelProperty(value="职称id")
	private Integer pId;

	/**
	 * 职称名称
	 */
	@TableField(value = "position_name")
	@ApiModelProperty(value="职称名称")
	private String positionName;

	/**
	 * 学历id
	 */
	@TableField(value = "d_id")
	@ApiModelProperty(value="学历id")
	private Integer dId;

	/**
	 * 学历名称
	 */
	@TableField(value = "degree_name")
	@ApiModelProperty(value="学历名称")
	private String degreeName;

	/**
	 * 入职日期
	 */
	@TableField(value = "seniority")
	@ApiModelProperty(value="入职日期")
	private Date seniority;

	/**
	 * 岗位证id
	 */
	@TableField(value = "jc_id")
	@ApiModelProperty(value="岗位证id")
	private Integer jcId;

	/**
	 * 岗位证名称
	 */
	@TableField(value = "job_certificate_name")
	@ApiModelProperty(value="岗位证名称")
	private String jobCertificateName;

	/**
	 * 个人绩效合计
	 */
	@TableField(value = "kpi_sum_price")
	@ApiModelProperty(value="个人绩效合计")
	private BigDecimal kpiSumPrice;

	/**
	 * 创建者
	 */
	@TableField(value = "create_by")
	@ApiModelProperty(value="创建者")
	private String createBy;

	/**
	 * 创建时间
	 */
	@TableField(value = "create_time")
	@ApiModelProperty(value="创建时间")
	private Date createTime;

	/**
	 * 更新者
	 */
	@TableField(value = "update_by")
	@ApiModelProperty(value="更新者")
	private String updateBy;

	/**
	 * 更新时间
	 */
	@TableField(value = "update_time")
	@ApiModelProperty(value="更新时间")
	private Date updateTime;

	/**
	 * 1删除  默认0
	 */
//	@TableLogic
	@TableField(value = "is_deleted")
	@ApiModelProperty(value="1删除  默认0")
	private Byte isDeleted;

	private static final long serialVersionUID = 1L;

	public static final String COL_U_ID = "u_id";

	public static final String COL_USER_CODE = "user_code";

	public static final String COL_USER_NAME = "user_name";

	public static final String COL_CARD_ID = "card_id";

	public static final String COL_J_ID = "j_id";

	public static final String COL_JOB_NAME = "job_name";

	public static final String COL_P_ID = "p_id";

	public static final String COL_POSITION_NAME = "position_name";

	public static final String COL_D_ID = "d_id";

	public static final String COL_DEGREE_NAME = "degree_name";

	public static final String COL_SENIORITY = "seniority";

	public static final String COL_JC_ID = "jc_id";

	public static final String COL_JOB_CERTIFICATE_NAME = "job_certificate_name";

	public static final String COL_KPI_SUM_PRICE = "kpi_sum_price";

	public static final String COL_CREATE_BY = "create_by";

	public static final String COL_CREATE_TIME = "create_time";

	public static final String COL_UPDATE_BY = "update_by";

	public static final String COL_UPDATE_TIME = "update_time";

	public static final String COL_IS_DELETED = "is_deleted";



}
