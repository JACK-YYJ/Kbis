package org.springblade.modules.system.service;

import org.springblade.modules.system.service.impl.FileUploadServiceImpl;
import org.springblade.modules.system.vo.PicUploadResult;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface FileUploadService   {
	//单图片上传
	PicUploadResult uplodadImg(MultipartFile uploadFile, HttpServletRequest request);

	//多图片上传
	PicUploadResult uploadManyImg(MultipartFile[] uploadFile, HttpServletRequest request);

}
