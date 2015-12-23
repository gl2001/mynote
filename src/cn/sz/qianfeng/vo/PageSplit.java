package cn.sz.qianfeng.vo;

import java.io.Serializable;

public class PageSplit implements Serializable {

	private int cp = 1;//当前页码
	private int ps = 5;//每页显示行数
	private String column;//根据哪一列来查询
	private String kw;//列的关键字
	private int count = 0 ;//总行数
	private int allpage = 0 ;//总页数
	private boolean ishaveData = true;//是否有数据
	public int getCp() {
		return cp;
	}
	public void setCp(int cp) {
		this.cp = cp;
	}
	public int getPs() {
		return ps;
	}
	public void setPs(int ps) {
		this.ps = ps;
	}
	public String getColumn() {
		return column;
	}
	public void setColumn(String column) {
		this.column = column;
	}
	public String getKw() {
		return kw;
	}
	public void setKw(String kw) {
		this.kw = kw;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getAllpage() {
		return allpage;
	}
	public void setAllpage(int allpage) {
		this.allpage = allpage;
	}
	public boolean getIshaveData() {
		return ishaveData;
	}
	public void setIshaveData(boolean ishaveData) {
		this.ishaveData = ishaveData;
	}
}
