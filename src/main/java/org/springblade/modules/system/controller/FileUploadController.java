package org.springblade.modules.system.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springblade.core.tool.api.R;
import org.springblade.modules.system.service.FileUploadService;
import org.springblade.modules.system.vo.PicUploadResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
@RestController
@RequestMapping("/api/upImage")
@Api(tags = "图片上传")
@CrossOrigin
public class FileUploadController {
	// 允许上传的格式 图片形式
	private static final String[] IMAGE_TYPE = new String[]{".bmp", ".jpg", ".jpeg", ".png"};

	@Resource
	private FileUploadService fileUploadService;

	@PostMapping("/uploadImg")
	@ApiOperation("单个图片")
	public R uploadImg(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
		boolean isFlag = false;
		for (String type : IMAGE_TYPE) {
			System.out.println(file.getOriginalFilename());
			if (StringUtils.endsWithIgnoreCase(file.getOriginalFilename(), type)) {
				isFlag = true;
				break;
			}
		}

		if (isFlag) {
			PicUploadResult picUploadResult = fileUploadService.uplodadImg(file, request);
			boolean isLegal = picUploadResult.isLegal();

			if (isLegal) {
				Map resMap = new HashMap<>();
				resMap.put("imgPath", picUploadResult.getImgPath());
				return R.data(resMap);
			} else {
				return R.fail("图片上传有误");
			}
		} else {
			return R.fail("上传的图片格式必须为:bmp,jpg,jpeg,png");
		}

	}

	@PostMapping("/uploadManyImg")
	@ApiOperation("多个图片")
	public R uploadManyImg(@RequestParam("files") MultipartFile[] files, HttpServletRequest request) {
		boolean isFlag = false;
		for (MultipartFile uploadFile : files) {
			for (String type : IMAGE_TYPE) {
				if (StringUtils.endsWithIgnoreCase(uploadFile.getOriginalFilename(), type)) {
					isFlag = true;
					break;
				}
			}
		}

		if (isFlag) {
			PicUploadResult picUploadResult = fileUploadService.uploadManyImg(files, request);
			boolean isLegal = picUploadResult.isLegal();

			if (isLegal) {
				Map resMap = new HashMap<>();
				resMap.put("imgPaths", picUploadResult.getImgPahts());
				return R.data(resMap);
			} else {
				return R.fail("图片上传有误");
			}
		} else {
			return R.fail("上传的图片格式必须为:bmp,jpg,jpeg,png");
		}
	}

}
