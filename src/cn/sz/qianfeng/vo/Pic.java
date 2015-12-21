package cn.sz.qianfeng.vo;

import java.io.Serializable;

public class Pic implements Serializable {

	private Integer pid;//Í¼Æ¬±àºÅ
	private Integer nid;//Í¼Æ¬ÊôÓÚÄÄÆª±Ê¼Ç
	private String picpath;//Í¼Æ¬Â·¾¶
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public Integer getNid() {
		return nid;
	}
	public void setNid(Integer nid) {
		this.nid = nid;
	}
	public String getPicpath() {
		return picpath;
	}
	public void setPicpath(String picpath) {
		this.picpath = picpath;
	}
}
