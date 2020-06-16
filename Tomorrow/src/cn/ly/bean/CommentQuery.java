package cn.ly.bean;
//专门封装查询条件
public class CommentQuery {
	private String site;
	private String num1;
	private String num2;
	private String exclusive;
	public String getExclusive() {
		return exclusive;
	}
	public void setExclusive(String exclusive) {
		this.exclusive = exclusive;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	public String getNum1() {
		return num1;
	}
	public void setNum1(String num1) {
		this.num1 = num1;
	}
	public String getNum2() {
		return num2;
	}
	public void setNum2(String num2) {
		this.num2 = num2;
	}
	
}
