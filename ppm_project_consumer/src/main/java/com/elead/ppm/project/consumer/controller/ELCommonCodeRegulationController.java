package com.elead.ppm.project.consumer.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.elead.platform.common.api.CommonResponse;
import com.elead.platform.common.utils.CodeUtils;
import com.elead.ppm.project.domain.entity.ELCommonCodeRegulation;
import com.elead.ppm.project.domain.service.ELCommonCodeRegulationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/project/commonCodeRegulation")
@Api(tags="编码配置")
public class ELCommonCodeRegulationController {

	@Autowired
	private ELCommonCodeRegulationService regulationService;
	protected Logger logger = LoggerFactory.getLogger(getClass());
	/**
	 * 添加接口@ResponseBody
	 * 
	 * @param record
	 */

	@Valid
	@ApiOperation("新增和修改编码配置")
	@PostMapping(value = "/saveCommonCode", produces = "application/json;charset=UTF-8")	
	public CommonResponse saveCommonCode(@ApiParam(required=true,value="表单提交json数据")@RequestParam(value="elCodeJsonString")@NotNull(message=CodeUtils.EMPTY_CODE)String elCodeJsonString,
			@ApiParam(required=true,value="编码ID")@RequestParam(value="id")@NotNull(message=CodeUtils.EMPTY_CODE)String id
			) {
		String result = "";
		try {
			// 反序列化
			ELCommonCodeRegulation elCommonCode = JSON.parseObject(elCodeJsonString, ELCommonCodeRegulation.class);
			List<ELCommonCodeRegulation> lsCommonCode = regulationService.getCommonCodeByName(elCommonCode.getName());
			if (id == "") {
				// 校验名称是否重复
				if (lsCommonCode != null && lsCommonCode.size() > 0) {
					return CommonResponse.createWrongParamCommonResponse("编码名称已存在，请重新填写！");
				}
				result = regulationService.insertCommonCodeRegulation(elCommonCode);
			} else {
				boolean isClear = false;
				// 校验名称是否重复
				if (lsCommonCode != null && lsCommonCode.size() > 0) {
					for (ELCommonCodeRegulation elCommonCodeRegulation : lsCommonCode) {
						if (!elCommonCodeRegulation.getId().equals(id) ) {
							return CommonResponse.createWrongParamCommonResponse("编码名称已存在，请重新填写！");
						}
						if (elCommonCodeRegulation.getId().equals(id)) {
							if (!elCommonCodeRegulation.getPrefix().equals(elCommonCode.getPrefix())
									|| !elCommonCodeRegulation.getSuffix().equals(elCommonCode.getSuffix())
									|| !elCommonCodeRegulation.getDateType().equals( elCommonCode.getDateType())
									|| elCommonCodeRegulation.getRandomLength() != elCommonCode.getRandomLength()) {
								isClear = true;
							}
						}
					}
					// 如果前缀、后缀、随机数长度和日期格式有修改，则清空编码
					if (isClear) {
						elCommonCode.setCode("");
					}
				}
				elCommonCode.setId(id);
				result = regulationService.updateByPrimaryKeySelective(elCommonCode);
			}

		} catch (Exception e) {
			logger.error("保存项目信息出错", e);
			return CommonResponse.createWrongParamCommonResponse(e.getLocalizedMessage());
		}

		return CommonResponse.createCommonResponse("保存成功！");

	}

	/**
	 * 根据名称获取编码
	 * 
	 * @param name
	 * @return code
	 */
	@Valid
	@ApiOperation("根据名称获取")
	@GetMapping(value = "/GetCode", produces = "application/json;charset=UTF-8")
	public CommonResponse GetCode(@ApiParam(required=true,value="名称")@RequestParam(value="name")@NotNull(message=CodeUtils.EMPTY_CODE)String name) {
		String code = "";
		List<ELCommonCodeRegulation> lsCommonCode = regulationService.getCommonCodeByName(name);
		if (lsCommonCode == null || lsCommonCode.size() == 0)
			return CommonResponse.createCommonResponse();
		ELCommonCodeRegulation commonCode = lsCommonCode.get(0);
		SimpleDateFormat format = new SimpleDateFormat(commonCode.getDateType()); // 时间字符
		String date = format.format(new Date());
		if (commonCode.getPrefix() != "" && commonCode.getSuffix() != "") {
			code = commonCode.getPrefix() + date + GetTmpNumber(commonCode, date) + commonCode.getSuffix(); // 组合流水号前一部
		} else if (commonCode.getPrefix() != "") {
			code = commonCode.getPrefix() + date + GetTmpNumber(commonCode, date);
		} else {
			code = date + GetTmpNumber(commonCode, date) + commonCode.getSuffix(); // 组合流水号前一部
		}
		// 更新最新编码
		commonCode.setCode(code);
		regulationService.updateByPrimaryKeySelective(commonCode);
		return CommonResponse.createCommonResponse(code);
	}

	/**
	 * 获取编码
	 * 
	 * @param ELCommonCodeRegulation
	 * @return 最大编码+1
	 */
	private String GetTmpNumber(ELCommonCodeRegulation commonCode, String date) {
		int codeNumber = 0;
		if (null != commonCode.getCode() && commonCode.getCode() != "") {
			// 获取数字
			int start = 0;
			int code_end = 0;
			String oldDate = "";
			if (commonCode.getPrefix() != "" && commonCode.getSuffix() != "") {
				// 编码有前后缀,把String类型的0001转化为int类型的1
				start = commonCode.getPrefix().length() + commonCode.getDateType().length();
				code_end = Integer.parseInt(commonCode.getCode().substring(start,
						(commonCode.getCode().length() - commonCode.getSuffix().length())));
				oldDate = commonCode.getCode().substring(commonCode.getPrefix().length(),
						(commonCode.getPrefix().length() + commonCode.getDateType().length()));

			} else if (commonCode.getPrefix() != null) {
				// 只有前缀,把String类型的0001转化为int类型的1
				start = commonCode.getPrefix().length() + commonCode.getDateType().length();
				code_end = Integer.parseInt(commonCode.getCode().substring(start, commonCode.getCode().length()));
				oldDate = commonCode.getCode().substring(commonCode.getPrefix().length(),
						(commonCode.getPrefix().length() + commonCode.getDateType().length()));
			} else {
				// 只有后缀,把String类型的0001转化为int类型的1
				start = commonCode.getDateType().length();
				code_end = Integer.parseInt(commonCode.getCode().substring(start,
						(commonCode.getCode().length() - commonCode.getSuffix().length())));
				oldDate = commonCode.getCode().substring(0, commonCode.getDateType().length());
			}
			if (oldDate.equals(date)) {
				codeNumber = code_end + 1;
			} else {
				codeNumber = 1;
			}
		} else {
			codeNumber = 1;
		}
		String numberStr = "000000000";
		if (("" + codeNumber).length() < commonCode.getRandomLength()) {
			return numberStr.substring(1, (commonCode.getRandomLength() - ("" + codeNumber).length()))
					+ ("" + codeNumber);
		} else {
			return ("" + codeNumber);
		}
	}

	/*
	 * 模糊搜索接口
	 * 
	 * @param response
	 * 
	 * @param page pageNum
	 * 
	 * @param rows pageSize
	 * 
	 * @param sort 排序字段
	 * 
	 * @param order 排序方式
	 */
	@ApiOperation("搜索编码配置列表数据")
	@RequestMapping("/search")
	public Map<String, Object> search( String name, int page, int rows, String sort,
			String order) {

		return regulationService.search(name, page, rows, sort, order);
	}

	/**
	 * 加载列表接口
	 * 
	 * @param response
	 * @param page
	 *            pageNum
	 * @param rows
	 *            pageSize
	 * @param sort
	 *            排序字段
	 * @param order
	 *            排序方式
	 * @return
	 */
	@ApiOperation("获取编码配置列表数据")
	@RequestMapping("/loadList")
	public Map<String, Object> loadList(int page, int rows, String sort, String order) {

	
		return regulationService.loadList(page, rows, sort, order);

	}

	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	@Valid
	@ApiOperation("删除编码配置数据")
	@PostMapping(value = "/deleteCodeRegulation", produces = "application/json;charset=UTF-8")
	public CommonResponse deleteCodeRegulation(
			@ApiParam(required=true,value="编码ID，多个用逗号分隔")@RequestParam(value="arrayId")@NotNull(message=CodeUtils.EMPTY_CODE)String arrayId) {
        if (arrayId == "")
			return CommonResponse.createWrongParamCommonResponse("删除失败");
		try {
			String[] listId = arrayId.split(",");
			for (String id : listId) {
				regulationService.deleteCodeRegulation(id);
			}
		} catch (Exception e) {
			return CommonResponse.createWrongParamCommonResponse(e.getLocalizedMessage());
		}
		return CommonResponse.createCommonResponse("删除成功");

	}

}
