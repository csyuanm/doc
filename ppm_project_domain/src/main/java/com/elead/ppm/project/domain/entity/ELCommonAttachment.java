package com.elead.ppm.project.domain.entity;

import com.elead.platform.common.api.DataEntity;

/**
 * 附件实体类
 * @author Administrator
 *
 */
public class ELCommonAttachment extends DataEntity<ELCommonAttachment>{
	
	private static final long serialVersionUID = 1L;
	
    private String ownerId;

    private String ownerType;

    private String name;

    private String code;

    private Integer serialNumber;

    private String path;

    private String type;

    private Integer size;

    private String folderId;

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId == null ? null : ownerId.trim();
    }

    public String getOwnerType() {
        return ownerType;
    }

    public void setOwnerType(String ownerType) {
        this.ownerType = ownerType == null ? null : ownerType.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public Integer getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Integer serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

}