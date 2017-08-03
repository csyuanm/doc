package com.elead.ppm.project.domain.exception;

import com.elead.platform.common.api.exception.EleadBaseException;
import com.google.common.base.Joiner;

/**
* @Description: 自定义Exception
* @author wangxz
* @date 2017/03/22
*/
public class ConcurrentException extends EleadBaseException{
	private static final long serialVersionUID = -1644359954949978011L;
	
	private String tableName;
	private String db;
	private String operate;
	
	public ConcurrentException(String tableName, String db, String operate,String errorCode) {
		super();
		this.tableName = tableName;
		this.db = db;
		this.operate = operate;
		this.errorCode = errorCode;
	}

	@Override
	public String getMessage() {
		return Joiner.on(":").join(db,tableName,operate,"current exception");
	}
	
}	
