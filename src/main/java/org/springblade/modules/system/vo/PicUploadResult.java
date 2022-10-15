package org.springblade.modules.system.vo;

import lombok.Data;

import java.util.List;
@Data
public class PicUploadResult {
	private boolean isLegal;

	private String imgPath;

	private List<String> imgPahts;
}
