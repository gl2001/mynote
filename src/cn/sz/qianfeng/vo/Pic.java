package cn.sz.qianfeng.vo;

import java.io.Serializable;

public class Pic implements Serializable {

	private Integer pid;//ͼƬ���
	private Integer nid;//ͼƬ������ƪ�ʼ�
	private String picpath;//ͼƬ·��
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
