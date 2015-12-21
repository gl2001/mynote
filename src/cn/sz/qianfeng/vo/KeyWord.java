package cn.sz.qianfeng.vo;

import java.io.Serializable;
/**
 * ¹Ø¼ü×Ö
 * @author gl
 *
 */
public class KeyWord implements Serializable {

	private Integer kwid;//¹Ø¼ü×Ö±àºÅ
	private Integer nid;//¹Ø¼ü×ÖÊôÓÚÄÄÆª±Ê¼Ç
	private String word;//¹Ø¼ü×Ö
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
