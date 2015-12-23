package cn.sz.qianfeng.vo;

import java.io.Serializable;
/**
 * 字典表
 * @author gl
 *
 */
public class Directory implements Serializable {

	private Integer directid;//字典编号
	private String subject;//字典所属项目
	private String option;//字典选项
	private String kw;//选项简写
	public Integer getDirectid() {
		return directid;
	}
	public void setDirectid(Integer directid) {
		this.directid = directid;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getOption() {
		return option;
	}
	public void setOption(String option) {
		this.option = option;
	}
	public String getKw() {
		return kw;
	}
	public void setKw(String kw) {
		this.kw = kw;
	}
}
