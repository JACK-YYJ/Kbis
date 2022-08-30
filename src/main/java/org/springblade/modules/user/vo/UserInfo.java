package org.springblade.modules.user.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName UserInfo
 * @Description TODO
 * @Author Ja'c'k
 * @Dato 2022/6/20 16:58
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer userid;

	private String phone;

	private String username;

	private String priv;

	private Boolean status;

	private List<UserInfo> userInfoList;

}
