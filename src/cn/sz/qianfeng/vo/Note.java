package cn.sz.qianfeng.vo;

import java.io.Serializable;
import java.sql.Timestamp;
/**
 * 用户的笔记
 * @author gl
 *
 */
public class Note implements Serializable {

	private Integer nid;//笔记编号
	private Integer userid;//笔记所属用户的用户编号
	private String notename;//笔记名字
	private Timestamp createTime;//创建时间
	private String path;//笔记文件存放路径(d:\\目录\\文件名.后缀)
	private String isShare;//是否共享(0表示共享，1表示不共享)
	private String summary;//简介
	private Integer readTimes;//查阅次数
	private Integer likeTimes;//点赞次数
	public Integer getNid() {
		return nid;
	}
	public void setNid(Integer nid) {
		this.nid = nid;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public String getNotename() {
		return notename;
	}
	public void setNotename(String notename) {
		this.notename = notename;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getIsShare() {
		return isShare;
	}
	public void setIsShare(String isShare) {
		this.isShare = isShare;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public Integer getReadTimes() {
		return readTimes;
	}
	public void setReadTimes(Integer readTimes) {
		this.readTimes = readTimes;
	}
	public Integer getLikeTimes() {
		return likeTimes;
	}
	public void setLikeTimes(Integer likeTimes) {
		this.likeTimes = likeTimes;
	}
}
