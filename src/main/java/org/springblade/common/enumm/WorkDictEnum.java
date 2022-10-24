package org.springblade.common.enumm;

import lombok.Getter;

@Getter
public enum WorkDictEnum {
	PING_PIAN(1,"平片"),
	CHANG_WI_TE_JIAN(2,"肠胃/特检"),
	RU_XIAN(3,"乳腺"),
	CT_PING_SHAO(4,"CT平扫"),
	CT_ZHENG_QIANG(5,"CT增强"),
	CT_SAN_WEI_DING_WEI(6,"CT三维/定位"),
	CT_XUE_GUAN(7,"CT血管"),
	MR_PING_SHAO(8,"MR平扫"),
	MR_ZHENG_QIANG(9,"MR增强"),
	GONG_NENG_MR_CHENG_XIANG(10,"功能MR成像"),
	MR_YU_XIAN_XIN_ZHANG(11,"MR乳腺/心脏");

	private int code;
	private String type;


	WorkDictEnum(int code,String type){
		this.code=code;
		this.type=type;
	}
}
