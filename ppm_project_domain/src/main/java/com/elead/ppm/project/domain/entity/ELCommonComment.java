package com.elead.ppm.project.domain.entity;

import java.util.Date;

import com.elead.platform.common.api.DataEntity;

/**
 * 评论实体类
 * @author Administrator
 *
 */
public class ELCommonComment extends DataEntity<ELCommonComment>{
	
	private static final long serialVersionUID = 1L;

    private String ownerId;

    private String ownerType;

    private String commentId;

    private String commentName;

    private String content;

    private Date commentTime;

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

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId == null ? null : commentId.trim();
    }

    public String getCommentName() {
        return commentName;
    }

    public void setCommentName(String commentName) {
        this.commentName = commentName == null ? null : commentName.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }

}