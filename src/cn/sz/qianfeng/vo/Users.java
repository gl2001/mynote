package cn.sz.qianfeng.vo;

import java.io.Serializable;
import java.sql.Timestamp;
/**
 * �û�ʵ����
 * @author gl
 *
 */
public class Users implements Serializable {

	private Integer userid;//�û����
	private String loginname;//��¼��,Ҫ��Ψһ
	private String pwd;//����,��MD5����
	private String realname;//��ʵ����
	private String nickname;//�ǳ�
	private String mobile;//�ֻ�����
	private String email;//����
	private String university;//��ҵѧУ
	private String subject;//רҵ
	private Timestamp graduateTime;//��ҵʱ��
	private String cls;//���ڵİ༶(�̶���ʽ����sz-android-xxxx)
	private String isAdmin;//�Ƿ��ǹ���Ա(0Ϊ����Ա��1Ϊ��ͨ�û�)
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public String getLoginname() {
		return loginname;
	}
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUniversity() {
		return university;
	}
	public void setUniversity(String university) {
		this.university = university;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public Timestamp getGraduateTime() {
		return graduateTime;
	}
	public void setGraduateTime(Timestamp graduateTime) {
		this.graduateTime = graduateTime;
	}
	public String getCls() {
		return cls;
	}
	public void setCls(String cls) {
		this.cls = cls;
	}
	public String getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(String isAdmin) {
		this.isAdmin = isAdmin;
	}
}
