package fans.umamusume.www.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseUser<M extends BaseUser<M>> extends Model<M> implements IBean {

	public void setUid(java.lang.Integer uid) {
		set("uid", uid);
	}
	
	public java.lang.Integer getUid() {
		return getInt("uid");
	}

	public void setUsername(java.lang.String username) {
		set("username", username);
	}
	
	public java.lang.String getUsername() {
		return getStr("username");
	}

	public void setSalt(java.lang.String salt) {
		set("salt", salt);
	}
	
	public java.lang.String getSalt() {
		return getStr("salt");
	}

	public void setPwd(java.lang.String pwd) {
		set("pwd", pwd);
	}
	
	public java.lang.String getPwd() {
		return getStr("pwd");
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

}
