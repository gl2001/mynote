package cn.sz.qianfeng.vo;

import java.io.Serializable;
import java.sql.Timestamp;
/**
 * �û��ıʼ�
 * @author gl
 *
 */
public class Note implements Serializable {

	private Integer nid;//�ʼǱ��
	private Integer userid;//�ʼ������û����û����
	private String notename;//�ʼ�����
	private Timestamp createTime;//����ʱ��
	private String path;//�ʼ��ļ����·��(d:\\Ŀ¼\\�ļ���.��׺)
	private String isShare;//�Ƿ���(0��ʾ����1��ʾ������)
	private String summary;//���
	private Integer readTimes;//���Ĵ���
	private Integer likeTimes;//���޴���
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
