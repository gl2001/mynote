package cn.sz.qianfeng.vo;

import java.io.Serializable;
/**
 * �ؼ���
 * @author gl
 *
 */
public class KeyWord implements Serializable {

	private Integer kwid;//�ؼ��ֱ��
	private Integer nid;//�ؼ���������ƪ�ʼ�
	private String word;//�ؼ���
	public Integer getKwid() {
		return kwid;
	}
	public void setKwid(Integer kwid) {
		this.kwid = kwid;
	}
	public Integer getNid() {
		return nid;
	}
	public void setNid(Integer nid) {
		this.nid = nid;
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
}
