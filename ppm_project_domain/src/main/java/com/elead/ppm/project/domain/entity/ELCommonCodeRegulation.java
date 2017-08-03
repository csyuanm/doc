package com.elead.ppm.project.domain.entity;

import com.elead.platform.common.api.DataEntity;

/**
 * 实体类
 * @author Administrator
 *
 */
public class ELCommonCodeRegulation extends DataEntity<ELCommonCodeRegulation>{
	
	private static final long serialVersionUID = 1L;

    private String name;

    private String prefix; //前綴

    private String suffix; //後綴

    private String dateType; //日期格式

    private String code; //編碼

    private Byte randomLength; //随机数长度

    private String description; //描述
    
    private Byte state; //状态：0-活动；1-停用

    public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Byte getState() {
		return state;
	}

	public void setState(Byte state) {
		this.state = state;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix == null ? null : prefix.trim();
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix == null ? null : suffix.trim();
    }

    public String getDateType() {
        return dateType;
    }

    public void setDateType(String dateType) {
        this.dateType = dateType == null ? null : dateType.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public Byte getRandomLength() {
        return randomLength;
    }

    public void setRandomLength(Byte randomLength) {
        this.randomLength = randomLength;
    }

}