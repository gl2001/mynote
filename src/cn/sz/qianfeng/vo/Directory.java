package cn.sz.qianfeng.vo;

import java.io.Serializable;
/**
 * �ֵ��
 * @author gl
 *
 */
public class Directory implements Serializable {

	private Integer directid;//�ֵ���
	private String subject;//�ֵ�������Ŀ
	private String option;//�ֵ�ѡ��
	private String kw;//ѡ���д
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
