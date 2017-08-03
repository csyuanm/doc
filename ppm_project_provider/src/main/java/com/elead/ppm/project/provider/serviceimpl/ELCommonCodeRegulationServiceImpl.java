package com.elead.ppm.project.provider.serviceimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.elead.platform.common.api.CommonResponse;
import com.elead.platform.common.service.service.CrudService;
import com.elead.platform.common.utils.JsonMapper;
import com.elead.ppm.project.domain.entity.ELCommonCodeRegulation;
import com.elead.ppm.project.domain.service.ELCommonCodeRegulationService;
import com.elead.ppm.project.provider.dao.ELCommonCodeRegulationDao;

/**
 * service实现类
 * 
 * @author Administrator
 *
 */
@Service(value = "elCommonCodeRegulationService")
@Transactional(readOnly = true)
public class ELCommonCodeRegulationServiceImpl extends CrudService<ELCommonCodeRegulationDao, ELCommonCodeRegulation>
		implements ELCommonCodeRegulationService {

	@Autowired
	private ELCommonCodeRegulationDao regulationDao;

	/**
	 * 添加
	 * 
	 * @param record
	 *            ELCommonCodeRegulation对象
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class, readOnly = false)
	@Override
	public String insertCommonCodeRegulation(ELCommonCodeRegulation record) {

		UUID uuid = UUID.randomUUID();
		record.setId(uuid.toString().replace("-", ""));
		try {

			regulationDao.insert(record);
		} catch (Exception e) {
			logger.error("保存项目信息出错", e);
			return JsonMapper.toJsonString(CommonResponse.createWrongParamCommonResponse(e.getLocalizedMessage()));
		}
		return JsonMapper.toJsonString(CommonResponse.createCommonResponse(record));

	}

	/**
	 * 修改
	 * 
	 * @param record
	 *            ELCommonCodeRegulation对象
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class, readOnly = false)
	@Override
	public String updateByPrimaryKeySelective(ELCommonCodeRegulation record) {
		try {
			regulationDao.updateByPrimaryKeySelective(record);
		} catch (Exception e) {
			logger.error("保存项目信息出错", e);
			return JsonMapper.toJsonString(CommonResponse.createWrongParamCommonResponse(e.getLocalizedMessage()));
		}
		return JsonMapper.toJsonString(CommonResponse.createCommonResponse(record));

	}

	/**
	 * 模糊查询(name)
	 * 
	 * @param name
	 * @return
	 */
	@Transactional(readOnly = true)
	@Override
	public List<ELCommonCodeRegulation> getCommonCodeByName(String name) {
		List<ELCommonCodeRegulation> ls = regulationDao.getCommonCodeByName(name);
		return ls;
	}

	@Transactional(readOnly = true)
	@Override
	public Map<String, Object> search(String name, int pageNum, int pageSize, String sort, String order) {

		name = "%" + name + "%";

		Map<String, Object> resultMap = new HashMap<String, Object>();

		int startIndex = (pageNum - 1) * pageSize;
		int total = regulationDao.queryTotal("search", name);

		resultMap.put("rows", getDataUtil(regulationDao.findByName(name, startIndex, pageSize, sort, order)));
		resultMap.put("total", total);

		return resultMap;

	}

	/**
	 * 加载列表
	 */
	@Transactional(readOnly = true)
	@Override
	public Map<String, Object> loadList(int pageNum, int pageSize, String sort, String order) {

		Map<String, Object> resultMap = new HashMap<String, Object>();

		int startIndex = (pageNum - 1) * pageSize;
		int total = regulationDao.queryTotal("loadList", "");

		resultMap.put("rows", getDataUtil(regulationDao.findAll(startIndex, pageSize, sort, order)));
		resultMap.put("total", total);

		return resultMap;
	}

	/**
	 * 工具类---用来生成与列表字段一致的结果集
	 */
	@Transactional(readOnly = true)
	@Override
	public List<Map<String, Object>> getDataUtil(List<ELCommonCodeRegulation> list) {

		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();

		int i = 0; // 用来设置表格每页数据的单选框的name不同
		for (ELCommonCodeRegulation e : list) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", e.getId());
			map.put("name", e.getName() == null ? "" : e.getName());
			map.put("date_type", e.getDateType() == null ? "" : e.getDateType());
			map.put("prefix", e.getPrefix() == null ? "" : e.getPrefix());
			map.put("suffix", e.getSuffix() == null ? "" : e.getSuffix());
			map.put("random_length", e.getRandomLength() == null ? "" : e.getRandomLength());
			map.put("description", e.getDescription() == null ? "" : e.getDescription());

			String stateStr = "";
			i = i + 1;
			if (e.getState() == null) {
				map.put("state", "");
			} else {
				if (e.getState() == 0) {
					stateStr = "活动";
				} else {
					stateStr = "停用";
				}

				map.put("state", stateStr);
			}

			resultList.add(map);

		}

		return resultList;

	}

	/**
	 * 根据id查询
	 * 
	 * @param id
	 * @return
	 */
	@Transactional(readOnly = true)
	@Override
	public String GetCommonCodeRegulationById(String id) {
		if (null != id && !"".equals(id)) {
			ELCommonCodeRegulation commonCode = regulationDao.getCodeRegulationById(id);
			if (null == commonCode) {
				logger.equals("查询编码配置");
				return JsonMapper.toJsonString(CommonResponse.createNotExistCommonResponse());
			}
			// 查询成功则返回一个对象并置为success
			return JsonMapper.toJsonString(CommonResponse.createCommonResponse(commonCode));
		}
		logger.error("ID为null,程序出错");
		return JsonMapper.toJsonString(CommonResponse.createNotExistCommonResponse(id));

	}

	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class, readOnly = false)
	@Override
	public int deleteCodeRegulation(String id) {
		ELCommonCodeRegulation commonCode = regulationDao.getCodeRegulationById(id);
		commonCode.setDelFlag("1");
		return	regulationDao.updateByPrimaryKeySelective(commonCode);
		
	}
}
