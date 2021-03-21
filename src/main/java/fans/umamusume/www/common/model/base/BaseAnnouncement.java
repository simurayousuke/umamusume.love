package fans.umamusume.www.common.model.base;

import com.jfinal.plugin.activerecord.IBean;
import com.jfinal.plugin.activerecord.Model;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseAnnouncement<M extends BaseAnnouncement<M>> extends Model<M> implements IBean {

	public void setId(java.lang.Integer id) {
		set("id", id);
	}
	
	public java.lang.Integer getId() {
		return getInt("id");
	}

	public void setTitle(java.lang.String title) {
		set("title", title);
	}
	
	public java.lang.String getTitle() {
		return getStr("title");
	}

	public void setContent(java.lang.String content) {
		set("content", content);
	}
	
	public java.lang.String getContent() {
		return getStr("content");
	}

	/**
	 * 0普通，其他越大越靠前
	 */
	public void setPriority(java.lang.Integer priority) {
		set("priority", priority);
	}
	
	/**
	 * 0普通，其他越大越靠前
	 */
	public java.lang.Integer getPriority() {
		return getInt("priority");
	}

	/**
	 * 浏览次数
	 */
	public void setViewCount(java.lang.Integer viewCount) {
		set("view_count", viewCount);
	}
	
	/**
	 * 浏览次数
	 */
	public java.lang.Integer getViewCount() {
		return getInt("view_count");
	}

	/**
	 * 点赞数
	 */
	public void setLikeCount(java.lang.Integer likeCount) {
		set("like_count", likeCount);
	}
	
	/**
	 * 点赞数
	 */
	public java.lang.Integer getLikeCount() {
		return getInt("like_count");
	}

	public void setCreateTime(java.util.Date createTime) {
		set("create_time", createTime);
	}
	
	public java.util.Date getCreateTime() {
		return get("create_time");
	}

	public void setUpdateTime(java.util.Date updateTime) {
		set("update_time", updateTime);
	}
	
	public java.util.Date getUpdateTime() {
		return get("update_time");
	}

	public void setTag(java.lang.String tag) {
		set("tag", tag);
	}
	
	public java.lang.String getTag() {
		return getStr("tag");
	}

	public void setHasTag(java.lang.Boolean hasTag) {
		set("has_tag", hasTag);
	}
	
	public java.lang.Boolean getHasTag() {
		return get("has_tag");
	}

	/**
	 * 0:label
1:label-primary
2:label-success:置顶
3:label-info
4:label-warning
5:label-danger:更新
	 */
	public void setLabelClass(java.lang.Integer labelClass) {
		set("label_class", labelClass);
	}
	
	/**
	 * 0:label
1:label-primary
2:label-success:置顶
3:label-info
4:label-warning
5:label-danger:更新
	 */
	public java.lang.Integer getLabelClass() {
		return getInt("label_class");
	}

	public void setDeleted(java.lang.Boolean deleted) {
		set("deleted", deleted);
	}
	
	public java.lang.Boolean getDeleted() {
		return get("deleted");
	}

}