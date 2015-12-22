package cn.sz.qianfeng.vo;

import java.io.Serializable;
import java.sql.Timestamp;
/**
 * 用户实体类
 * @author gl
 *
 */
public class Users implements Serializable {

	private Integer userid;//用户编号
	private String loginname;//登录名,要求唯一
	private String pwd;//密码,做MD5加密
	private String realname;//真实姓名
	private String nickname;//昵称
	private String mobile;//手机号码
	private String email;//邮箱
	private String university;//毕业学校
	private String subject;//专业
	private Timestamp graduateTime;//毕业时间
	private String cls;//现在的班级(固定格式：如sz-android-xxxx)
	private String isAdmin;//是否是管理员(0为管理员，1为普通用户)
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
